package by.allahverdiev.finaltask.controller;

import by.allahverdiev.finaltask.dao.pool.ConnectionPool;
import by.allahverdiev.finaltask.dao.postgres.ArrivalDaoPg;
import by.allahverdiev.finaltask.dao.postgres.ProductDaoPg;
import by.allahverdiev.finaltask.dao.postgres.UserDaoPg;
import by.allahverdiev.finaltask.entity.Product;
import by.allahverdiev.finaltask.service.WarehouseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

public class Main {
    private static final Logger logger = LogManager.getLogger(ProductDaoPg.class);

    public static void main(String[] args) throws SQLException, IOException {
        ConnectionPool connection = ConnectionPool.getInstance();
        connection.init();
//        ProductDaoPg productDao = new ProductDaoPg(connection.getConnection());
        ProductDaoPg productDao = new ProductDaoPg(connection.getConnection());
        UserDaoPg userDao = new UserDaoPg(connection.getConnection());
        ArrivalDaoPg arrivalDao = new ArrivalDaoPg(connection.getConnection());
        WarehouseService service = new WarehouseService();

        List<Product> products = productDao.findAll();
//        Map<Product,Integer> map = service.findAllProductsCountInCurrentDate(LocalDate.of(2021,01,01),LocalDate.of(2021,01,31), connection.getConnection());
//        Map<Product,Integer> map = service.findAllProductsCountInCurrentDate(LocalDate.of(2021,02,01),LocalDate.of(2021,02,28), connection.getConnection());
//        Map<Product,Integer> map = service.findAllProductsCountInCurrentDate(LocalDate.of(2021,03,01),LocalDate.of(2021,03,31), connection.getConnection());
//        Map<Product, Integer> map = service.findAllProductsCountInCurrentDate(LocalDate.of(2021, 04, 01), LocalDate.of(2021, 04, 30), connection.getConnection());

//        FileWriter writer = new FileWriter("H:\\FinalTask\\product4.txt", false);
//        FileWriter writer2 = new FileWriter("H:\\FinalTask\\count4.txt", false);
//        for (Map.Entry<Product, Integer> entry : map.entrySet()) {
//            writer.write(entry.getKey().getId() + "\n");
//            writer2.write(entry.getValue() + "\n");
//        }
//        writer.close();
//        writer2.close();
        LocalDate date = LocalDate.of(2021, 02, 15);

        YearMonth mont = YearMonth.of(date.getYear(), date.getMonthValue());
        LocalDate startDate = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
        LocalDate endDate = mont.atEndOfMonth();
        System.out.println(startDate);
        System.out.println(endDate);
//        for (Entity product : products) {
//            System.out.println(product);
//        }

//        Entity product = productDao.findEntityById(1800);
//        userDao.update(((Product)product).getManager());
//        System.out.println(product);
    }
}



