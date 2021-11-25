package by.allahverdiev.finaltask.service;

import by.allahverdiev.finaltask.dao.DaoFactory;
import by.allahverdiev.finaltask.dao.postgres.ArchiveDaoPg;
import by.allahverdiev.finaltask.dao.postgres.ArrivalDaoPg;
import by.allahverdiev.finaltask.dao.postgres.NeedDaoPg;
import by.allahverdiev.finaltask.dao.postgres.ProductDaoPg;
import by.allahverdiev.finaltask.entity.Archive;
import by.allahverdiev.finaltask.entity.Arrival;
import by.allahverdiev.finaltask.entity.Need;
import by.allahverdiev.finaltask.entity.Product;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;

public class SupplyService implements Service {
    DaoFactory factory = DaoFactory.getInstance();

    public Map<Product, ArrayList<Integer>> findDeficit(LocalDate date, int userId, Connection connection) {
        YearMonth tempMonth = YearMonth.of(date.getYear(), date.getMonthValue());
        LocalDate startOfMonth = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
        LocalDate endOfMonth = tempMonth.atEndOfMonth();

        Map<Product, ArrayList<Integer>> result = new HashMap<>();
        //TODO conection auto commit
        ProductDaoPg productDao = factory.getProductDao(connection);
        List<Product> productList = productDao.findAllByManagerRole(userId);
        ArrivalDaoPg arrivalDao = factory.getArrivalDao(connection);
        List<Arrival> arrivalList = arrivalDao.findAllInTimePeriod(startOfMonth, endOfMonth);
        ArchiveDaoPg archiveDao = factory.getArchiveDao(connection);
        List<Archive> archiveList = archiveDao.findEntryForMonth(date);
        NeedDaoPg needDao = factory.getNeedDao(connection);
        List<Need> needList = needDao.findNeedForCurrentMonth(date);
        for (Product product : productList) {
            int needCount = 0;
            int arrivalCount = 0;
            int archiveCount = 0;
            int deficit = 0;
            for (Need need : needList) {
                if (need.getProduct().getId() == product.getId()) {
                    needCount += need.getCount();
                }
            }
            for (Arrival arrival : arrivalList) {
                if (arrival.getProduct().getId() == product.getId()) {
                    arrivalCount += arrival.getCount();
                }
            }
            for (Archive archive : archiveList) {
                if (archive.getProduct().getId() == product.getId()) {
                    archiveCount += archive.getCount();
                }
            }
            deficit = archiveCount + arrivalCount - needCount;
            result.put(product, new ArrayList(Arrays.asList(needCount, archiveCount, arrivalCount, deficit)));
        }
        return result;
    }

    public List<Need> findAllNeed(Connection connection) {
        NeedDaoPg needDao = factory.getNeedDao(connection);
        ProductDaoPg productDao = factory.getProductDao(connection);
        List<Need> result = needDao.findAll();
        for (Need need : result) {
            productDao.update(need.getProduct());
        }
        return result;
    }

    public List<Need> findNeedForCurrentMonth(LocalDate date, Connection connection) {
        NeedDaoPg needDao = factory.getNeedDao(connection);
        ProductDaoPg productDao = factory.getProductDao(connection);
        List<Need> result = needDao.findNeedForCurrentMonth(date);
        for (Need need : result) {
            productDao.update(need.getProduct());
        }
        return result;
    }
}
