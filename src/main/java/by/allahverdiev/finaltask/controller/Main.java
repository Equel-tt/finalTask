package by.allahverdiev.finaltask.controller;

import by.allahverdiev.finaltask.dao.ConnectionCreator;
import by.allahverdiev.finaltask.dao.postgres.ProductDaoPg;
import by.allahverdiev.finaltask.entity.Product;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        ProductDaoPg productDao = new ProductDaoPg(ConnectionCreator.createConnection());
//        List<Product> products = productDao.findAll();

        Product product = productDao.findEntityById(1247);
        System.out.println(product);

//        for (Product product : products){
//            System.out.println(product);
//        }
    }
}



