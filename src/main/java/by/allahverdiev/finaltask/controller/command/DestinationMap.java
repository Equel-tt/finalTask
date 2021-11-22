package by.allahverdiev.finaltask.controller.command;

import java.util.HashMap;
import java.util.Map;

public class DestinationMap {
    private final Map<String, String> destination = new HashMap<>();

    public DestinationMap() {
        destination.put("AddArchiveEntryCommand", "/archive.jsp");
        destination.put("FindProductByIdCommand", "/productOverview.jsp");
        destination.put("FindProductByNameCommand", "/productOverview.jsp");
        destination.put("FindNeedForMonthCommand", "/needOverview.jsp");
        destination.put("FindArchiveEntryByMonthCommand", "/archive.jsp");
        destination.put("FindAllProdInCurrDateCommand", "/warehouse.jsp");
        destination.put("FindAllNeedCommand", "/needOverview.jsp");
        destination.put("FindAllArchiveCommand", "/archive.jsp");
        destination.put("LoginCommand", "/index.jsp");
        destination.put("LogoutCommand", "/login.jsp");
        destination.put("ProductSearchCommand", "/productSearch.jsp");
        destination.put("FindDeficitCommand", "/deficitOverview.jsp");


    }

    public String getDestination(String key) {
        String pageName = key.substring(key.lastIndexOf('.') + 1).trim();
        return destination.get(pageName);
    }
}
