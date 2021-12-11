package by.allahverdiev.finaltask.service;

import by.allahverdiev.finaltask.dao.DaoFactoryTest;
import by.allahverdiev.finaltask.dao.testDatabaseConnection.ConnectionCreator;
import by.allahverdiev.finaltask.entity.Department;
import by.allahverdiev.finaltask.entity.Need;
import by.allahverdiev.finaltask.entity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;


class SupplyServiceTest {
    Connection connection = ConnectionCreator.createConnection();
    SupplyService supplyService = new SupplyService(DaoFactoryTest.getInstance());

    SupplyServiceTest() throws SQLException {
    }

    private static Stream<Arguments> deficitData() {
        return Stream.of(
                Arguments.of(LocalDate.parse("2021-02-23"), new ArrayList<>(Arrays.asList(90, 0, 100, 10)), 1),
                Arguments.of(LocalDate.parse("2021-02-23"), new ArrayList<>(Arrays.asList(600, 0, 600, 0)), 2),
                Arguments.of(LocalDate.parse("2021-03-12"), new ArrayList<>(Arrays.asList(500, 0, 600, 100)), 2)
                //need, archive, arrival, deficit
        );
    }

    private static Stream<Arguments> needData() {
        return Stream.of(
                Arguments.of(LocalDate.parse("2021-02-10"), List.of(
                        new Need(LocalDate.parse("2021-02-01"), new Product(1), 90, new Department(1)),
                        new Need(LocalDate.parse("2021-02-01"), new Product(2), 600, new Department(1))
                )),
                Arguments.of(LocalDate.parse("2021-03-17"), List.of(
                        new Need(LocalDate.parse("2021-03-01"), new Product(1), 90, new Department(1)),
                        new Need(LocalDate.parse("2021-03-01"), new Product(2), 500, new Department(1))
                ))

                ////month, product, count, destination
        );
    }

    @ParameterizedTest
    @MethodSource("deficitData")
    void findDeficit(LocalDate date, ArrayList<Integer> exp, int id) {
        Map<Product, ArrayList<Integer>> act = supplyService.findDeficit(date, 1, connection);
        act.forEach((k, v) -> {
            if (k.getId() == id) {
                Assertions.assertIterableEquals(exp, v);
            }
        });
    }

    @ParameterizedTest
    @MethodSource("needData")
    void findNeedForCurrentMonth(LocalDate date, List<Need> exp) {
        List<Need> act = supplyService.findNeedForCurrentMonth(date, connection);
        Assertions.assertIterableEquals(exp, act);
    }

}