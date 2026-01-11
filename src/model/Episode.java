package model;

public class Episode {

    private final int episodeNumber;
    private final String episodeName;

    public Episode(int episodeNumber, String episodeName) {
        this.episodeNumber = episodeNumber;
        this.episodeName = episodeName;
    }

    public Episode(int episodeNumber) {
        this.episodeNumber = episodeNumber;
        this.episodeName = null;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public String getEpisodeName() {
        return episodeName;
    }

    @Override
    public String toString() {
        if (episodeName == null || episodeName.isBlank()) {
            return "Avsnitt " +  episodeNumber;
        }
        return "Avsnitt " + getEpisodeNumber() + " - " + getEpisodeName();
    }
}
