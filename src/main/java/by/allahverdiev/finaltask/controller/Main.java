package by.allahverdiev.finaltask.controller;

import by.allahverdiev.finaltask.dao.TransactionFactory;
import by.allahverdiev.finaltask.dao.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        pool.init();
        TransactionFactory factory = new TransactionFactory();
        Connection connection = factory.getConnection();
    }
}



