package model;

import java.util.ArrayList;
import java.util.List;

public class TVSeries extends LibraryItem{

    private final List<Season> seasons;

    public TVSeries(int itemId, String title, String genre, String language, SeriesInfo seriesInfo, Film director,
                    List<String> actors, MediaFormat mediaFormat, ItemType itemType, TranslationInfo translationInfo, List<Season> seasons) {
        super(itemId, ItemType.TV_SERIES, title, genre, language, seriesInfo );

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

