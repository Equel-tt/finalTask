package by.allahverdiev.finaltask.dao.postgres;

import by.allahverdiev.finaltask.dao.UserDao;
import by.allahverdiev.finaltask.entity.Entity;
import by.allahverdiev.finaltask.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoPg implements UserDao {
    private static final Logger LOGGER = LogManager.getLogger(UserDaoPg.class);
    Connection connection;

    public UserDaoPg(Connection newConnection) {
        this.connection = newConnection;
    }

    private static final String SQL_SELECT_ALL_USERS =
            "SELECT *" +
                    "FROM manufacture.public.users";


    @Override
    public List findAll() {
        List<User> users = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_USERS);
            while (resultSet.next()) {
                int newId = resultSet.getInt("id");
                String newName = resultSet.getString("name");
                users.add(new User(newId, newName));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return users;
    }

    @Override
    public Entity findEntityById(Object id) {
        return null;
    }

    @Override
    public boolean delete(Object id) {
        return false;
    }

    @Override
    public boolean delete(Entity entity) {
        return false;
    }

    @Override
    public boolean create(Entity entity) {
        return false;
    }

    @Override
    public Entity update(Entity entity) {
        return null;
    }
}
