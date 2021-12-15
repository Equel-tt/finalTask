package by.allahverdiev.finaltask.dao;

import by.allahverdiev.finaltask.entity.Consumption;

import java.time.LocalDate;
import java.util.List;

public interface ConsumptionDao extends Dao {

    List<Consumption> findAllInTimePeriod(LocalDate startDate, LocalDate endDate);

    List<Consumption> findByProductInTimePeriod(int productId, LocalDate startDate, LocalDate endDate);
}
