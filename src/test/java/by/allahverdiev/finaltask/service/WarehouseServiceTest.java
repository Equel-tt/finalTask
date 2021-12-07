package by.allahverdiev.finaltask.service;

import by.allahverdiev.finaltask.dao.DaoFactoryTest;
import by.allahverdiev.finaltask.dao.RegulationException;
import by.allahverdiev.finaltask.dao.testDatabaseConnection.ConnectionCreator;
import by.allahverdiev.finaltask.entity.Arrival;
import by.allahverdiev.finaltask.entity.Entity;
import by.allahverdiev.finaltask.entity.Product;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.Map.entry;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WarehouseServiceTest {
    Connection connection = ConnectionCreator.createConnection();
    WarehouseService serviceTest = new WarehouseService(DaoFactoryTest.getInstance());
    static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    WarehouseServiceTest() throws SQLException {
    }

    private static Stream<Arguments> correctId() {
        return Stream.of(
                Arguments.of(1, new Product(1)),
                Arguments.of(2, new Product(2))
        );
    }

    private static Stream<Arguments> incorrectId() {
        return Stream.of(
                Arguments.of(0),
                Arguments.of(3)
        );
    }

    private static Stream<Arguments> correctName() {
        return Stream.of(
                Arguments.of("ВТУЛКА 4040", new Product(1, "ВТУЛКА 4040")),
                Arguments.of("ГАЙКА М20х1.5", new Product(2, "ГАЙКА М20х1.5"))
        );
    }

    private static Stream<Arguments> incorrectName() {
        return Stream.of(
                Arguments.of(""),
                Arguments.of("ВТУЛКА 4040 "),
                Arguments.of("ВТУЛКА 4030")
        );
    }

    private static Stream<Arguments> currentInDate() {
        return Stream.of(
                Arguments.of(LocalDate.parse("2021-02-13"), Map.ofEntries(
                        entry(1, 50),
                        entry(2, 300))),
                Arguments.of(LocalDate.parse("2021-02-27"), Map.ofEntries(
                        entry(1, 0),
                        entry(2, 0))),
                Arguments.of(LocalDate.parse("2021-03-13"), Map.ofEntries(
                        entry(1, 50),
                        entry(2, 100)))
        );
    }

    private static Stream<Arguments> arrivalDate() {
        return Stream.of(
                Arguments.of("2021-02-01", "5555555"),
                Arguments.of("2021-02-12", "1111111"),
                Arguments.of("2021-03-01", "7777777")
        );
    }

    @ParameterizedTest
    @MethodSource("correctId")
    void findProductByIdCorrect(int id, Product product) {
        Entity exp = serviceTest.findProductById(id, connection);
        assertEquals(exp, product);
    }

    @ParameterizedTest
    @MethodSource("incorrectId")
    void findProductByIdIncorrect(int id) {
        assertThrows(NullPointerException.class, () -> {
            serviceTest.findProductById(id, connection);
        });
    }

    @ParameterizedTest
    @MethodSource("correctName")
    void findProductByNameCorrect(String name, Product product) {
        Entity exp = serviceTest.findProductByName(name, connection);
        assertEquals(((Product) exp).getName(), product.getName());
    }

    @ParameterizedTest
    @MethodSource("incorrectName")
    void findProductByNameIncorrect(String name) {
        assertThrows(NullPointerException.class, () -> {
            serviceTest.findProductByName(name, connection);
        });
    }

    @ParameterizedTest
    @MethodSource("currentInDate")
    void findAllProductsCountInCurrentDate(LocalDate date, Map<Integer, Integer> result) throws RegulationException {
        Map<Product, Integer> exp = serviceTest.findAllProductsCountInCurrentDate(date, connection);
        for (Map.Entry<Product, Integer> entry : exp.entrySet()) {
            int expValue = result.get(entry.getKey().getId());
            assertEquals(entry.getValue(), expValue);
        }
    }

    @ParameterizedTest
    @MethodSource("arrivalDate")
    void findArrivalInDate(String date, String exp) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date tempDate = format.parse(date);
        List<Arrival> act = serviceTest.findArrivalInDate(tempDate, connection);
        assertEquals(act.get(0).getDocument(), exp);
    }

//    @Test
//    void addArrivalEntry() {
//    }
}