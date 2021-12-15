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

    private final DaoFactory factory;

    public SupplyService(DaoFactory factory) {
        this.factory = factory;
    }

    /**
     * calculates the available quantity of materials and compares with the requirement for the selected month
     * use private method {@link #getArchiveCount(List, Product, int)}
     * use private method {@link #getArrivalCount(List, Product, int)}
     * use private method {@link #getNeedCount(List, Product, int)}
     */
    public Map<Product, ArrayList<Integer>> findDeficit(LocalDate date, int userId, Connection connection) {
        YearMonth tempMonth = YearMonth.of(date.getYear(), date.getMonthValue());
        LocalDate startOfMonth = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
        LocalDate endOfMonth = tempMonth.atEndOfMonth();

        ProductDaoPg productDao = factory.getProductDao(connection);
        List<Product> productList = productDao.findAllByManagerRole(userId);
        ArrivalDaoPg arrivalDao = factory.getArrivalDao(connection);
        List<Arrival> arrivalList = arrivalDao.findAllInTimePeriod(startOfMonth, endOfMonth);
        ArchiveDaoPg archiveDao = factory.getArchiveDao(connection);
        List<Archive> archiveList = archiveDao.findEntryForMonth(date);
        NeedDaoPg needDao = factory.getNeedDao(connection);
        List<Need> needList = needDao.findNeedForCurrentMonth(date);

        Map<Product, ArrayList<Integer>> result = new HashMap<>();
        for (Product product : productList) {
            int needCount = 0;
            int arrivalCount = 0;
            int archiveCount = 0;
            int deficit = 0;
            needCount = getNeedCount(needList, product, needCount);
            arrivalCount = getArrivalCount(arrivalList, product, arrivalCount);
            archiveCount = getArchiveCount(archiveList, product, archiveCount);
            deficit = archiveCount + arrivalCount - needCount;
            result.put(product, new ArrayList(Arrays.asList(needCount, archiveCount, arrivalCount, deficit)));
        }
        return result;
    }

    private int getArchiveCount(List<Archive> archiveList, Product product, int archiveCount) {
        for (Archive archive : archiveList) {
            if (archive.getProduct().getId() == product.getId()) {
                archiveCount += archive.getCount();
            }
        }
        return archiveCount;
    }

    private int getArrivalCount(List<Arrival> arrivalList, Product product, int arrivalCount) {
        for (Arrival arrival : arrivalList) {
            if (arrival.getProduct().getId() == product.getId()) {
                arrivalCount += arrival.getCount();
            }
        }
        return arrivalCount;
    }

    private int getNeedCount(List<Need> needList, Product product, int needCount) {
        for (Need need : needList) {
            if (need.getProduct().getId() == product.getId()) {
                needCount += need.getCount();
            }
        }
        return needCount;
    }

    /**
     * shows all records with need
     */
    public List<Need> findAllNeed(Connection connection) {
        NeedDaoPg needDao = factory.getNeedDao(connection);
        ProductDaoPg productDao = factory.getProductDao(connection);
        List<Need> result = needDao.findAll();
        for (Need need : result) {
            productDao.update(need.getProduct());
        }
        return result;
    }

    /**
     * shows record with need for the selected month
     */
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
