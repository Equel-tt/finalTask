package by.allahverdiev.finaltask.service;

import by.allahverdiev.finaltask.dao.pool.ConnectionPool;

public class InitializationService {
    private static final InitializationService instance = new InitializationService();

    public static InitializationService getInstance() {
        return instance;
    }

    public void initialize() {
        ConnectionPool.getInstance().init();
    }
}
