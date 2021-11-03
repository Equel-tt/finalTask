package by.allahverdiev.finaltask.dao.postgres;

import by.allahverdiev.finaltask.dao.ProductDao;
import by.allahverdiev.finaltask.entity.Entity;
import by.allahverdiev.finaltask.entity.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoPg implements ProductDao {
    private static final Logger LOGGER = LogManager.getLogger(ProductDaoPg.class);
    Connection connection;

    public ProductDaoPg(Connection newConnection) {
        this.connection = newConnection;
    }

    private static final String SQL_SELECT_ALL_PRODUCTS =
            "SELECT *" +
                    "FROM manufacture.public.product";
    private static final String SQL_SELECT_PRODUCT_BY_ID =
            "SELECT * " +
                    "FROM manufacture.public.product " +
                    "WHERE product.id = (?)";

    public ProductDaoPg() throws SQLException {
    }


    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_PRODUCTS);
            while (resultSet.next()) {
                int newId = resultSet.getInt("id");
                String newName = resultSet.getString("name");
                products.add(new Product(newId, newName));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return products;
    }

    @Override
    public Entity findEntityById(Object id) throws SQLException {
        return null;
    }

    public Product findEntityById(int id) throws SQLException {
        Product result = new Product();
        PreparedStatement ps = connection.prepareStatement(SQL_SELECT_PRODUCT_BY_ID);
        ps.setInt(1, id);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            result.setId(resultSet.getInt("id"));
            result.setName(resultSet.getString("name"));
        }
        return result;
    }

    @Override
    public boolean delete(Object id) {
        return false;
    }

    @Override
    public boolean delete(Entity entity) {
        return false;
    }

    @Override
    public boolean create(Entity entity) {
        return false;
    }

    @Override
    public Entity update(Entity entity) {
        return null;
    }
}
