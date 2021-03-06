package by.allahverdiev.finaltask.service;

import by.allahverdiev.finaltask.dao.DaoFactory;
import by.allahverdiev.finaltask.dao.postgres.UserDaoPg;
import by.allahverdiev.finaltask.entity.User;

import java.sql.Connection;

public class UserService implements Service {
    private final DaoFactory factory;

    public UserService(DaoFactory factory) {
        this.factory = factory;
    }

    public User login(Connection connection, String login, String password) throws AccessException {
        UserDaoPg userDaoPg = factory.getUserDao(connection);
        User user = userDaoPg.login(login, password);
        if (user.getName() == null) {
            throw new AccessException("error.login");
        }
        return user;
    }
}
