package by.allahverdiev.finaltask.service;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    public static ServiceFactory getInstance() {
        return instance;
    }

    private final WarehouseService warehouseService = new WarehouseService();
    private final SupplyService supplyService = new SupplyService();
    private final BookkeepingService bookkeepingService = new BookkeepingService();

    public WarehouseService getWarehouseService() {
        return warehouseService;
    }

    public SupplyService getSupplyService() {
        return supplyService;
    }

    public BookkeepingService getBookkeepingService() {
        return bookkeepingService;
    }
}
