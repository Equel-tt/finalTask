package by.allahverdiev.finaltask.dao;

import by.allahverdiev.finaltask.entity.Need;

import java.time.LocalDate;
import java.util.List;

public interface NeedDao extends Dao {

    List<Need> findNeedForCurrentMonth(LocalDate date);
}
