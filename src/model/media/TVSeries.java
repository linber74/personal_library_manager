package model.media;

import model.detail.Season;
import model.detail.SeriesInfo;
import model.enums.ItemType;
import model.enums.MediaFormat;
import model.enums.TranslationInfo;

import java.util.ArrayList;
import java.util.List;

public class TVSeries extends VisualMedia {

    private final List<Season> seasons;

    public TVSeries(int itemId, String title, List<String> genre, String language, SeriesInfo seriesInfo,
                    String director, List<String> actors, MediaFormat mediaFormat, TranslationInfo translationInfo, List<Season> seasons) {
        super(itemId, ItemType.TV_SERIES, title, genre, language, seriesInfo, director, actors, mediaFormat, translationInfo);

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

