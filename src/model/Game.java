package model;

public class Game extends LibraryItem {

   private final String creator;

    public Game(String itemId, String title, String genre, String language, SeriesInfo seriesInfo, String creator) {
        super(itemId, ItemType.GAME, title, genre, language, seriesInfo);
        this.creator = creator;
    }

    public String getCreator() {
        return creator;
    }

    // TODO: Adjust toString() when final output format is decided.
    @Override
    public String toString() {
        return creator;
    }
}
