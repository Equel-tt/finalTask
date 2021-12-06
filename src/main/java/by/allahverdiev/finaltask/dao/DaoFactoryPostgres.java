package by.allahverdiev.finaltask.dao;

import by.allahverdiev.finaltask.dao.postgres.*;

import java.sql.Connection;

public class DaoFactoryPostgres implements DaoFactory {
    private static final DaoFactoryPostgres instance = new DaoFactoryPostgres();

    public static DaoFactoryPostgres getInstance() {
        return instance;
    }

    private final ArchiveDaoPg archiveDao = new ArchiveDaoPg();
    private final ArrivalDaoPg arrivalDao = new ArrivalDaoPg();
    private final ConsumptionDaoPg consumptionDao = new ConsumptionDaoPg();
    private final NeedDaoPg needDao = new NeedDaoPg();
    private final ProductDaoPg productDao = new ProductDaoPg();
    private final UserDaoPg userDao = new UserDaoPg();

    public ArchiveDaoPg getArchiveDao(Connection connection) {
        archiveDao.setConnection(connection);
        return archiveDao;
    }

    public ArrivalDaoPg getArrivalDao(Connection connection) {
        arrivalDao.setConnection(connection);
        return arrivalDao;
    }

    public ConsumptionDaoPg getConsumptionDao(Connection connection) {
        consumptionDao.setConnection(connection);
        return consumptionDao;
    }

    public NeedDaoPg getNeedDao(Connection connection) {
        needDao.setConnection(connection);
        return needDao;
    }

    public ProductDaoPg getProductDao(Connection connection) {
        productDao.setConnection(connection);
        return productDao;
    }

    public UserDaoPg getUserDao(Connection connection) {
        userDao.setConnection(connection);
        return userDao;
    }
}
