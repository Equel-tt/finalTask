package by.allahverdiev.finaltask.controller;

import by.allahverdiev.finaltask.dao.pool.ConnectionPool;
import by.allahverdiev.finaltask.dao.postgres.ArrivalDaoPg;
import by.allahverdiev.finaltask.dao.postgres.ProductDaoPg;
import by.allahverdiev.finaltask.dao.postgres.UserDaoPg;
import by.allahverdiev.finaltask.entity.Entity;
import by.allahverdiev.finaltask.entity.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class Main {
    private static final Logger logger = LogManager.getLogger(ProductDaoPg.class);
    public static void main(String[] args) throws SQLException {
        logger.info("test");
        logger.error("test");
        ConnectionPool connection = ConnectionPool.getInstance();
        connection.init();
//        ProductDaoPg productDao = new ProductDaoPg(connection.getConnection());
        ProductDaoPg productDao = new ProductDaoPg(connection.getConnection());
        UserDaoPg userDao = new UserDaoPg(connection.getConnection());
        ArrivalDaoPg arrivalDao = new ArrivalDaoPg(connection.getConnection());


        List<Product> products = productDao.findAll();
//        products.forEach(x -> userDao.update(x.getManager()));
        for (Entity product : products) {
            System.out.println(product);
        }

//        Entity product = productDao.findEntityById(1800);
//        userDao.update(((Product)product).getManager());
//        System.out.println(product);
    }
}



