package by.allahverdiev.finaltask.service;

import by.allahverdiev.finaltask.dao.postgres.UserDaoPg;
import by.allahverdiev.finaltask.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;

public class UserService implements Service {
    private static final Logger logger = LogManager.getLogger(UserService.class);

    public User login(Connection connection, String login, String password) throws AccessException {
        UserDaoPg userDaoPg = new UserDaoPg(connection);
        User user = userDaoPg.login(login, password);
        logger.debug(user.getName() + " работаем в сервисе");
        if (user.getName() == null) {
            logger.debug("выбрасываем исключение из сервиса");
            throw new AccessException("Неверный логин/пароль");
        }
        return user;
    }
}
