package model.media;

import model.detail.SeriesInfo;
import model.enums.BookFormat;
import model.enums.FanficType;
import model.enums.ItemType;

import java.util.List;

public class Book extends LibraryItem {

    private final List<String> author;
    private final BookFormat bookFormat;
    private final List <String> fandom;
    private final FanficType fanficType;

    public Book(int itemId, String title, List<String> genre, String language, Integer publishYear,
                SeriesInfo seriesInfo, List<String> author, BookFormat bookFormat, FanficType fanficType, List<String> fandom) {
        super(itemId, ItemType.BOOK, title, genre, language, publishYear, seriesInfo);
        this.author = author;
        this.bookFormat = bookFormat;
        if (bookFormat == BookFormat.FANFICTION) {
            this.fanficType = fanficType;
            this.fandom = (fandom == null || fandom.isEmpty()) ? null : fandom;
        } else {
            this.fanficType = null;
            this.fandom = null;
        }

    }

    public List<String> getAuthor() {
        return author;
    }

    public BookFormat getBookFormat() {
        return bookFormat;
    }
    public List<String> getFandom() {
        return fandom;
    }
    public FanficType getFanficType() {
        return fanficType;
    }

    // TODO: Adjust toString() when final output format is decided.
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append(getTitle()).append("\n");

        sb.append("Author: ").append(author).append("\n");

        sb.append("Genre: ").append(getGenre()).append("\n");

        sb.append("Book Format: ").append(bookFormat).append("\n");

        if (getFandom() != null && !getFandom().isEmpty()) {
            sb.append("Fandom: ").append(String.join(", ", getFandom())).append("\n");
        }

        if (fanficType != null) {
            sb.append("FanficType: ").append(fanficType).append("\n");
        }

        if (getSeriesInfo() != null) {
            sb.append("SeriesInfo: ").append(getSeriesInfo()).append("\n");
        }

        sb.append("Type: ").append(getItemType()).append("\n");

        sb.append("Language: ").append(getLanguage()).append("\n");

        if (getPublishYear() != null) {
            sb.append("PublishYear: ").append(getPublishYear()).append("\n");
        }

        return sb.toString();
    }
}

