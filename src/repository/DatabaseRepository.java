package repository;

import model.Book;
import model.BookFormat;
import model.FanficType;
import model.LibraryItem;

import java.sql.*;
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
        String sql = ("Insert into libraryitem (title, itemType, language, seriesName) values (?, ?, ?, ?)");
        try(Connection conn = connectionManager.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, item.getTitle());

            ps.setString(2, item.getItemType().toString());

            ps.setString(3, item.getLanguage());

            String seriesName = (item.getSeriesInfo() != null)
                    ? item.getSeriesInfo().getSeriesName()
                    : null;
            ps.setString(4, seriesName );

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            int id = 0;
            if (rs.next()) {
                id = rs.getInt(1);
            }

            for (String genre : item.getGenre()) {
                executeJunctionInsert("Insert into item_genres (itemId, genre) values (?, ?)", id, genre);
            }

            switch (item.getItemType()) {
                case BOOK: {
                    Book book = (Book) item;
                    String bookSql = "INSERT INTO book (itemId, fanficType, bookFormat) VALUES (?, ?, ?)";
                    try (PreparedStatement prep =  conn.prepareStatement(bookSql)){

                        prep.setInt(1, id);

                        if (book.getFanficType() != null) {
                            prep.setString(2, book.getFanficType().toString());
                        } else {
                            prep.setNull(2, Types.VARCHAR);
                        }

                        prep.setString(3, book.getBookFormat().toString());

                        prep.executeUpdate();

                    }

                    for (String author : book.getAuthor()) {
                        executeJunctionInsert("Insert into book_authors (bookId, authorName) values (?, ?)", id, author);
                    }
                    if (book.getFandom() != null) {
                        for (String fandom : book.getFandom()) {
                            executeJunctionInsert("Insert into book_fandoms (bookId, fandom) values (?, ?)", id, fandom);
                        }
                    }
                }

                case FILM:
                case GAME:
                case TV_SERIES:
            }

//          else if item är Film:
//            INSERT i film + film_actors
//          else if item är Game:
//            INSERT i game
//              else if item är TVSeries:
//            INSERT i tvseries + season + episode

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

    private void executeJunctionInsert (String sql, int id, String value) {
        try (Connection conn = connectionManager.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.setString(2, value);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
