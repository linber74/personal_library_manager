package Archive_Superklass.Bibliotek;

import Service.Dialogtext;

public class Audiobooks extends Bibliotek {

    public Audiobooks(String title, String author, String genre, String language) {
        super(title, author, genre, language, Dialogtext.AUDIOBOK);
    }
}
