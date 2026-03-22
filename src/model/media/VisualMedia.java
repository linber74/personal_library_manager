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
        return super.toString();
    }
}
