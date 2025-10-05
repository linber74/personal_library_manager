package Bibliotek;

public class Books extends Bibliotek {

    public Books(String title, String author, String genre, String language, String format) {
        super(title, author, genre, language, format);
        if (!"Hardcover".equals(format) && !"Paperback".equals(format)) {
            throw new IllegalArgumentException("Måste vara Hardcover eller Paperback");
        }
    }
}
