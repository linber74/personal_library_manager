package model.media;

import model.enums.ItemType;
import model.detail.SeriesInfo;

import java.util.List;

public class Game extends LibraryItem {

   private final String creator;

    public Game(int itemId, String title, List<String> genre, String language, Integer publishYear, SeriesInfo seriesInfo, String creator) {
        super(itemId, ItemType.GAME, title, genre, language, publishYear, seriesInfo);
        this.creator = creator;
    }

    public String getCreator() {
        return creator;
    }

    // TODO: Adjust toString() when final output format is decided.
    @Override
    public String toString() {
        return super.toString()+ creator + "\n";
    }
}
