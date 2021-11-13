package by.allahverdiev.finaltask.service;

import by.allahverdiev.finaltask.dao.postgres.ProductDaoPg;
import by.allahverdiev.finaltask.dao.postgres.UserDaoPg;
import by.allahverdiev.finaltask.entity.Entity;
import by.allahverdiev.finaltask.entity.Product;

import java.sql.Connection;

public class WarehouseService implements Service {

    public Entity findProductById(int newId, Connection connection) {
        ProductDaoPg productDao = new ProductDaoPg(connection);
        UserDaoPg userDao = new UserDaoPg(connection);
        Product product = (Product) productDao.findEntityById(newId);
        userDao.update(product.getManager());
        return product;
    }
}
