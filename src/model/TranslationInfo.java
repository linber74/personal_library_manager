package model;

public enum TranslationInfo {

    SWEDISH("Svenska"),
    ENGLISH("Engelska"),
    SPANISH("Spanska");

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

    public static TranslationInfo fromString (String text){
        for (TranslationInfo translationInfo :  TranslationInfo.values() ) {
            if (translationInfo.translation.equalsIgnoreCase(text)) {
                return translationInfo;
            }
        }
        throw new IllegalArgumentException("Format " + text + " not supported");
    }
}
