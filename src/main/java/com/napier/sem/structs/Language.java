package com.napier.sem.structs;
//in- memory class for language reports
public  enum Language {
    ;

    /****/
    private final String name;

    private final int percentage;

    Language(String name, int percentage) {
        this.name = name;
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return "Language{" +
                "name='" + name  +
                ", percentage=" + percentage +
                '}';
    }
}
