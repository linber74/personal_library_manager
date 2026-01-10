package model;

import java.util.ArrayList;
import java.util.List;

public class Season {

    private final int seasonNumber;

    private final List<Episode> episodes;

    public Season(int seasonNumber) {
        this.seasonNumber = seasonNumber;
        episodes = new ArrayList<>();
    }

    public Season(int seasonNumber, List<Episode> episodes) {
        this.seasonNumber = seasonNumber;
        this.episodes = new ArrayList<>(episodes);
    }

    public void addEpisode (Episode ep) {
        episodes.add(ep);
    }

    public List<Episode> getEpisodes () {
        return episodes;
    }

    @Override
    public String toString() {
        return "Säsong " + seasonNumber + " - " + episodes;
    }
}
