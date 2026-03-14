package model.enums;

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

    public static FanficType fromString (String text){
        for (FanficType fanficType :  FanficType.values() ) {
            if (fanficType.ficType.equalsIgnoreCase(text)) {
                return fanficType;
            }
        }
        throw new IllegalArgumentException("Format " + text + " not supported");
    }
}
