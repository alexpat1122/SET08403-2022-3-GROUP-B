package com.napier.sem.database;

import com.napier.sem.constant.Constants;

import java.util.ArrayList;
import java.util.HashMap;

public final class Request {

    private static ArrayList<String> continents = Queries.continentList();

    public static HashMap<String, String> countriesByContinent() {
        HashMap<String,String> requests = new HashMap<>();
       String beg = "SELECT Code,country.name,Continent,Region, country.Population, city.name FROM country JOIN city on" +
               " city.id = Capital WHERE continent =";
       String end = Constants.POP_DESC;
        for (String continent: continents) {
            requests.put(continent.replace("\"",""),beg + continent + end);
        }
        return requests;
    }

}
