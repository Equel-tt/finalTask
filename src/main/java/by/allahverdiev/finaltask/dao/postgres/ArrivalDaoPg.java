package by.allahverdiev.finaltask.dao.postgres;

import by.allahverdiev.finaltask.dao.ArrivalDao;
import by.allahverdiev.finaltask.entity.Arrival;
import by.allahverdiev.finaltask.entity.Entity;
import by.allahverdiev.finaltask.entity.Product;
import by.allahverdiev.finaltask.entity.User;
import by.allahverdiev.finaltask.service.DateConversion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ArrivalDaoPg implements ArrivalDao {
    private static final Logger LOGGER = LogManager.getLogger(ArrivalDaoPg.class);
    DateConversion conversion = new DateConversion();
    Connection connection;

    public ArrivalDaoPg() {
    }

    public ArrivalDaoPg(Connection newConnection) {
        this.connection = newConnection;
    }

    private static final String SQL_SELECT_ALL_ARRIVALS =
            "SELECT * " +
                    "FROM manufacture.public.arrival";
    private static final String SQL_SELECT_ARRIVALS_BY_PRODUCT_ID =
            "SELECT * " +
                    "FROM manufacture.public.arrival " +
                    "WHERE product_id = (?)";


    @Override
    public List<Arrival> findAll() {
        List<Arrival> arrivals = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_ARRIVALS);
            while (resultSet.next()) {
                String doc = resultSet.getString("doc");
                double count = resultSet.getDouble("count");
                LocalDate date = conversion.toLocalDate(resultSet.getDate("date"));
                Product product = new Product(resultSet.getInt("product_id"));
                double price = resultSet.getDouble("price");
                User user = new User(resultSet.getInt("user_id"));
                arrivals.add(new Arrival(doc, count, date, product, price, user));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return arrivals;
    }

    public List<Arrival> findByProductId(int id) {
        List<Arrival> arrivals = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SQL_SELECT_ARRIVALS_BY_PRODUCT_ID)) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                String doc = resultSet.getString("doc");
                double count = resultSet.getDouble("count");
                LocalDate date = conversion.toLocalDate(resultSet.getDate("date"));
                Product product = new Product(id);
                double price = resultSet.getDouble("price");
                User user = new User(resultSet.getInt("user_id"));
                arrivals.add(new Arrival(doc, count, date, product, price, user));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return arrivals;
    }

    @Override
    public Entity findEntityById(Object id) throws SQLException {
        return null;
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
