package com.napier.sem.structs;

//in- memory class for continent reports
public enum Continent {

    /**/
    ASIA, EUROPE, NORTH_AMERICA, AFRICA, OCEANIA, ANTARCTICA, SOUTH_AMERICA, INCORRECT_DATA_FORMAT_PROVIDED;

    public static Continent getContinent(String continent) {
        switch (continent) {
            case "Asia": return ASIA;
            case "Europe": return EUROPE;
            case "North America": return NORTH_AMERICA;
            case "Africa": return AFRICA;
            case "Oceania": return OCEANIA;
            case "Antarctica": return ANTARCTICA;
            case "South America": return SOUTH_AMERICA;
            default: return INCORRECT_DATA_FORMAT_PROVIDED;
        }
    }
}
