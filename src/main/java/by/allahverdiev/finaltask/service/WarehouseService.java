package by.allahverdiev.finaltask.service;

import by.allahverdiev.finaltask.dao.DaoFactory;
import by.allahverdiev.finaltask.dao.RegulationException;
import by.allahverdiev.finaltask.dao.postgres.*;
import by.allahverdiev.finaltask.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WarehouseService implements Service {
    private static final Logger logger = LogManager.getLogger(WarehouseService.class);
    private final DaoFactory factory;

    public WarehouseService(DaoFactory factory) {
        this.factory = factory;
    }

    public Entity findProductById(int newId, Connection connection) {
        logger.info(factory.getClass());
        ProductDaoPg productDao = factory.getProductDao(connection);
        UserDaoPg userDao = factory.getUserDao(connection);
        logger.info(userDao.getClass());
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

    /**
     * starting from the existing archive record for the previous month,
     * calculate the current quantity for each material, adding arrivals and subtracting consumptions
     *
     * @throws RegulationException
     */
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
            count = addArrivalCount(arrivalList, product, count);
            count = takeAwayConsumptionCount(consumptionsList, product, count);
            result.put(product, count);
        }
        //find last archive entry, and it's date
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

    private int takeAwayConsumptionCount(List<Consumption> consumptionsList, Product product, int count) {
        for (Consumption consumption : consumptionsList) {
            if (consumption.getProduct().getId() == product.getId()) {
                count -= consumption.getCount();
            }
        }
        return count;
    }

    private int addArrivalCount(List<Arrival> arrivalList, Product product, int count) {
        for (Arrival arrival : arrivalList) {
            if (arrival.getProduct().getId() == product.getId()) {
                count += arrival.getCount();
            }
        }
        return count;
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

    /**
     * prepare a list of names of all products for prompting the user
     */
    public List<String> prepareForSearch(Connection connection) {
        ProductDaoPg productDao = factory.getProductDao(connection);
        return productDao.findNamesOfProducts();
    }

    public boolean addArrivalEntry(String doc, int count, Date date, Product product, double price, User user, Connection connection) throws SQLException {
        ArrivalDaoPg arrivalDao = factory.getArrivalDao(connection);
        ArchiveDaoPg archiveDao = factory.getArchiveDao(connection);
        int productId = product.getId();
        logger.info(product.getId());
        int userId = user.getId();
        logger.info(user.getId());
        LocalDate localDate = Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        YearMonth tempMonth = YearMonth.of(localDate.getYear(), localDate.getMonthValue());
        LocalDate lastDate = tempMonth.atEndOfMonth();
        if (archiveDao.isArchiveEntryExist(lastDate)) {
            return false; //нельзя оформить поступление, т.к. месяц уже закрыт
        }
        arrivalDao.createArrivalEntry(doc, count, new java.sql.Date(date.getTime()), productId, price, userId);
        return true;
    }
}
