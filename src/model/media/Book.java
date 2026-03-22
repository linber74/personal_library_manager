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
        return super.toString() + "Författare: " + author + " - " + "Titel: " + getTitle() + "\n";
    }
}

