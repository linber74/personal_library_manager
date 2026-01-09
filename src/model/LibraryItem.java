package model;



abstract class LibraryItem {
    private  int itemId;
    private ItemType itemType;
    private final String title;
    private final String genre;
    private final String language;
    private final SeriesInfo serieInfo;

    public LibraryItem(String title, String genre, String language, SeriesInfo serieInfo, int itemId, ItemType itemType) {
        this.title = title;
        this.genre = genre;
        this.language = language;
        this.serieInfo = serieInfo;
        this.itemId = itemId;
        this.itemType = itemType;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
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

    public SeriesInfo getSerieInfo() {
        return serieInfo;
    }
}
