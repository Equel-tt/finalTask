package by.allahverdiev.finaltask.service;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    public static ServiceFactory getInstance() {
        return instance;
    }

    private final WarehouseService warehouseService = new WarehouseService();

    public WarehouseService getWarehouseService() {
        return warehouseService;
    }
}
