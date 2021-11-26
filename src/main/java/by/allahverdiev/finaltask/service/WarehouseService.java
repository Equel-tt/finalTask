package by.allahverdiev.finaltask.service;

import by.allahverdiev.finaltask.dao.DaoFactory;
import by.allahverdiev.finaltask.dao.RegulationException;
import by.allahverdiev.finaltask.dao.postgres.*;
import by.allahverdiev.finaltask.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WarehouseService implements Service {
    private static final Logger logger = LogManager.getLogger(WarehouseService.class);
    DaoFactory factory = DaoFactory.getInstance();

    public Entity findProductById(int newId, Connection connection) {
        ProductDaoPg productDao = factory.getProductDao(connection);
        UserDaoPg userDao = factory.getUserDao(connection);
        Product product = productDao.findEntityById(newId);
        userDao.update(product.getManager());
        return product;
    }

    public Entity findProductByName(String name, Connection connection) {
        ProductDaoPg productDao = factory.getProductDao(connection);
        UserDaoPg userDao = factory.getUserDao(connection);
        Product product = productDao.findEntityByName(name);
        userDao.update(product.getManager());
        return product;
    }

    public Map<Product, Integer> findAllProductsCountInCurrentDate(LocalDate date, Connection connection) throws RegulationException {
        LocalDate start = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
        Map<Product, Integer> result = new HashMap<>();
        ProductDaoPg productDao = factory.getProductDao(connection);
        List<Product> productList = productDao.findAll();
        ArrivalDaoPg arrivalDao = factory.getArrivalDao(connection);
        List<Arrival> arrivalList = arrivalDao.findAllInTimePeriod(start, date);
        ConsumptionDaoPg consumptionDao = factory.getConsumptionDao(connection);
        List<Consumption> consumptionsList = consumptionDao.findAllInTimePeriod(start, date);

        for (Product product : productList) {
            int count = 0;
            for (Arrival arrival : arrivalList) {
                if (arrival.getProduct().getId() == product.getId()) {
                    count += arrival.getCount();
                }
            }
            for (Consumption consumption : consumptionsList) {
                if (consumption.getProduct().getId() == product.getId()) {
                    count -= consumption.getCount();
                }
            }
            result.put(product, count);
        }
        //find last archive entry, and it's date, if not exist - use static startDate (start date of accounting)
        if (date.getMonthValue() != 1 && date.getYear() != 2021) {
            ArchiveDaoPg archiveDao = factory.getArchiveDao(connection);
            List<Archive> archiveList = archiveDao.findLastArchiveEntry(date);
            for (Map.Entry<Product, Integer> entry : result.entrySet()) {
                for (Archive archive : archiveList) {
                    if (entry.getKey().getId() == archive.getProduct().getId()) {
                        entry.setValue(entry.getValue() + archive.getCount());
                    }
                }
            }
        }
        return result;
    }

    public List<Arrival> findArrivalInDate(Date date, Connection connection) {
        ArrivalDaoPg arrivalDao = factory.getArrivalDao(connection);
        List<Arrival> arrivalList = arrivalDao.findArrivalsInCurrentDate(new java.sql.Date(date.getTime()));
        ProductDaoPg productDao = factory.getProductDao(connection);
        UserDaoPg userDao = factory.getUserDao(connection);
        for (Arrival arrival : arrivalList) {
            arrival.setProduct(productDao.updateName(arrival.getProduct()));
            arrival.setUser(userDao.updateName(arrival.getUser()));
        }
        return arrivalList;
    }

    public List<String> prepareForSearch(Connection connection) {
        ProductDaoPg productDao = factory.getProductDao(connection);
        return productDao.findNamesOfProducts();
    }

    public boolean addArrivalEntry(String doc, int count, Date date, Product product, double price, User user, Connection connection) throws SQLException {
        logger.info("страрт команды");
        ArrivalDaoPg arrivalDao = factory.getArrivalDao(connection);
        int productId = product.getId();
        logger.info(product.getId());
        int userId = user.getId();
        logger.info(user.getId());
        arrivalDao.createArrivalEntry(doc, count, new java.sql.Date(date.getTime()), productId, price, userId);
        return true;
    }
}
