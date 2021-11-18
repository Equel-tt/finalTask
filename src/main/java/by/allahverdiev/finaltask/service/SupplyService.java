package by.allahverdiev.finaltask.service;

import by.allahverdiev.finaltask.dao.postgres.ArrivalDaoPg;
import by.allahverdiev.finaltask.dao.postgres.ConsumptionDaoPg;
import by.allahverdiev.finaltask.dao.postgres.NeedDaoPg;
import by.allahverdiev.finaltask.dao.postgres.ProductDaoPg;
import by.allahverdiev.finaltask.entity.Arrival;
import by.allahverdiev.finaltask.entity.Consumption;
import by.allahverdiev.finaltask.entity.Need;
import by.allahverdiev.finaltask.entity.Product;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SupplyService implements Service {

    public Map<Product, ArrayList<Integer>> findDeficit(LocalDate start, LocalDate end, Connection connection) {
        Map<Product, ArrayList<Integer>> result = new HashMap<>();
        ProductDaoPg productDao = new ProductDaoPg(connection);
        List<Product> productList = productDao.findAll();
        ArrivalDaoPg arrivalDao = new ArrivalDaoPg(connection);
        List<Arrival> arrivalList = arrivalDao.findAllInTimePeriod(start, end);
        ConsumptionDaoPg consumptionDao = new ConsumptionDaoPg(connection);
        List<Consumption> consumptionsList = consumptionDao.findAllInTimePeriod(start, end);

        return result;
    }

    public List<Need> findAllNeed(Connection connection) {
        NeedDaoPg needDao = new NeedDaoPg(connection);
        ProductDaoPg productDao = new ProductDaoPg(connection);
        List<Need> result = needDao.findAll();
        for (Need need : result) {
            productDao.update(need.getProduct());
        }
        return result;
    }

    public List<Need> findNeedForCurrentMonth(LocalDate date, Connection connection) {
        NeedDaoPg needDao = new NeedDaoPg(connection);
        ProductDaoPg productDao = new ProductDaoPg(connection);
        List<Need> result = needDao.findNeedForCurrentMonth(date);
        for (Need need : result) {
            productDao.update(need.getProduct());
        }
        return result;
    }
}
