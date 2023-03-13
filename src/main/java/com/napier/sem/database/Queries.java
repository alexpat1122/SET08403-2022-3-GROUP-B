package com.napier.sem.database;

import java.util.ArrayList;

public final class Queries {

    ArrayList<String> continents = continentList();


    static ArrayList<String> continentList() {
        ArrayList<String> continents = new ArrayList<>();
        continents.add(Enums.ASIA.label);
        continents.add(Enums.EUROPE.label);
        continents.add(Enums.NA.label);
        continents.add(Enums.SA.label);
        continents.add(Enums.OCEANIA.label);
        continents.add(Enums.AFRICA.label);
        continents.add(Enums.ANTARCTICA.label);
        return continents;
    }
}
