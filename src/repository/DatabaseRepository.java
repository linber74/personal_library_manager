package repository;

import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DatabaseRepository implements LibraryRepository {

    private final ConnectionManager connectionManager;
    public DatabaseRepository(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }


    @Override
    public List<LibraryItem> loadAll()  {
        String sql = "Select * from libraryItem";
        List<LibraryItem> items = new ArrayList<>();
        try (Connection conn = connectionManager.getConnection();
        PreparedStatement prep = conn.prepareStatement(sql);){

            try(ResultSet rs = prep.executeQuery()) {

                while (rs.next()) {
                    int itemId = rs.getInt("itemId");
                    String title = rs.getString("title");
                    String itemType = rs.getString("itemType");
                    String language = rs.getString("language");
                    String seriesName = rs.getString("seriesName");

                    List <String> genres = getListByIntKey(
                            "SELECT genre FROM item_genres WHERE itemId = ?", itemId, "genre");

                    SeriesInfo seriesInfo = (seriesName != null) ? new SeriesInfo(seriesName) :null;

                    switch (itemType) {
                        case "Bok": {
                            String bookSql = "Select * from Book where itemId = ?";

                            try (PreparedStatement bookPrep = conn.prepareStatement(bookSql)){
                                bookPrep.setInt(1, itemId);

                                try (ResultSet bookRs = bookPrep.executeQuery()) {
                                    if (bookRs.next()) {
                                        // author
                                        List<String> authors = getListByIntKey(
                                                "SELECT authorName FROM Book_Authors WHERE bookId = ?", itemId, "authorName");
                                        // bookFormat
                                        BookFormat bookFormat = BookFormat.fromString(bookRs.getString("bookFormat"));
                                        // fandom
                                        List<String> fandoms = getListByIntKey(
                                                "SELECT fandom FROM book_fandoms WHERE bookId = ?", itemId, "fandom");
                                        // fanficType
                                        String fanficStr = bookRs.getString("fanficType");
                                        FanficType fanficType = (fanficStr != null)
                                                ? FanficType.fromString(fanficStr) : null;

                                        Book book = new Book(itemId, title, genres, language, seriesInfo, authors, bookFormat, fanficType, fandoms);
                                        items.add(book);
                                    }
                                }
                            }
                        }

                        case "Film":

                        case "TV-serie":

                        case "Spel": {
                            String gameSql =  "Select creator from game where itemId = ?";

                            try(PreparedStatement gamePrep = conn.prepareStatement(gameSql)){
                                gamePrep.setInt(1, itemId);

                                try(ResultSet gameRs = gamePrep.executeQuery()){
                                    if (gameRs.next()) {
                                        String creator = gameRs.getString("creator");
                                        Game game = new Game(itemId, title, genres, language, seriesInfo, creator);
                                        items.add(game);
                                    }
                                }
                            }
                            break;
                        }
                    }


//SeriesInfo seriesInfo = (seriesName != null)
//    ? new SeriesInfo(seriesName) : null;
//
// Switch på itemType-strängen
//switch (itemType):
//    case "Bok": hämta book-data, skapa Book
//    case "Film": hämta film-data, skapa Film
//    case "Spel": hämta game-data, skapa Game
//    case "TV-serie": hämta tvseries-data, skapa TVSeries
//

                    /// / Lägg till i listan
//items.add(item);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return items;
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

                case FILM: {
                    Film film = (Film) item;
                    String filmSql = "Insert into film (itemId, director, filmType, mediaFormat, translationInfo) values (?, ?, ?, ?, ?)";

                    try (PreparedStatement prep = conn.prepareStatement(filmSql)){

                        prep.setInt(1, id);

                        if (film.getDirector() != null) {
                            prep.setString(2, film.getDirector());
                        }else  {
                            prep.setNull(2, Types.VARCHAR);
                        }

                        prep.setString(3, film.getFilmType().toString());

                        prep.setString(4, film.getMediaFormat().toString());

                        if (film.getTranslationInfo() != null) {
                            prep.setString(5, film.getTranslationInfo().toString());
                        } else {
                            prep.setNull(5, Types.VARCHAR);
                        }
                        prep.executeUpdate();
                    }

                    for (String actor : film.getActors()) {
                        executeJunctionInsert("Insert into film_actors (filmId, actorName) values (?, ?)", id, actor);
                    }
                }

                case GAME: {
                    Game game = (Game) item;
                    String gameSql = "Insert into game (itemId, creator)  values (?, ?)";

                    try (PreparedStatement prep = conn.prepareStatement(gameSql)){
                        prep.setInt(1, id);

                        if (game.getCreator() != null) {
                            prep.setString(2, game.getCreator());
                        } else {
                            prep.setNull(2, Types.VARCHAR);
                        }
                        prep.executeUpdate();
                    }
                }

                case TV_SERIES: {
                    TVSeries tvseries = (TVSeries) item;
                    String seriesSql = "Insert into tvseries (itemId) values (?)";

                    try (PreparedStatement prep = conn.prepareStatement(seriesSql)){
                        prep.setInt(1, id);
                        prep.executeUpdate();
                    }

                    String seasonSql =  "Insert into season (tvseriesId, seasonNumber) values (?, ?)";
                    String episodeSql  = "Insert into episode (tvseriesId, seasonNumber, episodeNumber, episodeName) values (?, ?, ?, ?)";
                    for (Season season : tvseries.getSeasons()) {
                        try (PreparedStatement prep = conn.prepareStatement(seasonSql)){
                            prep.setInt(1, id);
                            prep.setInt(2, season.getSeasonNumber());
                            prep.executeUpdate();
                        }
                        for (Episode episode : season.getEpisodes()) {
                            try (PreparedStatement prep = conn.prepareStatement(episodeSql)){
                                prep.setInt(1, id);
                                prep.setInt(2, season.getSeasonNumber());
                                prep.setInt(3, episode.getEpisodeNumber());

                                if (episode.getEpisodeName() != null) {
                                    prep.setString(4, episode.getEpisodeName());
                                } else  {
                                    prep.setNull(4, Types.VARCHAR);
                                }
                                prep.executeUpdate();
                            }
                        }
                    }
                }
            }



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

    // Help Methods
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

    private List <String> getListByIntKey(String sql, int id, String columnName) {
        List <String> list = new ArrayList<>();

        try(Connection conn = connectionManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(rs.getString(columnName));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

//    privat metod: getListByIntKey(sql, kolumnNamn, id) -> List<String>
//    skapa tom lista
//    öppna connection
//    sätt int-parametern (?)
//    kör frågan
//    loopa resultat: lägg till kolumnvärdet
//    returnera listan
}
