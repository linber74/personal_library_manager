package model;

public enum FilmType {

    FILM ("Film"),
    TV_SERIES ("TV-serie"),
    OTHER ("Annat");

    private final String type;

    FilmType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type;
    }

    public static FilmType fromString (String text){
        for (FilmType filmType :  FilmType.values() ) {
            if (filmType.type.equalsIgnoreCase(text)) {
                return filmType;
            }
        }
        throw new IllegalArgumentException("Format " + text + " not supported");
    }
}
