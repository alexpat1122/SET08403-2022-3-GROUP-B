package com.napier.sem.structs;
//in- memory class for language reports
public  enum Language {

    /**/
    CHINESE, ENGLISH, HINDI, SPANISH, ARABIC;

    public static String getLanguage(Language language) {
        switch (language) {
            case HINDI: return "Hindi";
            case ARABIC: return "Arabic";
            case ENGLISH: return "English";
            case SPANISH: return "Spanish";
            case CHINESE: return "Chinese";
            default: return "Not a language";
        }
    }
}
