package model;

public enum ItemType {

    BOOK ("Bok"),
    FILM ("Film"),
    GAME ("Spel");

    public final String type;

    ItemType(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type;
    }
}
