package model;

public class TranslationInfo {

    private final String translation;

    public TranslationInfo(String translation) {
        this.translation = translation;
    }
    public String getTranslationTypeOrLanguage() {
        return translation;
    }

    @Override
    public String toString() {
        return translation;
    }
}
