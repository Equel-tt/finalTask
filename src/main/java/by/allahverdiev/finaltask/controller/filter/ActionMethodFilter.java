package by.allahverdiev.finaltask.controller.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActionMethodFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(ActionMethodFilter.class);
    private final Map<String, List<String>> repository = new HashMap<>();


    @Override
    public void init(FilterConfig filterConfig) {
        repository.put("POST", List.of("FIND_PRODUCT_BY_ID", "FIND_PRODUCT_BY_NAME", "FIND_NEED_FOR_MONTH",
                "FindDeficitCommand", "FIND_ARCHIVE_ENTRY_BY_MONTH", "FIND_ALL_PRODUCTS_IN_CURRENT_DATE",
                "FIND_ALL_NEED", "FIND_ALL_ARCHIVE", "ADD_ARCHIVE_ENTRY", "LOGOUT", "LOGIN", "SEARCH",
                "FIND_DEFICIT", "CHANGE_LANGUAGE", "FIND_ARRIVALS_IN_CURRENT_DATE", "ADD_ARRIVAL_ENTRY", "HOME"));
        repository.put("GET", List.of(""));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String commandName = request.getParameter("command");
        String methodName = request.getMethod();
        logger.info(methodName);

        if (!repository.get(methodName).contains(commandName)) {
            request.setAttribute("command", "REFRESH");
        }
        chain.doFilter(request, response);
    }

}
