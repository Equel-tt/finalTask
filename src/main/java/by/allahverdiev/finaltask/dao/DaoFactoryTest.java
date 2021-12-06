package by.allahverdiev.finaltask.dao;

import by.allahverdiev.finaltask.dao.postgresTest.*;

import java.sql.Connection;

public class DaoFactoryTest implements DaoFactory {
    private static final DaoFactoryTest instance = new DaoFactoryTest();

    public static DaoFactoryTest getInstance() {
        return instance;
    }

    private final ArchiveDaoTest archiveDao = new ArchiveDaoTest();
    private final ArrivalDaoTest arrivalDao = new ArrivalDaoTest();
    private final ConsumptionDaoTest consumptionDao = new ConsumptionDaoTest();
    private final NeedDaoTest needDao = new NeedDaoTest();
    private final ProductDaoTest productDao = new ProductDaoTest();
    private final UserDaoTest userDao = new UserDaoTest();

    public ArchiveDaoTest getArchiveDao(Connection connection) {
        archiveDao.setConnection(connection);
        return archiveDao;
    }

    public ArrivalDaoTest getArrivalDao(Connection connection) {
        arrivalDao.setConnection(connection);
        return arrivalDao;
    }

    public ConsumptionDaoTest getConsumptionDao(Connection connection) {
        consumptionDao.setConnection(connection);
        return consumptionDao;
    }

    public NeedDaoTest getNeedDao(Connection connection) {
        needDao.setConnection(connection);
        return needDao;
    }

    public ProductDaoTest getProductDao(Connection connection) {
        productDao.setConnection(connection);
        return productDao;
    }

    public UserDaoTest getUserDao(Connection connection) {
        userDao.setConnection(connection);
        return userDao;
    }
}
