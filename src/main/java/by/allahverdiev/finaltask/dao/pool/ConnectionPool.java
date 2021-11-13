package by.allahverdiev.finaltask.dao.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.LinkedBlockingQueue;

public final class ConnectionPool {
	private static final Logger logger = LogManager.getLogger(ConnectionPool.class);
    private int maxSize;
	private int checkConnectionTimeout;
	private static final Properties properties = new Properties();
	private static String databaseUrl;

	private final BlockingQueue<PooledConnection> freeConnections = new LinkedBlockingQueue<>();
	private final Set<PooledConnection> usedConnections = new ConcurrentSkipListSet<>();

	private ConnectionPool() {
	}

	private static final ConnectionPool instance = new ConnectionPool();

	public static ConnectionPool getInstance() {
		return instance;
	}

	public synchronized Connection getConnection() {
		PooledConnection connection = null;
		while (connection == null) {
			try {
				if (!freeConnections.isEmpty()) {
					connection = freeConnections.take();
					if (!connection.isValid(checkConnectionTimeout)) {
						try {
							connection.getConnection().close();
						} catch (SQLException e) {
						}
						connection = null;
					}
				} else if (usedConnections.size() < maxSize) {
					connection = createConnection();
				} else {
                    logger.error("The limit of number of database connections is exceeded");
				}
			} catch (InterruptedException | SQLException e) {
                logger.error("It is impossible to connect to a database", e);
			}
		}
        usedConnections.add(connection);
        logger.debug(String.format("Connection was received from pool. Current pool size: %d used connections; %d free connection", usedConnections.size(), freeConnections.size()));
		return connection;
	}

	synchronized void freeConnection(PooledConnection connection) {
		try {
			if (connection.isValid(checkConnectionTimeout)) {
				connection.clearWarnings();
				connection.setAutoCommit(true);
				usedConnections.remove(connection);
                freeConnections.put(connection);
                logger.debug(String.format("Connection was returned into pool. Current pool size: %d used connections; %d free connection", usedConnections.size(), freeConnections.size()));
			}
		} catch (SQLException | InterruptedException e1) {
            logger.warn("It is impossible to return database connection into pool", e1);
			try {
				connection.getConnection().close();
			} catch (SQLException e2) {
			}
		}
	}

	public synchronized void init() {
		try {
            logger.info("start init connection pool");
            properties.load(new FileReader("H:\\FinalTask\\src\\main\\resources\\database.properties"));
            String driverName = (String) properties.get("db.driver");
            Class.forName(driverName);
            databaseUrl = (String) properties.get("db.url");
            int startSize = Integer.parseInt((String) properties.get("startSize"));
            this.maxSize = Integer.parseInt((String) properties.get("maxSize"));
            this.checkConnectionTimeout = Integer.parseInt((String) properties.get("checkConnectionTimeout"));
            for (int counter = 0; counter < startSize; counter++) {
                freeConnections.put(createConnection());
            }
            logger.info("end init connection pool");
		} catch (ClassNotFoundException | IOException | SQLException | InterruptedException e) {
            logger.fatal(e.getMessage()); // fatal exception
		}
	}

	private PooledConnection createConnection() throws SQLException {
		return new PooledConnection(DriverManager.getConnection(databaseUrl, properties));
	}

}
