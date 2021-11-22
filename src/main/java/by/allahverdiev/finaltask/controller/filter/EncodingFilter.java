package by.allahverdiev.finaltask.controller.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EncodingFilter implements Filter {
	private static final Logger logger = LogManager.getLogger(EncodingFilter.class);
	@Override
	public void init(FilterConfig filterConfig) {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setCharacterEncoding("UTF-8");

        httpResponse.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
        httpResponse.setHeader("Pragma", "no-cache"); //HTTP 1.0
        httpResponse.setDateHeader("Expires", 0); //prevents caching at the proxy server

        chain.doFilter(request, response);
    }

	@Override
	public void destroy() {
	}
}
