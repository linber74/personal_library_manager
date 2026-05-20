package model.enums;

import java.util.logging.Logger;

public enum BookFormat {

    PRINT_BOOK ("Bok"),
    EBOOK ("Ebook"),
    AUDIOBOOK ("Ljudbok"),
    FANFICTION ("Fanfiction");

    private final String bFormat;

    BookFormat(String bFormat){
        this.bFormat = bFormat;
    }

    public String getbFormat() {
        return bFormat;
    }

    @Override
    public String toString() {
        return bFormat;
    }

    private static final Logger LOGGER = Logger.getLogger(BookFormat.class.getName());

    public static BookFormat fromString (String text){
        for (BookFormat bookFormat : BookFormat.values()) {
            if (bookFormat.bFormat.equalsIgnoreCase(text)) {
                return bookFormat;
            }
        }
        LOGGER.warning("BookFormat " + text + " not supported");
        throw new IllegalArgumentException("Format " + text + " not supported");
    }
}
