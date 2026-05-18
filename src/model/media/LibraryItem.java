package model.media;


import model.detail.SeriesInfo;
import model.enums.ItemType;

import java.util.List;

public abstract class LibraryItem {
    private final int itemId;
    private final ItemType itemType;

    private String title;
    private List<String> genre;
    private  String language;
    private Integer publishYear;
    private SeriesInfo seriesInfo;

    public LibraryItem(int itemId, ItemType itemType, String title, List<String> genre, String language, Integer publishYear, SeriesInfo seriesInfo) {
        this.itemId = itemId;
        this.itemType = itemType;
        this.title = title;
        this.genre = genre;
        this.language = language;
        this.publishYear = publishYear;
        this.seriesInfo = seriesInfo;
    }


    public int getItemId() {
        return itemId;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getGenre() {
        return genre;
    }

    public String getLanguage() {
        return language;
    }

    public Integer getPublishYear() {
        return publishYear;
    }

    public SeriesInfo getSeriesInfo() {
        return seriesInfo;
    }

    public  void setTitle(String title) {
        this.title = title;
    }

    public  void setGenre(List<String> genre) {
        this.genre = genre;
    }

    public  void setLanguage(String language) {
        this.language = language;
    }

    public  void setPublishYear(Integer publishYear) {
        this.publishYear = publishYear;
    }

    public  void setSeriesInfo(SeriesInfo serieInfo) {
        this.seriesInfo = serieInfo;
    }

    @Override
    public String toString(){
    // kort, UI-vänlig sammanfattning
        String base = title + " | " + "Type: " + itemType + " | " + "(" + language + ")";
        if (publishYear != null) {
            base += ", " + publishYear;
        }
        if (seriesInfo != null) {
            base += " - " + seriesInfo;
        }
        return base;
    }
}
