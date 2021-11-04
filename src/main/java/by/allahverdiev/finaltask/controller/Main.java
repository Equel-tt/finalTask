package by.allahverdiev.finaltask.controller;

import by.allahverdiev.finaltask.dao.ConnectionCreator;
import by.allahverdiev.finaltask.dao.postgres.ArrivalDaoPg;
import by.allahverdiev.finaltask.dao.postgres.ProductDaoPg;
import by.allahverdiev.finaltask.dao.postgres.UserDaoPg;
import by.allahverdiev.finaltask.entity.Arrival;
import by.allahverdiev.finaltask.entity.Entity;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        ProductDaoPg productDao = new ProductDaoPg(ConnectionCreator.createConnection());
        UserDaoPg userDao = new UserDaoPg(ConnectionCreator.createConnection());
        ArrivalDaoPg arrivalDao = new ArrivalDaoPg(ConnectionCreator.createConnection());

        List<Arrival> products = arrivalDao.findByProductId(1900);
        for (Entity product : products) {
            System.out.println(product);
        }

//        User product = userDao.findEntityById(5);
//        System.out.println(product);
    }
}



