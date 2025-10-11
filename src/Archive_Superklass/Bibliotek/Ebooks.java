package Archive_Superklass.Bibliotek;

import Service.Dialogtext;

public class Ebooks extends Bibliotek {

    public Ebooks(String title, String author, String genre, String language) {
        super(title, author, genre, language, Dialogtext.E_BOK);

    }
}

