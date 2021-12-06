package by.allahverdiev.finaltask.dao;

import by.allahverdiev.finaltask.dao.postgres.*;

import java.sql.Connection;

public interface DaoFactory {
    static DaoFactory getInstance() {
        return null;
    }

    ArchiveDaoPg getArchiveDao(Connection connection);

    ArrivalDaoPg getArrivalDao(Connection connection);

    ConsumptionDaoPg getConsumptionDao(Connection connection);

    NeedDaoPg getNeedDao(Connection connection);

    ProductDaoPg getProductDao(Connection connection);

    UserDaoPg getUserDao(Connection connection);
}
