package by.allahverdiev.finaltask.service;

import by.allahverdiev.finaltask.dao.DaoFactoryTest;
import by.allahverdiev.finaltask.dao.RegulationException;
import by.allahverdiev.finaltask.dao.testDatabaseConnection.ConnectionCreator;
import by.allahverdiev.finaltask.entity.Archive;
import by.allahverdiev.finaltask.entity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BookkeepingServiceTest {
    Connection connection = ConnectionCreator.createConnection();
    BookkeepingService bookkeepingService = new BookkeepingService(DaoFactoryTest.getInstance());

    BookkeepingServiceTest() throws SQLException {
    }

    private static Stream<Arguments> findArchiveCorrectData() {
        return Stream.of(
                Arguments.of(LocalDate.parse("2021-02-23"), List.of(
                        new Archive(LocalDate.parse("2021-02-28"), new Product(1), 0),
                        new Archive(LocalDate.parse("2021-02-28"), new Product(2), 0)
                )),
                Arguments.of(LocalDate.parse("2021-01-03"), List.of(
                        new Archive(LocalDate.parse("2021-01-31"), new Product(1), 0),
                        new Archive(LocalDate.parse("2021-01-31"), new Product(2), 0)
                ))
        );
    }

    private static Stream<Arguments> findArchiveIncorrectData() {
        return Stream.of(
                Arguments.of(LocalDate.parse("2022-03-23")),
                Arguments.of(LocalDate.parse("2021-05-01"))
        );
    }

    private static Stream<Arguments> createArchiveEntryCorrectData() {
        return Stream.of(
                Arguments.of(LocalDate.parse("2021-03-23")),
                Arguments.of(LocalDate.parse("2021-04-01"))
        );
    }

    private static Stream<Arguments> createArchiveEntryNoPreviousEntry() {
        return Stream.of(
                Arguments.of(LocalDate.parse("2023-03-23")),
                Arguments.of(LocalDate.parse("2021-06-01"))
        );
    }

    private static Stream<Arguments> createArchiveEntryExistEntry() {
        return Stream.of(
                Arguments.of(LocalDate.parse("2021-01-23")),
                Arguments.of(LocalDate.parse("2021-02-01"))
        );
    }

    private static Stream<Arguments> deleteArchiveEntryCorrectData() {
        return Stream.of(
                Arguments.of(LocalDate.parse("2021-03-23")),
                Arguments.of(LocalDate.parse("2021-04-01"))
        );
    }

    private static Stream<Arguments> deleteArchiveEntryIncorrectData() {
        return Stream.of(
                Arguments.of(LocalDate.parse("2021-05-23")),
                Arguments.of(LocalDate.parse("2022-01-01"))
        );
    }

    @ParameterizedTest
    @MethodSource("createArchiveEntryCorrectData")
    void createArchiveEntryCorrectData(LocalDate date) throws RegulationException, SQLException {
        assertTrue(bookkeepingService.createArchiveEntry(date, connection));
    }

    @ParameterizedTest
    @MethodSource("createArchiveEntryNoPreviousEntry")
    void createArchiveEntryNoPreviousEntry(LocalDate date) {
        assertThrows(RegulationException.class, () -> {
            bookkeepingService.createArchiveEntry(date, connection);
        });
    }

    @ParameterizedTest
    @MethodSource("createArchiveEntryExistEntry")
    void createArchiveEntryExistEntry(LocalDate date) throws RegulationException, SQLException {
        assertFalse(bookkeepingService.createArchiveEntry(date, connection));
    }

    @ParameterizedTest
    @MethodSource("findArchiveCorrectData")
    void findArchiveEntryByMonthCorrectData(LocalDate date, List<Archive> exp) {
        List<Archive> act = bookkeepingService.findArchiveEntryByMonth(date, connection);
        Assertions.assertIterableEquals(exp, act);
    }

    @ParameterizedTest
    @MethodSource("findArchiveIncorrectData")
    void findArchiveEntryByMonthIncorrectData(LocalDate date) {
        List<Archive> act = bookkeepingService.findArchiveEntryByMonth(date, connection);
        for (Archive archive : act) {
            assertNull(archive);
        }
    }

    @Test
    void deleteArrivalEntry() {
    }

    @ParameterizedTest
    @MethodSource("deleteArchiveEntryCorrectData")
    void deleteArchiveEntry(LocalDate date) throws SQLException {
        assertTrue(bookkeepingService.deleteArchiveEntry(connection, date));
    }

    @ParameterizedTest
    @MethodSource("deleteArchiveEntryIncorrectData")
    void deleteArchiveEntryIncorrectData(LocalDate date) throws SQLException {
        assertFalse(bookkeepingService.deleteArchiveEntry(connection, date));
    }
}