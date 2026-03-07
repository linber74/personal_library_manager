package repository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {

    private final String url;
    private final String user;
    private final String password;

    public ConnectionManager () {
        Properties prop = new Properties();
        try (
                InputStream stream = DatabaseRepository.class.getClassLoader().getResourceAsStream("settings.properties")) {
            if (stream == null) {
                throw new FileNotFoundException("settings.properties not found");
            }
            prop.load(stream);

            this.url = prop.getProperty("db.url");
            this.user = prop.getProperty("db.user");
            this.password = prop.getProperty("db.password");

            if (url == null || url.isBlank()) {
                throw new IllegalArgumentException("url saknas");
            } else if (user == null || user.isBlank()) {
                throw new IllegalArgumentException("user saknas");

            } else if (password == null || password.isBlank()) {
                throw new IllegalArgumentException("password saknas");
            }

        } catch (
                IOException e) {
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
