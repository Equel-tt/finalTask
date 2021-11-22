package by.allahverdiev.finaltask.controller.filter;

import by.allahverdiev.finaltask.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SecurityFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(SecurityFilter.class);
    private final Map<String, List<Integer>> repository = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) {
        repository.put("FIND_PRODUCT_BY_ID", List.of(2, 3));
        repository.put("FIND_PRODUCT_BY_NAME", List.of(2, 3));
        repository.put("FIND_NEED_FOR_MONTH", List.of(2));
        repository.put("FindDeficitCommand", List.of(2));
        repository.put("FIND_ARCHIVE_ENTRY_BY_MONTH", List.of(1));
        repository.put("FIND_ALL_PRODUCTS_IN_CURRENT_DATE", List.of(2, 3));
        repository.put("FIND_ALL_NEED", List.of(2));
        repository.put("FIND_ALL_ARCHIVE", List.of(1));
        repository.put("ADD_ARCHIVE_ENTRY", List.of(1));
        repository.put("LOGOUT", List.of(1, 2, 3, 4));
        repository.put("LOGIN", List.of(1, 2, 3, 4));
        repository.put("SEARCH", List.of(1, 2, 3, 4));
        repository.put("FIND_DEFICIT", List.of(2));
        repository.put("TEST", List.of(1, 2, 3, 4));

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String commandName = request.getParameter("command");
        logger.info(commandName);
        HttpSession session = request.getSession(false);
        User user;
        boolean everythingIsAllowed = false;
        if (session != null) {
            user = (User) session.getAttribute("user");
            if (user != null && repository.get(commandName).contains(user.getRole())) {
                everythingIsAllowed = true;
            }
        }
        if (commandName.equals("LOGIN")) {
            everythingIsAllowed = true;
        }
        if (everythingIsAllowed) {
            logger.debug(everythingIsAllowed + " фильтр до сервлета");
            chain.doFilter(request, response);
            logger.debug(" фильтр после сервлета");
        } else {
            response.sendRedirect(request.getContextPath());
        }
    }

    @Override
    public void destroy() {
    }
}
