package model;

import java.util.ArrayList;
import java.util.List;

public class TVSeries extends Film {

    private final List<Season> seasons;

    public TVSeries(String itemId, String title, String genre, String language, SeriesInfo seriesInfo, String director,
                    List<String> actors, MediaFormat mediaFormat, TranslationInfo translationInfo, List<Season> seasons) {
        super(itemId,  title, genre, language, seriesInfo, director, actors, mediaFormat, FilmType.TV_SERIES, translationInfo);

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

