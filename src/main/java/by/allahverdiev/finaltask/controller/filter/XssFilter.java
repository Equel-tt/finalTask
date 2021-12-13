package by.allahverdiev.finaltask.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class XssFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Map<String, String[]> values = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : values.entrySet()) {
            for (String value : entry.getValue()) {
                String safeValue = value.replace("&", "&amp;").replace("<", "&lt;");
                request.setAttribute(entry.getKey(), safeValue);
            }

        }
        chain.doFilter(request, response);
    }
}
