package by.allahverdiev.finaltask.service;

import by.allahverdiev.finaltask.dao.postgres.ArchiveDao;
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
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookkeepingService implements Service {
    private static final Logger logger = LogManager.getLogger(BookkeepingService.class);

    public boolean createArchiveEntry(LocalDate date, Connection connection) {
        YearMonth tempMonth = YearMonth.of(date.getYear(), date.getMonthValue());
        LocalDate start = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
        LocalDate month = tempMonth.atEndOfMonth();
        logger.info(month + "месяц после обработки в сервисе");
        ArchiveDao archiveDao = new ArchiveDao(connection);
        if (!archiveDao.isArchiveEntryExist(month)) {
            logger.info("записи в архиве нет");
            Map<Product, Integer> result = new HashMap<>();
            ProductDaoPg productDao = new ProductDaoPg(connection);
            List<Product> productList = productDao.findAll();
            ArrivalDaoPg arrivalDao = new ArrivalDaoPg(connection);
            List<Arrival> arrivalList = arrivalDao.findAllInTimePeriod(start, month);
            ConsumptionDaoPg consumptionDao = new ConsumptionDaoPg(connection);
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
            archiveDao.createArchiveEntry(month, result);
            return true;
        }
        return false;
    }

    public List<Archive> findArchiveEntryByMonth(LocalDate date, Connection connection) {
        YearMonth tempMonth = YearMonth.of(date.getYear(), date.getMonthValue());
        LocalDate month = tempMonth.atEndOfMonth();
        ArchiveDao archiveDao = new ArchiveDao(connection);
        return archiveDao.findEntryForMonth(month);
    }
}
