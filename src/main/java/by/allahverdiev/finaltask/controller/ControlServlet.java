package by.allahverdiev.finaltask.controller;

import by.allahverdiev.finaltask.controller.command.CommandController;
import by.allahverdiev.finaltask.service.InitializationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(name = "/control", value = "*.jsp")
//@WebServlet(urlPatterns = "/control")
public class ControlServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(ControlServlet.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    public void init() throws ServletException {
        super.init();
        InitializationService.getInstance().initialize();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        logger.info("start do get");
        buildRequest(request, response);
        logger.info("end do get");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("start do post");
        buildRequest(request, response);
        logger.info("end do post");
    }

    private void buildRequest(HttpServletRequest request, HttpServletResponse response) {
        try {
            CommandController control = new CommandController();
            control.executeTask(request);
            String way = (String) request.getAttribute("way");
            logger.info(way);
            switch (way) {
                case "redirect":
//                    response.sendRedirect(request.getHeader("referer"));
//                    response.sendRedirect("/WEB-INF/jsp" + request.getAttribute("destination"));
                    response.sendRedirect(request.getContextPath() + "/success.jsp");
                    break;
                case "directRedirect":
                    response.sendRedirect("/finalWebApp/login.jsp");
                    break;
                case "forward":
                    String destination = "/WEB-INF/jsp" + request.getAttribute("destination");
                    logger.info(destination);
                    getServletContext().getRequestDispatcher(destination).forward(request, response);
                    break;
            }
        } catch (ServletException | IOException e) {
            logger.info(e.getMessage());
        }
    }

    public void destroy() {
    }
}