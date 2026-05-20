package model.enums;

import java.util.logging.Logger;

public enum TranslationInfo {

    SWEDISH("Swedish"),
    ENGLISH("English"),
    SPANISH("Spanish");

    private final String translation;

    TranslationInfo(String translation) {
        this.translation = translation;
    }

    public String getTranslationTypeOrLanguage() {
        return translation;
    }

    @Override
    public String toString() {
        return translation;
    }

    private static final Logger LOGGER = Logger.getLogger(TranslationInfo.class.getName());

    public static TranslationInfo fromString (String text){
        for (TranslationInfo translationInfo :  TranslationInfo.values() ) {
            if (translationInfo.translation.equalsIgnoreCase(text)) {
                return translationInfo;
            }
        }
        LOGGER.warning("Format " + text + " not supported");
        throw new IllegalArgumentException("Format " + text + " not supported");
    }
}
