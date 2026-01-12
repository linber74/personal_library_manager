package model;



public abstract class LibraryItem {
    private final String itemId;
    private final ItemType itemType;

    private String title;
    private String genre;
    private  String language;
    private  SeriesInfo seriesInfo;

    public LibraryItem(String itemId, ItemType itemType, String title, String genre, String language, SeriesInfo seriesInfo) {
        this.itemId = itemId;
        this.itemType = itemType;
        this.title = title;
        this.genre = genre;
        this.language = language;
        this.seriesInfo = seriesInfo;
    }

    public String getItemId() {
        return itemId;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getLanguage() {
        return language;
    }

    public SeriesInfo getSeriesInfo() {
        return seriesInfo;
    }

    public  void setTitle(String title) {
        this.title = title;
    }

    public  void setGenre(String genre) {
        this.genre = genre;
    }

    public  void setLanguage(String language) {
        this.language = language;
    }

    public  void setSeriesInfo(SeriesInfo serieInfo) {
        this.seriesInfo = serieInfo;
    }

    @Override
    public String toString(){
    // kort, UI-vänlig sammanfattning
        String base = "[" + itemType + "] " + title + " (" + language + ")";
        if (seriesInfo != null) {
            base += " - " + seriesInfo;
        }
        return base;
    }
}
