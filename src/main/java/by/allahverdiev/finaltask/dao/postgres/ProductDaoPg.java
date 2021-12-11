package by.allahverdiev.finaltask.dao.postgres;

import by.allahverdiev.finaltask.dao.ProductDao;
import by.allahverdiev.finaltask.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoPg implements ProductDao {
    private static final Logger logger = LogManager.getLogger(ProductDaoPg.class);
    Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public ProductDaoPg() {
    }

    private static final String SQL_SELECT_ALL_PRODUCTS =
            "SELECT *" +
                    "FROM manufacture.public.product";
    private static final String SQL_SELECT_PRODUCT_BY_ID =
            "SELECT * " +
                    "FROM manufacture.public.product " +
                    "WHERE product.id = (?)";
    private static final String SQL_SELECT_PRODUCT_BY_NAME =
            "SELECT * " +
                    "FROM manufacture.public.product " +
                    "WHERE product.name = (?)";
    private static final String SQL_SELECT_PRODUCT_BY_MANAGER =
            "SELECT * " +
                    "FROM manufacture.public.product " +
                    "WHERE manager_id = (?)";
    private static final String SQL_UPDATE_PRODUCT_TYPE_BY_ID =
            "SELECT * " +
                    "FROM manufacture.public.product_type " +
                    "WHERE product_type.id = (?)";
    private static final String SQL_UPDATE_PROVIDER_BY_ID =
            "SELECT * " +
                    "FROM manufacture.public.provider " +
                    "WHERE provider.id = (?)";
    private static final String SQL_SELECT_PRODUCT_LIST_BY_NAME_FOR_SEARCH =
            "SELECT name " +
                    "FROM manufacture.public.product ";
    private static final String SQL_INSERT_PRODUCT =
            "INSERT INTO manufacture.public.product " +
                    "(name, manager_id, provider_id, product_type_id) " +
                    "VALUES ((?), (?), (?), (?))";

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_PRODUCTS);
            while (resultSet.next()) {
                int newId = resultSet.getInt("id");
                String newName = resultSet.getString("name");
                User user = new User(resultSet.getInt("manager_id"));
                ProductType type = new ProductType(resultSet.getInt("product_type_id"));
                Provider provider = new Provider(resultSet.getInt("provider_id"));
                products.add(new Product(newId, newName, user, type, provider));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return products;
    }

    @Override
    public Entity findEntityById(Object id) {
        return null;
    }

    public Product findEntityById(int id) {
        Product result = new Product();
        try (PreparedStatement ps = connection.prepareStatement(SQL_SELECT_PRODUCT_BY_ID)) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                result.setId(resultSet.getInt("id"));
                result.setName(resultSet.getString("name"));
                result.setManager(new User(resultSet.getInt("manager_id")));
                result.setProductType(new ProductType(resultSet.getInt("product_type_id")));
                updateType(result.getProductType());
                result.setProvider(new Provider(resultSet.getInt("provider_id")));
                updateProvider(result.getProvider());
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return result;
    }

    public Product findEntityByName(String name) {
        Product result = new Product();
        try (PreparedStatement ps = connection.prepareStatement(SQL_SELECT_PRODUCT_BY_NAME)) {
            ps.setString(1, name);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                result.setId(resultSet.getInt("id"));
                result.setName(resultSet.getString("name"));
                result.setManager(new User(resultSet.getInt("manager_id")));
                result.setProductType(new ProductType(resultSet.getInt("product_type_id")));
                updateType(result.getProductType());
                result.setProvider(new Provider(resultSet.getInt("provider_id")));
                updateProvider(result.getProvider());
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return result;
    }

    public List<Product> findAllByManagerRole(int userId) {
        List<Product> products = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SQL_SELECT_PRODUCT_BY_MANAGER)) {
            ps.setInt(1, userId);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int newId = resultSet.getInt("id");
                String newName = resultSet.getString("name");
                User user = new User(userId);
                ProductType type = new ProductType(resultSet.getInt("product_type_id"));
                Provider provider = new Provider(resultSet.getInt("provider_id"));
                products.add(new Product(newId, newName, user, type, provider));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return products;
    }

    public List<String> findNamesOfProducts() {
        List<String> result = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_PRODUCT_LIST_BY_NAME_FOR_SEARCH);
            while (resultSet.next()) {
                result.add(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return result;
    }

    public int createProduct(String name, int managerId, int productTypeId, int providerId) {
        int rows = 0;
        try (PreparedStatement ps = connection.prepareStatement(SQL_INSERT_PRODUCT)) {
            ps.setString(1, name);
            ps.setInt(2, managerId);
            ps.setInt(3, productTypeId);
            ps.setInt(4, providerId);
            rows = ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return rows;
    }

    /**
     * update product type using reference table "manufacture.public.product_type"
     */
    private void updateType(Entity entity) {
        int id = ((ProductType) entity).getId();
        try (PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_PRODUCT_TYPE_BY_ID)) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                ((ProductType) entity).setName(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * update provider using reference table "manufacture.public.provider"
     */
    private void updateProvider(Entity entity) {
        int id = ((Provider) entity).getId();
        try (PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_PROVIDER_BY_ID)) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                ((Provider) entity).setName(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
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
        int id = ((Product) entity).getId();
        try (PreparedStatement ps = connection.prepareStatement(SQL_SELECT_PRODUCT_BY_ID)) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                ((Product) entity).setName(resultSet.getString("name"));
                ((Product) entity).setManager(new User(resultSet.getInt("manager_id")));
                ((Product) entity).setProductType(new ProductType(resultSet.getInt("product_type_id")));
                ((Product) entity).setProvider(new Provider(resultSet.getInt("provider_id")));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return entity;
    }

    public Product updateName(Product product) {
        int id = product.getId();
        try (PreparedStatement ps = connection.prepareStatement(SQL_SELECT_PRODUCT_BY_ID)) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                product.setName(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        logger.info(product.getName());
        return product;
    }
}
