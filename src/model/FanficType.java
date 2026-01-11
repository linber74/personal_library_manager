package model;

public enum FanficType {

    CANON ("Canon"),
    AU ("AU"),
    UBER ("Über"),
    ORIGINAL ("Original/Okänt");

    private final String ficType;

    FanficType(String ficType) {
        this.ficType = ficType;
    }

    @Override
    public String toString() {
        return ficType;
    }
}
