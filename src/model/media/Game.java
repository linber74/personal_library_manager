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

        StringBuilder sb = new StringBuilder();

        sb.append(getTitle()).append("\n");

        if (getSeriesInfo() != null) {
            sb.append("SeriesInfo: ").append(getSeriesInfo()).append("\n");
        }

        sb.append("Genre: ").append(getGenre()).append("\n");

        if (creator != null) {
            sb.append("Creator: ").append(creator).append("\n");
        } else {
            sb.append("Creator: Unknown\n");
        }

        sb.append("Type: ").append(getItemType()).append("\n");

        sb.append("Language: ").append(getLanguage()).append("\n");

        if (getPublishYear() != null) {
            sb.append("PublishYear: ").append(getPublishYear()).append("\n");
        }

        return sb.toString();
    }
}
