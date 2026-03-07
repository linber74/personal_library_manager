package model;

import java.util.ArrayList;
import java.util.List;

public class TVSeries extends LibraryItem{
    private final String director;
    private final List<String> actors;
    private final MediaFormat mediaFormat;
    private final TranslationInfo translationInfo;
    private final List<Season> seasons;

    public TVSeries(int itemId, String title, List<String> genre, String language, SeriesInfo seriesInfo,
                    String director1, List<String> actors1, MediaFormat mediaFormat1, TranslationInfo translationInfo1, List<Season> seasons) {
        super(itemId, ItemType.TV_SERIES, title, genre, language, seriesInfo );
        this.director = director1;
        this.actors = actors1;
        this.mediaFormat = mediaFormat1;
        this.translationInfo = translationInfo1;

        if (seasons == null) {
            this.seasons = new ArrayList<>();
        }
        else {
            this.seasons = new ArrayList<>(seasons);
        }
    }
    public List<Season> getSeasons() {
        return seasons;
    }

    public void addSeason(Season season) {
        this.seasons.add(season);
    }

    // TODO: Adjust toString() when final output format is decided.
    @Override
    public String toString() {
        return super.toString();
    }

}

