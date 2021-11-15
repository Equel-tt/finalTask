package by.allahverdiev.finaltask.dao;

import by.allahverdiev.finaltask.entity.Entity;

import java.sql.SQLException;
import java.util.List;

public interface Dao<K, T extends Entity> {
//    Connection connection = null;

    List<T> findAll() throws SQLException;

    T findEntityById(K id) throws SQLException;

    boolean delete(K id);

    boolean delete(T entity);

    boolean create(T entity);

    T update(T entity);
}
