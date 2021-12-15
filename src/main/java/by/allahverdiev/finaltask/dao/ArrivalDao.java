package by.allahverdiev.finaltask.dao;

import by.allahverdiev.finaltask.entity.Arrival;
import by.allahverdiev.finaltask.entity.Product;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface ArrivalDao extends Dao {

    List<Arrival> findAllInTimePeriod(LocalDate startDate, LocalDate endDate);

    List<Arrival> findByProductId(int id);

    List<Arrival> findByProductIdInTimePeriod(int id, LocalDate start, LocalDate end);

    int countOfProductInTimePeriod(Product product, LocalDate startDate, LocalDate endDate);

    void createArrivalEntry(String doc, int count, Date date, int productId, double price, int userId) throws SQLException;

    List<Arrival> findArrivalsInCurrentDate(Date date);

    int deleteEntityByKeys(String doc, int productId);
}
