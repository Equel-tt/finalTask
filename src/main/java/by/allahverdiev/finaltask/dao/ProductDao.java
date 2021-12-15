package by.allahverdiev.finaltask.dao;

import by.allahverdiev.finaltask.entity.Product;

import java.util.List;

public interface ProductDao extends Dao {

    Product findEntityById(int id);

    Product findEntityByName(String name);

    List<Product> findAllByManagerRole(int userId);

    List<String> findNamesOfProducts();

    int createProduct(String name, int managerId, int productTypeId, int providerId);

    Product updateName(Product product);
}
