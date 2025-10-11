package Archive_Superklass.Bibliotek;

import Archive_Superklass.Archive;

public class Bibliotek extends Archive {
    private final String author;

    public Bibliotek(String title, String author, String genre, String language, String format) {
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

