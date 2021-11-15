package by.allahverdiev.finaltask.service;

import by.allahverdiev.finaltask.dao.postgres.ArrivalDaoPg;
import by.allahverdiev.finaltask.dao.postgres.ConsumptionDaoPg;
import by.allahverdiev.finaltask.dao.postgres.ProductDaoPg;
import by.allahverdiev.finaltask.dao.postgres.UserDaoPg;
import by.allahverdiev.finaltask.entity.Arrival;
import by.allahverdiev.finaltask.entity.Consumption;
import by.allahverdiev.finaltask.entity.Entity;
import by.allahverdiev.finaltask.entity.Product;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WarehouseService implements Service {

    public Entity findProductById(int newId, Connection connection) {
//        DaoFactory factory = DaoFactory.getInstance();
        ProductDaoPg productDao = new ProductDaoPg(connection);
        UserDaoPg userDao = new UserDaoPg(connection);
        Product product = productDao.findEntityById(newId);
        userDao.update(product.getManager());
        return product;
    }

    public Map<Product, Integer> findAllProductsCountInCurrentDate(LocalDate start, LocalDate end, Connection connection) {
        Map<Product, Integer> result = new HashMap<>();
        ProductDaoPg productDao = new ProductDaoPg(connection);
        List<Product> productList = productDao.findAll();
        ArrivalDaoPg arrivalDao = new ArrivalDaoPg(connection);
        List<Arrival> arrivalList = arrivalDao.findAllInTimePeriod(start, end);
        ConsumptionDaoPg consumptionDao = new ConsumptionDaoPg(connection);
        List<Consumption> consumptionsList = consumptionDao.findAllInTimePeriod(start, end);

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
        return result;
    }
}
