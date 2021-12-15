package by.allahverdiev.finaltask.controller.command;

import java.util.HashMap;
import java.util.Map;

public class DestinationMap {
    private final Map<String, String> destination = new HashMap<>();

    public DestinationMap() {
        destination.put("AddArchiveEntryCommand", "/homePageBookkeeping.jsp");
        destination.put("FindProductByIdCommand", "/productOverview.jsp");
        destination.put("FindProductByNameCommand", "/productOverview.jsp");
        destination.put("FindNeedForMonthCommand", "/needOverview.jsp");
        destination.put("FindArchiveEntryByMonthCommand", "/archiveOverview.jsp");
        destination.put("FindAllProdInCurrDateCommand", "/warehouseOverview.jsp");
        destination.put("FindAllNeedCommand", "/needOverview.jsp");
        destination.put("LoginCommand", "/index.jsp");
        destination.put("LogoutCommand", "/login.jsp");
        destination.put("ProductSearchCommand", "/productSearch.jsp");
        destination.put("FindDeficitCommand", "/deficitOverview.jsp");
        destination.put("AddArrivalEntryCommand", "/homePageWarehouse.jsp");
        destination.put("FindArrivalsInDateCommand", "/arrivalOverview.jsp");
        destination.put("DeleteArrivalEntryCommand", "/homePageBookkeeping.jsp");

    }

    public String getDestination(String key) {
        String pageName = key.substring(key.lastIndexOf('.') + 1).trim();
        return destination.get(pageName);
    }
}
