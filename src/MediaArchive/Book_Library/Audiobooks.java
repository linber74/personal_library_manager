package MediaArchive.Book_Library;

import Service.Dialogtext;

public class Audiobooks extends Library {

    public Audiobooks(String title, String author, String genre, String language) {
        super(title, author, genre, language, Dialogtext.AUDIOBOK);
    }
}
