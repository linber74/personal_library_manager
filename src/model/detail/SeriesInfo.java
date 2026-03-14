package model.detail;

public class SeriesInfo {

    private final  String seriesName;
    private final int partNumber;

    public SeriesInfo(String seriesName) {
        this.seriesName = seriesName;
        this.partNumber = 0;
    }

    public SeriesInfo(String seriesName, int partNumber) {
        this.seriesName = seriesName;
        this.partNumber = partNumber;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public int getPartNumber() {
        return partNumber;
    }

    @Override
    public String toString() {
        return "Serie: " + this.seriesName + ", Del: " + this.partNumber;
    }
}
