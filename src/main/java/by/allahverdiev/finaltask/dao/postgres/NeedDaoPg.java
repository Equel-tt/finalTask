package by.allahverdiev.finaltask.dao.postgres;

import by.allahverdiev.finaltask.dao.NeedDao;
import by.allahverdiev.finaltask.dao.util.DateConversion;
import by.allahverdiev.finaltask.entity.Department;
import by.allahverdiev.finaltask.entity.Entity;
import by.allahverdiev.finaltask.entity.Need;
import by.allahverdiev.finaltask.entity.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NeedDaoPg implements NeedDao {
    private static final Logger logger = LogManager.getLogger(NeedDaoPg.class);
    DateConversion conversion = new DateConversion();
    Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public NeedDaoPg() {
    }

    private static final String SQL_SELECT_ALL_NEED =
            "SELECT * " +
                    "FROM manufacture.public.need ";
    private static final String SQL_SELECT_NEED_FOR_MONTH =
            "SELECT * " +
                    "FROM manufacture.public.need " +
                    "WHERE month = (?)";

    @Override
    public List<Need> findAll() {
        List<Need> need = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_NEED);
            buildResultList(need, resultSet);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return need;
    }

    @Override
    public List<Need> findNeedForCurrentMonth(LocalDate date) {
        List<Need> need = new ArrayList<>();
        int montNum = date.getMonthValue();
        int yearNum = date.getYear();
        LocalDate startDate = LocalDate.of(yearNum, montNum, 1);
        try (PreparedStatement ps = connection.prepareStatement(SQL_SELECT_NEED_FOR_MONTH)) {
            ps.setDate(1, Date.valueOf(startDate));
            ResultSet resultSet = ps.executeQuery();
            buildResultList(need, resultSet);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return need;
    }

    private void buildResultList(List<Need> need, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            LocalDate month = conversion.toLocalDate(resultSet.getDate("month"));
            Product product = new Product(resultSet.getInt("product_id"));
            int count = resultSet.getInt("count");
            Department destination = new Department(resultSet.getInt("destination_id"));
            need.add(new Need(month, product, count, destination));
        }
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
