package by.allahverdiev.finaltask.service;

import by.allahverdiev.finaltask.dao.DaoFactory;
import by.allahverdiev.finaltask.dao.RegulationException;
import by.allahverdiev.finaltask.dao.postgres.ArchiveDaoPg;
import by.allahverdiev.finaltask.dao.postgres.ArrivalDaoPg;
import by.allahverdiev.finaltask.dao.postgres.ConsumptionDaoPg;
import by.allahverdiev.finaltask.dao.postgres.ProductDaoPg;
import by.allahverdiev.finaltask.entity.Archive;
import by.allahverdiev.finaltask.entity.Arrival;
import by.allahverdiev.finaltask.entity.Consumption;
import by.allahverdiev.finaltask.entity.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookkeepingService implements Service {
    private static final Logger logger = LogManager.getLogger(BookkeepingService.class);
    DaoFactory factory = DaoFactory.getInstance();

    public boolean createArchiveEntry(LocalDate date, Connection connection) throws RegulationException, SQLException {
        YearMonth tempMonth = YearMonth.of(date.getYear(), date.getMonthValue());
        LocalDate start = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
        LocalDate month = tempMonth.atEndOfMonth();
        logger.debug(month);
        try {
            connection.setAutoCommit(false);
            ArchiveDaoPg archiveDao = factory.getArchiveDao(connection);
            if (!archiveDao.isArchiveEntryExist(month)) {
                Map<Product, Integer> result = new HashMap<>();
                ProductDaoPg productDao = factory.getProductDao(connection);
                List<Product> productList = productDao.findAll();
                ArrivalDaoPg arrivalDao = factory.getArrivalDao(connection);
                List<Arrival> arrivalList = arrivalDao.findAllInTimePeriod(start, month);
                ConsumptionDaoPg consumptionDao = factory.getConsumptionDao(connection);
                List<Consumption> consumptionsList = consumptionDao.findAllInTimePeriod(start, month);

                for (Product product : productList) {
                    int count = 0;
                    for (Arrival arrival : arrivalList) {
                        if (arrival.getProduct().getId() == product.getId()) {
                            count += arrival.getCount();
                        }
                    }
                    for (Consumption consumption : consumptionsList) {
                        if (consumption.getProduct().getId() == product.getId()) {
                            count -= consumption.getCount();
                        }
                    }
                    result.put(product, count);
                }

                if (!month.equals(LocalDate.parse("2021-01-31"))) {
                    List<Archive> archiveList = archiveDao.findLastArchiveEntry(date);
                    for (Map.Entry<Product, Integer> map : result.entrySet()) {
                        for (Archive archive : archiveList) {
                            if (map.getKey().getId() == archive.getProduct().getId()) {
                                map.setValue(map.getValue() + archive.getCount());
                            }
                        }
                    }
                }

                archiveDao.createArchiveEntry(month, result);
                connection.commit();
                return true;
            }
        } catch (SQLException e) {
            connection.rollback();
        }
        return false;
    }

    public List<Archive> findArchiveEntryByMonth(LocalDate date, Connection connection) {
        YearMonth tempMonth = YearMonth.of(date.getYear(), date.getMonthValue());
        LocalDate month = tempMonth.atEndOfMonth();
        ArchiveDaoPg archiveDao = factory.getArchiveDao(connection);
        return archiveDao.findEntryForMonth(month);
    }

    public List<Archive> findAllArchive(Connection connection) {
        ArchiveDaoPg archiveDaoPg = factory.getArchiveDao(connection);
        return archiveDaoPg.findAll();
    }

    public List<Archive> findPreviousArchiveEntry(LocalDate date, Connection connection) throws RegulationException {
        ArchiveDaoPg archiveDao = factory.getArchiveDao(connection);
        return archiveDao.findLastArchiveEntry(date);
    }

    public boolean deleteArrivalEntry(Connection connection, String document, int productId) throws SQLException {
        ArrivalDaoPg arrivalDao = factory.getArrivalDao(connection);
        connection.setAutoCommit(false);
        if (arrivalDao.deleteEntityByKeys(document, productId) == 1) {
            connection.commit();
            return true;
        } else {
            connection.rollback();
            return false;
        }
    }
}
