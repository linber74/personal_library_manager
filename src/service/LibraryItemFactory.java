package service;

import model.*;

import java.util.UUID;

public class LibraryItemFactory {


    private String generateId() {
        return UUID.randomUUID().toString();
    }

    public Game createGame(String title, String genre, String language, SeriesInfo seriesInfo, String creator) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or blank");
        }
        if (creator == null || creator.isBlank()) {
            throw new IllegalArgumentException("Creator cannot be null or blank");
        }

       String id = generateId();
       return new Game(id, title, genre, language, seriesInfo, creator);
    }

    public Book createBook(String title, String genre, String language, SeriesInfo seriesInfo, String author,
                           BookFormat bookFormat, FanficType fanficType, String fandom) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or blank");
        }
        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("Author cannot be null or blank");
        }
        if (bookFormat == null) {
            throw new IllegalArgumentException("BookFormat cannot be null");
        }
        if (bookFormat == BookFormat.FANFICTION) {
            if (fanficType == null) {
                throw new IllegalArgumentException("FanficType cannot be null");
            } else if (fanficType != FanficType.ORIGINAL) {
                if (fandom == null ||  fandom.isBlank()) {
                    throw new IllegalArgumentException("Fandom cannot be null or blank");
                }
                
            }
        }

        String id = generateId();
        return new Book(id, title, genre, language, seriesInfo, author, bookFormat, fanficType, fandom);
    }
}
