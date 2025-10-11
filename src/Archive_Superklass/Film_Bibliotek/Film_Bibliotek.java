package Archive_Superklass.Film_Bibliotek;

import Archive_Superklass.Archive;

public class Film_Bibliotek extends Archive {

    private String movie;

    public Film_Bibliotek(String title, String genre, String language, String format) {
        super(title, genre, language, format);
    }
}
