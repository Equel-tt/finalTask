package by.allahverdiev.finaltask.service;

import by.allahverdiev.finaltask.dao.DaoFactoryPostgres;
import by.allahverdiev.finaltask.dao.postgres.UserDaoPg;
import by.allahverdiev.finaltask.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;

public class UserService implements Service {
    private static final Logger logger = LogManager.getLogger(UserService.class);
    private final DaoFactoryPostgres factory;

    public UserService(DaoFactoryPostgres factory) {
        this.factory = factory;
    }
//    DaoFactory factory = DaoFactory.getInstance();

    public User login(Connection connection, String login, String password) throws AccessException {
        UserDaoPg userDaoPg = factory.getUserDao(connection);
        User user = userDaoPg.login(login, password);
        if (user.getName() == null) {
            throw new AccessException("Неверный логин/пароль");
        }
        return user;
    }
}
