package repository;

import model.LibraryItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DatabaseRepository implements LibraryRepository {

    private final ConnectionManager connectionManager;
    public DatabaseRepository(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }


    @Override
    public List<LibraryItem> loadAll() {
        return List.of();
    }

    @Override
    public LibraryItem findById(int itemId) {
        return null;
    }

    @Override
    public void save(LibraryItem item) {

    }

    @Override
    public boolean deleteById(int itemId) {
        return false;
    }

    @Override
    public List<String> getAllGenres() {
        return getSimpleList("SELECT genre From genre", "genre");
    }

    @Override
    public List<String> getAllLanguages() {
        return getSimpleList("SELECT language From language", "language");
    }

    @Override
    public List<String> getAllFandoms() {
        return getSimpleList("SELECT fandom From fandom", "fandom");
    }

    @Override
    public void addGenre(String genre) {
        executeSimpleUpdate("INSERT INTO genre (genre) VALUES (?)", genre);
    }

    @Override
    public void addLanguage(String language) {
        executeSimpleUpdate("INSERT INTO language (language) VALUES (?)", language);
    }

    @Override
    public void addFandom(String fandom) {
        executeSimpleUpdate("INSERT INTO fandom (fandom) VALUES (?)", fandom);
    }

    @Override
    public void removeGenre(String genre) {
        executeSimpleUpdate("DELETE FROM genre WHERE genre = ?", genre);
    }

    @Override
    public void removeLanguage(String language) {
        executeSimpleUpdate("DELETE FROM language WHERE language = ?", language);
    }

    @Override
    public void removeFandom(String fandom) {
        executeSimpleUpdate("DELETE FROM fandom WHERE fandom = ?", fandom);
    }

    private List <String> getSimpleList (String sql, String columnName) {
        List<String> list = new ArrayList<>();
        try(Connection conn = connectionManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(rs.getString(columnName));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    private void executeSimpleUpdate(String sql, String value) {
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, value);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
