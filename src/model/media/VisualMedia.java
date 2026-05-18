package model.media;

import model.detail.SeriesInfo;
import model.enums.ItemType;
import model.enums.MediaFormat;
import model.enums.TranslationInfo;

import java.util.ArrayList;
import java.util.List;

public abstract class VisualMedia extends LibraryItem {

    private final String director;
    private final List<String> actors;
    private final MediaFormat mediaFormat;
    private final TranslationInfo translationInfo;

    public VisualMedia (int itemId, ItemType itemType, String title, List<String> genre, String language, Integer publishYear,
                        SeriesInfo seriesInfo, String director, List<String> actors, MediaFormat mediaFormat, TranslationInfo translationInfo) {
        super(itemId, itemType, title, genre, language, publishYear, seriesInfo);
        this.director = director;
        if (actors != null) {
            this.actors = new ArrayList<>(actors);
        }
        else {
            this.actors = new ArrayList<>();
        }
        this.mediaFormat = mediaFormat;
        this.translationInfo = translationInfo;
    }

    public String getDirector() {
        return director;
    }

    public List<String> getActors() {
        return actors;
    }

    public MediaFormat getMediaFormat() {
        return mediaFormat;
    }

    public TranslationInfo getTranslationInfo() {
        return translationInfo;
    }

    public boolean isTranslated() {
        return translationInfo != null;
    }

    public  VisualMedia addActor(String actor) {
        actors.add(actor);
        return this;
    }

    // TODO: Adjust toString() when final output format is decided.
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getTitle()).append("\n");

        if (director != null) {
            sb.append("Director: ").append(director).append("\n");
        } else {
            sb.append("Director: Unknown\n");
        }

        if (!getActors().isEmpty()){sb.append("Actors: ").append(getActors()).append("\n");}

        sb.append("Genre: ").append(getGenre()).append("\n");

        if (getSeriesInfo() != null) {
            sb.append("SeriesInfo: ").append(getSeriesInfo()).append("\n");
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
