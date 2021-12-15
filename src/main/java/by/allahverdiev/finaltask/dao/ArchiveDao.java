package by.allahverdiev.finaltask.dao;

import by.allahverdiev.finaltask.entity.Archive;
import by.allahverdiev.finaltask.entity.Product;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface ArchiveDao extends Dao {

    List<Archive> findEntryForMonth(LocalDate date);

    void createArchiveEntry(LocalDate date, Map<Product, Integer> map);

    boolean isArchiveEntryExist(LocalDate date);

    List<Archive> findLastArchiveEntry(LocalDate date) throws RegulationException;

    boolean deleteArchiveEntry(Date date);
}
