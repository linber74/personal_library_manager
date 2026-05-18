package model.media;

import model.detail.Episode;
import model.detail.Season;
import model.detail.SeriesInfo;
import model.enums.ItemType;
import model.enums.MediaFormat;
import model.enums.TranslationInfo;

import java.util.ArrayList;
import java.util.List;

public class TVSeries extends VisualMedia {

    private final List<Season> seasons;

    public TVSeries(int itemId, String title, List<String> genre, String language, Integer publishYear, SeriesInfo seriesInfo,
                    String director, List<String> actors, MediaFormat mediaFormat, TranslationInfo translationInfo, List<Season> seasons) {
        super(itemId, ItemType.TV_SERIES, title, genre, language, publishYear, seriesInfo, director, actors, mediaFormat, translationInfo);

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
        StringBuilder sb = new StringBuilder();
        sb.append(getTitle()).append("\n");

        if (getDirector() != null) {
            sb.append("Director: ").append(getDirector()).append("\n");
        } else {
            sb.append("Director: Unknown\n");
        }

        if (!getActors().isEmpty()){sb.append("Actors: ").append(getActors()).append("\n");}

        sb.append("Genre: ").append(getGenre()).append("\n");

        if (getSeriesInfo() != null) {
            sb.append("SeriesInfo: ").append(getSeriesInfo()).append("\n");
        }

        for (Season season : getSeasons()) {
            sb.append("Season: ")
                    .append(season.getSeasonNumber()).append("\n");
            for (Episode episode : season.getEpisodes()) {
                sb.append(" ").append(episode).append("\n");
            }
        }

        sb.append("Type: ").append(getItemType()).append("\n");

        sb.append("Format: ").append(getMediaFormat()).append("\n");

        sb.append("Language: ").append(getLanguage()).append("\n");

        if (getTranslationInfo() != null) {
            sb.append("TranslationInfo: ").append(getTranslationInfo()).append("\n");
        }

        if (getPublishYear() != null) {
            sb.append("PublishYear: ").append(getPublishYear()).append("\n");
        }
        return sb.toString();
    }
}

