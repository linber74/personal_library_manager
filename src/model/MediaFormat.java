package model;

public enum MediaFormat {

    DIGITAL ("Digitalt"),
    DVD ("DVD"),
    BLURAY ("Blu-ray");

    private final String format;

    MediaFormat(String format){
        this.format = format;
    }

    public String getFormat(){
        return this.format;
    }

    @Override
    public String toString() {
        return format;
    }
}
