package com.napier.sem.database;

public enum Enums {

    ASIA ("\"Asia\"" ) ,
    EUROPE("\"Europe\""),
    NA("\"North America\""),
    AFRICA("\"Africa\""),
    SA("\"South America\""),
    OCEANIA("\"Oceania\""),
    ANTARCTICA("\"Antarctica\"");
    public final String label;
    Enums(String label) {
        this.label = label;
    }
}
