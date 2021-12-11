package by.allahverdiev.finaltask.service;

import by.allahverdiev.finaltask.dao.DaoFactoryTest;
import by.allahverdiev.finaltask.dao.testDatabaseConnection.ConnectionCreator;
import by.allahverdiev.finaltask.entity.User;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserServiceTest {
    Connection connection = ConnectionCreator.createConnection();
    UserService serviceTest = new UserService(DaoFactoryTest.getInstance());

    UserServiceTest() throws SQLException {
    }

    private static Stream<Arguments> loginCorrectData() {
        return Stream.of(
                Arguments.of("petrKoval", "6666", new User(1, "Петр", "Коваль", "Илларионович", 2, "Снабжение")),
                Arguments.of("jannaVasiluk", "1111", new User(2, "Жанна", "Василюк", "Геннадьевна", 1, "Бухгалтер")),
                Arguments.of("larisaBortnik", "3333", new User(3, "Лариса", "Бортник", "Антоновна", 3, "Кладовщик"))
        );
    }

    private static Stream<Arguments> loginIncorrectData() {
        return Stream.of(
                Arguments.of("petrKoval", "1234", new User(1, "Петр", "Коваль", "Илларионович", 2, "Снабжение")),
                Arguments.of("jannaVasiluk", "111", new User(2, "Жанна", "Василюк", "Геннадьевна", 1, "Бухгалтер"))
        );
    }

    @ParameterizedTest
    @MethodSource("loginCorrectData")
    void login(String login, String password, User exp) throws AccessException {
        User act = serviceTest.login(connection, login, password);
        assertEquals(exp, act);
    }

    @ParameterizedTest
    @MethodSource("loginIncorrectData")
    void loginIncorrect(String login, String password) {
        assertThrows(AccessException.class, () -> {
            serviceTest.login(connection, login, password);
        });
    }

}