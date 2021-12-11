package by.allahverdiev.finaltask.dao.postgres;

import by.allahverdiev.finaltask.dao.Dao;
import by.allahverdiev.finaltask.dao.util.DateConversion;
import by.allahverdiev.finaltask.entity.Consumption;
import by.allahverdiev.finaltask.entity.Department;
import by.allahverdiev.finaltask.entity.Entity;
import by.allahverdiev.finaltask.entity.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ConsumptionDaoPg implements Dao {
    private static final Logger LOGGER = LogManager.getLogger(ConsumptionDaoPg.class);
    public static final LocalDate startDate = LocalDate.of(2021, 01, 07);
    DateConversion conversion = new DateConversion();
    Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public ConsumptionDaoPg() {
    }

    private static final String SQL_SELECT_ALL_CONSUMPTION_IN_TIME_PERIOD =
            "SELECT * " +
                    "FROM manufacture.public.consumption " +
                    "WHERE date BETWEEN (?) AND (?)";
    private static final String SQL_SELECT_PRODUCT_CONSUMPTION_IN_TIME_PERIOD =
            "SELECT * " +
                    "FROM manufacture.public.consumption " +
                    "WHERE (product_id = (?)) AND (date BETWEEN (?) AND (?))";

    public List<Consumption> findAllInTimePeriod(LocalDate startDate, LocalDate endDate) {
        List<Consumption> consumptions = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SQL_SELECT_ALL_CONSUMPTION_IN_TIME_PERIOD)) {
            ps.setDate(1, Date.valueOf(startDate));
            ps.setDate(2, Date.valueOf(endDate));
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int count = resultSet.getInt("count");
                LocalDate date = conversion.toLocalDate(resultSet.getDate("date"));
                Department department = new Department(resultSet.getInt("destination_id"));
                Product product = new Product(resultSet.getInt("product_id"));
                consumptions.add(new Consumption(id, count, date, department, product));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return consumptions;
    }

    public List<Consumption> findByProductInTimePeriod(int productId, LocalDate startDate, LocalDate endDate) {
        List<Consumption> consumptions = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SQL_SELECT_PRODUCT_CONSUMPTION_IN_TIME_PERIOD)) {
            ps.setInt(1, productId);
            ps.setDate(2, Date.valueOf(startDate));
            ps.setDate(3, Date.valueOf(endDate));
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int count = resultSet.getInt("count");
                LocalDate date = conversion.toLocalDate(resultSet.getDate("date"));
                Department department = new Department(resultSet.getInt("destination_id"));
                Product product = new Product(productId);
                consumptions.add(new Consumption(id, count, date, department, product));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return consumptions;
    }

    @Override
    public List findAll() throws SQLException {
        return null;
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
