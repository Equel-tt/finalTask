package by.allahverdiev.finaltask.dao.postgres;

import by.allahverdiev.finaltask.dao.UserDao;
import by.allahverdiev.finaltask.entity.Entity;
import by.allahverdiev.finaltask.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
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
                    "FROM manufacture.public.employee";
    private static final String SQL_SELECT_USER_BY_ID =
            "SELECT * " +
                    "FROM manufacture.public.employee " +
                    "WHERE employee.id = (?)";


    @Override
    public List findAll() {
        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_USERS);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String patronymic = resultSet.getString("patronymic");
                int role = resultSet.getInt("role");
                String description = resultSet.getString("description");
                users.add(new User(id, name, surname, patronymic, role, description));
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

    public User findEntityById(int id) {
        User user = new User();
        try (PreparedStatement ps = connection.prepareStatement(SQL_SELECT_USER_BY_ID)) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setPatronymic(resultSet.getString("patronymic"));
                user.setRole(resultSet.getInt("role"));
                user.setDescription(resultSet.getString("description"));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return user;
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