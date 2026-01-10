package model;

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
}
