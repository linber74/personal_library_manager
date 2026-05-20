package model.enums;

import java.util.logging.Logger;

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

    private static final Logger LOGGER = Logger.getLogger(FanficType.class.getName());

    public static FanficType fromString (String text){
        for (FanficType fanficType :  FanficType.values() ) {
            if (fanficType.ficType.equalsIgnoreCase(text)) {
                return fanficType;
            }
        }
        LOGGER.warning("Format " + text + " not supported");
        throw new IllegalArgumentException("Format " + text + " not supported");
    }
}
