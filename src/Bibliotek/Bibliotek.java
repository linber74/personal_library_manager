package Bibliotek;

public class Bibliotek extends Archive {
    public final String author;

    public Bibliotek(String title, String author, String genre, String language, String format) {
        super(title, genre, language, format);
        this.author = author;
    }



    // @Override
    //public String toString() {
      //  return
    //}

}

