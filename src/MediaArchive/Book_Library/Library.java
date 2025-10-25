package MediaArchive.Book_Library;

import MediaArchive.Archive;

public class Library extends Archive {
    private final String author;

    public Library(String title, String author, String genre, String language, String format) {
        super(title, genre, language, format);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    // @Override
    //public String toString() {
      //  return
    //}

}

