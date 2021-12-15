package by.allahverdiev.finaltask.dao.postgres;

import by.allahverdiev.finaltask.dao.ArchiveDao;
import by.allahverdiev.finaltask.dao.RegulationException;
import by.allahverdiev.finaltask.dao.util.DateConversion;
import by.allahverdiev.finaltask.entity.Archive;
import by.allahverdiev.finaltask.entity.Entity;
import by.allahverdiev.finaltask.entity.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArchiveDaoPg implements ArchiveDao {
    private static final Logger logger = LogManager.getLogger(ArchiveDaoPg.class);
    private final DateConversion conversion = new DateConversion();
    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public ArchiveDaoPg() {
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
    private static final String SQL_DELETE_ARCHIVE_ENTRY =
            "DELETE FROM manufacture.public.archive " +
                    "WHERE month = (?) ";

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

    @Override
    public List<Archive> findEntryForMonth(LocalDate date) {
        List<Archive> archive = new ArrayList<>();
        YearMonth tempMonth = YearMonth.of(date.getYear(), date.getMonthValue());
        LocalDate currentDate = tempMonth.atEndOfMonth();
        return buildArchiveList(archive, currentDate);
    }

    @Override
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

    @Override
    public boolean isArchiveEntryExist(LocalDate date) {
        boolean isExist = true;
        try (PreparedStatement ps = connection.prepareStatement(SQL_SELECT_ARCHIVE_AT_MONTH)) {
            ps.setDate(1, Date.valueOf(date));
            ResultSet resultSet = ps.executeQuery();
            isExist = resultSet.next();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return isExist;
    }

    @Override
    public List<Archive> findLastArchiveEntry(LocalDate date) throws RegulationException {
        List<Archive> archive = new ArrayList<>();
        LocalDate previousMonth = date.minusMonths(1);
        YearMonth tempMonth = YearMonth.of(previousMonth.getYear(), previousMonth.getMonthValue());
        LocalDate lastDate = tempMonth.atEndOfMonth();
        if (isArchiveEntryExist(lastDate)) {
            return buildArchiveList(archive, lastDate); //был previousMonth
        } else {
            throw new RegulationException("Предыдущий месяц не закрыт");
        }
    }

    private List<Archive> buildArchiveList(List<Archive> archive, LocalDate currentDate) {
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

    @Override
    public boolean deleteArchiveEntry(Date date) {
        try (PreparedStatement ps = connection.prepareStatement(SQL_DELETE_ARCHIVE_ENTRY)) {
            ps.setDate(1, date);
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return false;
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
