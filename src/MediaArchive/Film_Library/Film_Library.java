package MediaArchive.Film_Library;

import Archive_Superklass.Archive;

public class Film_Library extends Archive {

    private String movie;

    public Film_Library(String title, String genre, String language, String format) {
        super(title, genre, language, format);
    }
}
