package by.allahverdiev.finaltask.service;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    public static ServiceFactory getInstance() {
        return instance;
    }

    private final WarehouseService warehouseService = new WarehouseService();
    private final SupplyService supplyService = new SupplyService();
    private final BookkeepingService bookkeepingService = new BookkeepingService();
    private final UserService userService = new UserService();

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
