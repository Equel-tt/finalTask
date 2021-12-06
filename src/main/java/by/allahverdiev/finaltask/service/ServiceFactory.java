package by.allahverdiev.finaltask.service;

import by.allahverdiev.finaltask.dao.DaoFactoryPostgres;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    public static ServiceFactory getInstance() {
        return instance;
    }

    private final WarehouseService warehouseService = new WarehouseService(DaoFactoryPostgres.getInstance());
    private final SupplyService supplyService = new SupplyService(DaoFactoryPostgres.getInstance());
    private final BookkeepingService bookkeepingService = new BookkeepingService(DaoFactoryPostgres.getInstance());
    private final UserService userService = new UserService(DaoFactoryPostgres.getInstance());

    public WarehouseService getWarehouseService() {
        return warehouseService;
    }

    public SupplyService getSupplyService() {
        return supplyService;
    }

    public BookkeepingService getBookkeepingService() {
        return bookkeepingService;
    }

    public UserService getUserService() {
        return userService;
    }
}
