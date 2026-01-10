package model;



abstract class LibraryItem {
    private  int itemId;
    private ItemType itemType;
    private String title;
    private String genre;
    private  String language;
    private  SeriesInfo serieInfo;

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

    public  void setTitle(String title) {
        this.title = title;
    }
    public  void setGenre(String genre) {
        this.genre = genre;
    }

    public  void setLanguage(String language) {
        this.language = language;
    }
    public  void setSerieInfo(SeriesInfo serieInfo) {
        this.serieInfo = serieInfo;
    }

    @Override
    public String toString(){
        return toString();
    }
}
