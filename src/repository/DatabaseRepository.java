package repository;

import model.detail.Season;
import model.detail.SeriesInfo;
import model.enums.*;
import model.media.*;
import model.detail.Episode;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class DatabaseRepository implements LibraryRepository {

    private static final Logger LOGGER = Logger.getLogger(DatabaseRepository.class.getName());

    private final ConnectionManager connectionManager;

    public DatabaseRepository(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public List<LibraryItem> loadAll() {
        String sql = "Select * from libraryItem";
        List<LibraryItem> items = new ArrayList<>();
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement prep = conn.prepareStatement(sql);) {

            try (ResultSet rs = prep.executeQuery()) {

                while (rs.next()) {
                    LibraryItemData data = extractLibraryItemData(rs);

                    switch (data.itemType) {
                        case "Bok" -> items.add(loadBookData(conn, data));

                        case "Film" -> items.add(loadFilmData(conn, data));

                        case "TV-serie" -> items.add(loadTVSeriesData(conn, data));

                        case "Spel"  -> items.add(loadGameData(conn, data));
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.severe("Error while loading library items: " + e.getMessage());
        }
        return items;
    }

    @Override
    public LibraryItem findById(int itemId) {
        String sql = "SELECT * FROM libraryItem WHERE itemId = ?";

        try (Connection conn = connectionManager.getConnection();
             PreparedStatement prep = conn.prepareStatement(sql)) {
            prep.setInt(1, itemId);

            try (ResultSet rs = prep.executeQuery()) {

                if (rs.next()) {
                    LibraryItemData data = extractLibraryItemData(rs);

                    switch (data.itemType) {
                        case "Bok": return loadBookData(conn, data);

                        case "Film": return loadFilmData(conn, data);

                        case "TV-serie": return loadTVSeriesData(conn, data);

                        case "Spel": return loadGameData(conn, data);
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.severe("Error while trying to find library item with id " + itemId);
        }
        return null;
    }

    @Override
    public void save(LibraryItem item) {
        String sql = ("Insert into libraryitem (title, itemType, language, publishYear, seriesName) values (?, ?, ?, ?, ?)");
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, item.getTitle());

            ps.setString(2, item.getItemType().toString());

            ps.setString(3, item.getLanguage());

            if (item.getPublishYear() != null) {
                ps.setInt(4, item.getPublishYear());
            } else  {
                ps.setNull(4, Types.INTEGER);
            }

            String seriesName = (item.getSeriesInfo() != null)
                    ? item.getSeriesInfo().getSeriesName()
                    : null;
            ps.setString(5, seriesName);

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
                    try (PreparedStatement prep = conn.prepareStatement(bookSql)) {

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
                    break;
                }

                case FILM: {
//
                    Film film = (Film) item;
                    String filmSql = "Insert into film (itemId) values (?)";

                    insertVisualMedia(id, film);

                    try (PreparedStatement prep = conn.prepareStatement(filmSql)) {
                        prep.setInt(1, id);
                        prep.executeUpdate();
                    }
                    break;
                }

                case GAME: {
                    Game game = (Game) item;
                    String gameSql = "Insert into game (itemId, creator)  values (?, ?)";

                    try (PreparedStatement prep = conn.prepareStatement(gameSql)) {
                        prep.setInt(1, id);

                        if (game.getCreator() != null) {
                            prep.setString(2, game.getCreator());
                        } else {
                            prep.setNull(2, Types.VARCHAR);
                        }
                        prep.executeUpdate();
                    }
                    break;
                }

                case TV_SERIES: {
//
                    TVSeries tvseries = (TVSeries) item;

                    insertVisualMedia(id, tvseries);

                    String seriesSql = "Insert into tvseries (itemId) values (?)";

                    try (PreparedStatement prep = conn.prepareStatement(seriesSql)) {
                        prep.setInt(1, id);
                        prep.executeUpdate();
                    }

                    String seasonSql = "Insert into season (tvseriesId, seasonNumber) values (?, ?)";
                    String episodeSql = "Insert into episode (tvseriesId, seasonNumber, episodeNumber, episodeName) values (?, ?, ?, ?)";
                    for (Season season : tvseries.getSeasons()) {
                        try (PreparedStatement prep = conn.prepareStatement(seasonSql)) {
                            prep.setInt(1, id);
                            prep.setInt(2, season.getSeasonNumber());
                            prep.executeUpdate();
                        }
                        for (Episode episode : season.getEpisodes()) {
                            try (PreparedStatement prep = conn.prepareStatement(episodeSql)) {
                                prep.setInt(1, id);
                                prep.setInt(2, season.getSeasonNumber());
                                prep.setInt(3, episode.getEpisodeNumber());

                                if (episode.getEpisodeName() != null) {
                                    prep.setString(4, episode.getEpisodeName());
                                } else {
                                    prep.setNull(4, Types.VARCHAR);
                                }
                                prep.executeUpdate();
                            }
                        }
                    }
                    break;
                }
            }
        }catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Finns redan i databasen!");
        } catch (SQLException e) {
            LOGGER.severe("Couldn't save to database: "+ e.getMessage());
        }
    }

    @Override
    public boolean deleteById(int itemId) {
        String sql = "SELECT itemType FROM libraryItem WHERE itemId = ?";

        try (Connection conn = connectionManager.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, itemId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String itemType = rs.getString("itemType");
                executeDeleteByInt("Delete from item_genres WHERE itemId = ?", itemId);

                switch (itemType) {
                    case "Bok": {
                        executeDeleteByInt("Delete from book_authors WHERE bookId = ?", itemId);
                        executeDeleteByInt("Delete from book_fandoms WHERE bookId = ?", itemId);
                        executeDeleteByInt("Delete from book where itemId = ?", itemId);
                        break;
                    }
                    case "Film": {
                        executeDeleteByInt("Delete from visualmedia_actors WHERE visualmediaId = ?", itemId);
                        executeDeleteByInt("Delete from film where itemId = ?", itemId);
                        executeDeleteByInt("Delete from visualmedia where itemId = ?", itemId);
                        break;
                    }

                    case "TV-serie": {
                        executeDeleteByInt("Delete from visualmedia_actors WHERE visualmediaId = ?", itemId);
                        executeDeleteByInt("Delete from episode WHERE tvseriesId = ?", itemId);
                        executeDeleteByInt("Delete from season WHERE tvseriesId = ?", itemId);
                        executeDeleteByInt("Delete from tvseries where itemId = ?", itemId);
                        executeDeleteByInt("Delete from visualmedia where itemId = ?", itemId);
                        break;
                    }
                    case "Spel": {
                        executeDeleteByInt("Delete from game WHERE itemId = ?", itemId);
                        break;
                    }
                }
                executeDeleteByInt("Delete from libraryitem where itemId = ?", itemId);
                return true;
            }
        } catch (SQLException e) {
            LOGGER.severe("Problem while executing query: " + sql);
        }
        return false ;
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
    private List<String> getSimpleList(String sql, String columnName) {
        List<String> list = new ArrayList<>();
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(rs.getString(columnName));
            }
        } catch (SQLException e) {
            LOGGER.severe("Error while retrieval: " + e.getMessage());
        }
        return list;
    }

    private void executeSimpleUpdate(String sql, String value) {
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, value);
            stmt.executeUpdate();

        } catch (SQLException e) {
            LOGGER.severe("Error while update: " + e.getMessage());
        }
    }

    private void executeDeleteByInt (String sql, int id) {
        try (Connection conn = connectionManager.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            LOGGER.severe("Error while delete: " + e.getMessage());
        }
    }

    private void executeJunctionInsert(String sql, int id, String value) {
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.setString(2, value);
            stmt.executeUpdate();

        } catch (SQLException e) {
            LOGGER.severe("Error while junction insert: " + e.getMessage());
        }
    }

    private List<String> getListByIntKey(String sql, int id, String columnName) {
        List<String> list = new ArrayList<>();

        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(rs.getString(columnName));
            }
        } catch (SQLException e) {
           LOGGER.severe("Error while retrieval by key: " + e.getMessage());
        }
        return list;
    }

    private void insertVisualMedia(int id, VisualMedia vm) {

        String sql = "INSERT INTO visualmedia (itemId, director, mediaFormat, translationInfo) VALUES (?, ?, ?, ?)";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            if (vm.getDirector() != null) {
                stmt.setString(2, vm.getDirector());
            } else {
                stmt.setNull(2, Types.VARCHAR);
            }

            stmt.setString(3, vm.getMediaFormat().toString());

            if (vm.getTranslationInfo() != null) {
                stmt.setString(4, vm.getTranslationInfo().toString());
            } else {
                stmt.setNull(4, Types.VARCHAR);
            }
            stmt.executeUpdate();

            for (String actor : vm.getActors()) {
                executeJunctionInsert("Insert into visualmedia_actors (visualmediaId, actorName) values (?, ?)", id, actor);
            }

        } catch (SQLException e) {
            LOGGER.severe("Error while inserting visual media: " + e.getMessage());
        }
    }

    private VisualMediaData loadVisualMediaData(int id) {
        String sql = "SELECT * FROM visualmedia WHERE itemId = ?";

        try (Connection conn = connectionManager.getConnection();
             PreparedStatement prep = conn.prepareStatement(sql)) {
            prep.setInt(1, id);
            try (ResultSet rs = prep.executeQuery()) {
                if (rs.next()) {
                    VisualMediaData vm = new VisualMediaData();

                    // director
                    vm.director = rs.getString("director");

                    // mediaFormat
                    String mediaStr = rs.getString("mediaFormat");
                    vm.mediaFormat = MediaFormat.fromString(mediaStr);

                    // translationInfo
                    String translationInfo = rs.getString("translationInfo");
                    vm.translationInfo = (translationInfo != null) ?
                            TranslationInfo.fromString(translationInfo) : null;

                    // actors
                    vm.actors = getListByIntKey("SELECT actorName FROM visualmedia_actors WHERE visualmediaId = ?", id,
                            "actorName");
                    return vm;
                }
            }

        } catch (SQLException e) {
            LOGGER.severe("Error while loading visual media: " + e.getMessage());
        }
        return null;
    }

    private LibraryItemData extractLibraryItemData(ResultSet rs) throws SQLException {

        LibraryItemData lid = new LibraryItemData();

        lid.itemId = rs.getInt("itemId");
        lid.title = rs.getString("title");
        lid.itemType = rs.getString("itemType");
        lid.language = rs.getString("language");
        lid.publishYear = rs.getInt("publishYear");

        String sName = rs.getString("seriesName");
        lid.seriesInfo = (sName != null) ? new SeriesInfo(sName) : null;

        lid.genres = getListByIntKey("Select genre From item_genres Where itemId = ? ", lid.itemId, "genre");

        return lid;
    }

    private Book loadBookData(Connection conn, LibraryItemData data) {
        String bookSql = "Select * from Book where itemId = ?";

        try (PreparedStatement bookPrep = conn.prepareStatement(bookSql)) {
            bookPrep.setInt(1, data.itemId);

            try (ResultSet bookRs = bookPrep.executeQuery()) {
                if (bookRs.next()) {
                    // author
                    List<String> authors = getListByIntKey(
                            "SELECT authorName FROM Book_Authors WHERE bookId = ?", data.itemId, "authorName");
                    // bookFormat
                    BookFormat bookFormat = BookFormat.fromString(bookRs.getString("bookFormat"));
                    // fandom
                    List<String> fandoms = getListByIntKey(
                            "SELECT fandom FROM book_fandoms WHERE bookId = ?", data.itemId, "fandom");
                    // fanficType
                    String fanficStr = bookRs.getString("fanficType");
                    FanficType fanficType = (fanficStr != null)
                            ? FanficType.fromString(fanficStr) : null;

                    Book book = new Book(data.itemId, data.title, data.genres, data.language, data.publishYear, data.seriesInfo, authors, bookFormat, fanficType, fandoms);

                    return book;
                }
            }
        } catch (SQLException e) {
            LOGGER.severe("Error while loading book: " + e.getMessage());
        }
        return null;
    }

    private Film loadFilmData(Connection conn, LibraryItemData data){
        String filmSql = "Select * from film where itemId = ?";

        try (PreparedStatement filmPrep = conn.prepareStatement(filmSql)) {
            filmPrep.setInt(1, data.itemId);
            try (ResultSet filmRs = filmPrep.executeQuery()) {
                if (filmRs.next()) {
                    VisualMediaData vm = loadVisualMediaData(data.itemId);

                    Film film = new Film(data.itemId, data.title, data.genres, data.language, data.seriesInfo, data.publishYear, vm.director,
                            vm.actors, vm.mediaFormat, vm.translationInfo);

                    return film;
                }
            }
        } catch (SQLException e) {
            LOGGER.severe("Error while loading film: " + e.getMessage());
        }
        return null;
    }

    private Game loadGameData(Connection conn, LibraryItemData data) {
        String gameSql = "Select creator from game where itemId = ?";

        try (PreparedStatement gamePrep = conn.prepareStatement(gameSql)) {
            gamePrep.setInt(1, data.itemId);

            try (ResultSet gameRs = gamePrep.executeQuery()) {
                if (gameRs.next()) {
                    String creator = gameRs.getString("creator");
                    Game game = new Game(data.itemId, data.title, data.genres, data.language, data.publishYear, data.seriesInfo, creator);

                    return game;
                }
            }
        } catch (SQLException e) {
            LOGGER.severe("Error while loading game: " + e.getMessage());
        }
        return null;
    }

    private TVSeries loadTVSeriesData(Connection conn, LibraryItemData data) {
        String tvSeriesSql = "Select * from tvSeries where itemId = ?";
        try (PreparedStatement tvSeriesPrep = conn.prepareStatement(tvSeriesSql)) {
            tvSeriesPrep.setInt(1, data.itemId);

            try (ResultSet tvSeriesRs = tvSeriesPrep.executeQuery()) {
                if (tvSeriesRs.next()) {
                    VisualMediaData vm = loadVisualMediaData(data.itemId);

                    // season
                    String seasonSql = "SELECT * FROM season WHERE tvseriesId = ?";

                    try (PreparedStatement seasonPrep = conn.prepareStatement(seasonSql)) {
                        seasonPrep.setInt(1, data.itemId);

                        List<Season> seasons = new ArrayList<>();

                        try (ResultSet seasonRs = seasonPrep.executeQuery()) {
                            while (seasonRs.next()) {
                                int seasonNumber = seasonRs.getInt("seasonNumber");

                                String episodeSql = "SELECT * FROM episode WHERE tvseriesId = ? AND seasonNumber = ?";

                                List<Episode> episodes = new ArrayList<>();

                                try (PreparedStatement episodePrep = conn.prepareStatement(episodeSql)) {
                                    episodePrep.setInt(1, data.itemId);
                                    episodePrep.setInt(2, seasonNumber);

                                    try (ResultSet episodeRs = episodePrep.executeQuery()) {
                                        while (episodeRs.next()) {
                                            int episodeNumber = episodeRs.getInt("episodeNumber");
                                            String episodeName = episodeRs.getString("episodeName");

                                            Episode episode = new Episode(episodeNumber, episodeName);

                                            episodes.add(episode);
                                        }
                                    }
                                }
                                Season season = new Season(seasonNumber, episodes);
                                seasons.add(season);
                            }
                            TVSeries tvSeries = new TVSeries(data.itemId, data.title, data.genres, data.language, data.publishYear, data.seriesInfo,
                                    vm.director, vm.actors, vm.mediaFormat, vm.translationInfo, seasons);
                            return tvSeries;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.severe("Error while loading TV series: " + e.getMessage());
        }
        return null;
    }

        // Inner classes
        private static class VisualMediaData {
            String director;
            MediaFormat mediaFormat;
            TranslationInfo translationInfo;
            List<String> actors;
        }

        private static class LibraryItemData {
            int itemId;
            String title;
            String itemType;
            String language;
            Integer publishYear;
            SeriesInfo seriesInfo;
            List<String> genres;
        }
    }