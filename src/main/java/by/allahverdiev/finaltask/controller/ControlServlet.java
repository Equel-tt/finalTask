package by.allahverdiev.finaltask.controller;

import by.allahverdiev.finaltask.dao.pool.ConnectionPool;
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
        ConnectionPool.getInstance().init();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
//        try {
//            ProductDaoPg daoPg = new ProductDaoPg(ConnectionCreator.createConnection());
//            List<Product> set = daoPg.findAll();
//            request.setAttribute("products", set);
//            getServletContext().getRequestDispatcher("/result.jsp").forward(request, response);
//        } catch (SQLException | ServletException | IOException e) {
//            e.printStackTrace();
//        }
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
            //работает
//            ProductDaoPg daoPg = new ProductDaoPg(ConnectionPool.getInstance().getConnection());
//            UserDaoPg userDaoPg = new UserDaoPg(ConnectionPool.getInstance().getConnection());
//            Product product = daoPg.findEntityById(Integer.parseInt(request.getParameter("productId")));
//            userDaoPg.update(product.getManager());
//            logger.info(request.getParameter("productId"));
//            logger.info(product.getName());
//            List<Product> list = new ArrayList<>();
//            list.add(product);
//            request.setAttribute("products", list);
//            getServletContext().getRequestDispatcher(request.getParameter("destination")).forward(request, response);

            control.executeTask(request);
            getServletContext().getRequestDispatcher(request.getParameter("destination")).forward(request, response);

        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
    }
}