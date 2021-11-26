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
import java.util.UUID;

public class SecurityFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(SecurityFilter.class);
    private final Map<String, List<Integer>> repository = new HashMap<>();
    private static final Map<UUID, String> idList = new HashMap<>();

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
        repository.put("CHANGE_LANGUAGE", List.of(1, 2, 3, 4));
        repository.put("FIND_ARRIVALS_IN_CURRENT_DATE", List.of(3));
        repository.put("ADD_ARRIVAL_ENTRY", List.of(3));
        repository.put("HOME", List.of(1, 2, 3, 4));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String commandName = request.getParameter("command");
        logger.info(commandName);
        logger.debug(request.getSession(false).getAttribute("uid"));
        HttpSession session = request.getSession(false);
        User user;
        logger.debug(idList.entrySet().toString());
        boolean everythingIsAllowed = false;

        if (session.getAttribute("uid") != null) {
            if (!idList.keySet().contains((UUID) session.getAttribute("uid"))) {
                user = (User) session.getAttribute("user");
                if ((user != null
                        && repository.get(commandName).contains(user.getRole()))
                        || (user == null && commandName.equals("LOGIN"))) {
                    everythingIsAllowed = true;
                    request.setAttribute("command", request.getParameter("command"));
                }
            } else if (!idList.get((UUID) session.getAttribute("uid")).equals(commandName)) {
                everythingIsAllowed = true;
                request.setAttribute("command", request.getParameter("command"));
            } else {
                everythingIsAllowed = true;
                request.setAttribute("command", "REFRESH");
            }
        }

        if (((session.getAttribute("uid") == null) && (commandName.equals("LOGIN"))) || commandName.equals("CHANGE_LANGUAGE") || commandName.equals("HOME")) {
            everythingIsAllowed = true;
            request.setAttribute("command", request.getParameter("command"));
        }

        if (everythingIsAllowed) {
            chain.doFilter(request, response);
            try {
                if (session.getAttribute("uid") != null) {
                    idList.put((UUID) session.getAttribute("uid"), commandName);
                }
            } catch (IllegalStateException ex) {
                logger.info(ex.getMessage());
            }
            logger.debug(idList.entrySet().toString());
        } else {
            response.sendRedirect(request.getContextPath());
        }
    }

    @Override
    public void destroy() {
    }
}
