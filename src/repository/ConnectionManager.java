package repository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

public class ConnectionManager {

    private static final Logger LOGGER = Logger.getLogger(ConnectionManager.class.getName());

    private final String url;
    private final String user;
    private final String password;

    public ConnectionManager () {
        Properties prop = new Properties();
        try (
                InputStream stream = DatabaseRepository.class.getClassLoader().getResourceAsStream("settings.properties")) {
            if (stream == null) {
                LOGGER.severe("Could not load settings.properties");
                throw new FileNotFoundException("Could not load settings.properties");
            }
            prop.load(stream);

            this.url = prop.getProperty("db.url");
            this.user = prop.getProperty("db.user");
            this.password = prop.getProperty("db.password");

            if (url == null || url.isBlank()) {
                LOGGER.severe("url missing");
                throw new IllegalArgumentException("url missing");
            } else if (user == null || user.isBlank()) {
                LOGGER.severe("user missing");
                throw new IllegalArgumentException("user saknas");

            } else if (password == null || password.isBlank()) {
                LOGGER.severe("password missing");
                throw new IllegalArgumentException("password missing");
            }

        } catch (IOException e) {
            LOGGER.severe("Could not load settings.properties");
            throw new RuntimeException("Kunde inte läsa settings.properties" + e);
        }
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
