package MediaArchive.Book_Library;

public class Fanfiction extends Library {

    private final String type;

    public Fanfiction(String title, String author, String genre, String language, String format, String type) {
        super(title, author, genre, language,format);
        this.type = type;
    }
}
