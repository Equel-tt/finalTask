package by.allahverdiev.finaltask.dao;

import by.allahverdiev.finaltask.entity.User;

public interface UserDao extends Dao {

    User login(String login, String inputPassword);

    User findEntityById(int id);

    User updateName(User user);
}
