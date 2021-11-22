package by.allahverdiev.finaltask.dao;

import by.allahverdiev.finaltask.dao.pool.ConnectionPool;

import java.sql.Connection;

public class TransactionFactory {
    private static final ConnectionPool pool = ConnectionPool.getInstance();

    private static final TransactionFactory instance = new TransactionFactory();

    public static TransactionFactory getInstance() {
        return instance;
    }

    public Connection getConnection() {
        return pool.getConnection();
    }
}
