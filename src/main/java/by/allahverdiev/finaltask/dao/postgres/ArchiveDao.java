package by.allahverdiev.finaltask.dao.postgres;

import by.allahverdiev.finaltask.dao.Dao;
import by.allahverdiev.finaltask.entity.Archive;
import by.allahverdiev.finaltask.entity.Entity;
import by.allahverdiev.finaltask.entity.Product;
import by.allahverdiev.finaltask.service.DateConversion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArchiveDao implements Dao {
    private static final Logger logger = LogManager.getLogger(ArchiveDao.class);
    private static final LocalDate startDate = LocalDate.of(2021, 01, 07);
    DateConversion conversion = new DateConversion();
    Connection connection;

    public ArchiveDao(Connection newConnection) {
        this.connection = newConnection;
    }


    private static final String SQL_SELECT_ARCHIVE =
            "SELECT * " +
                    "FROM manufacture.public.archive ";
    private static final String SQL_SELECT_ARCHIVE_AT_MONTH =
            "SELECT * " +
                    "FROM manufacture.public.archive " +
                    "WHERE month = (?)";
    private static final String SQL_INSERT_ARCHIVE_ENTRY =
            "INSERT INTO manufacture.public.archive " +
                    "(month, product_id, count) VALUES ((?),(?),(?))";

    @Override
    public List findAll() {
        List<Archive> archive = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ARCHIVE);
            while (resultSet.next()) {
                LocalDate date = conversion.toLocalDate(resultSet.getDate("month"));
                Product product = new Product(resultSet.getInt("product_id"));
                int count = resultSet.getInt("count");
                archive.add(new Archive(date, product, count));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return archive;
    }

    public List findEntryForMonth(LocalDate date) {
        List<Archive> archive = new ArrayList<>();
        YearMonth tempMonth = YearMonth.of(date.getYear(), date.getMonthValue());
        LocalDate currentDate = tempMonth.atEndOfMonth();
        try (PreparedStatement ps = connection.prepareStatement(SQL_SELECT_ARCHIVE_AT_MONTH)) {
            ps.setDate(1, Date.valueOf(currentDate));
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                LocalDate month = conversion.toLocalDate(resultSet.getDate("month"));
                Product product = new Product(resultSet.getInt("product_id"));
                int count = resultSet.getInt("count");
                archive.add(new Archive(month, product, count));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return archive;
    }

    public void createArchiveEntry(LocalDate date, Map<Product, Integer> map) {
        for (Map.Entry<Product, Integer> m : map.entrySet()) {
            try (PreparedStatement ps = connection.prepareStatement(SQL_INSERT_ARCHIVE_ENTRY)) {
                ps.setDate(1, Date.valueOf(date));
                ps.setInt(2, m.getKey().getId());
                ps.setInt(3, m.getValue());
                ps.executeUpdate();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
    }

    public boolean isArchiveEntryExist(LocalDate date) {
        boolean isExist = false;
        LocalDate startDate = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
        try (PreparedStatement ps = connection.prepareStatement(SQL_SELECT_ARCHIVE_AT_MONTH)) {
            ps.setDate(1, Date.valueOf(startDate));
            isExist = ps.execute();
            logger.info(isExist + "после проверки на существование записи в методе isArchiveEntryExist");
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return isExist;
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
