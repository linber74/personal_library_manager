package model.enums;

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

    public static MediaFormat fromString (String text){
        for (MediaFormat mediaFormat :  MediaFormat.values() ) {
            if (mediaFormat.format.equalsIgnoreCase(text)) {
                return mediaFormat;
            }
        }
        throw new IllegalArgumentException("Format " + text + " not supported");
    }
}

