package com.napier.sem.database;

// a class to do queries as enums, to reduce user error, since all queries are constant

import com.napier.sem.FileManager;
import com.napier.sem.constant.Constants;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

public enum Query {

    /* query class, consisting of enums for static queries and methods to build queries on the go */
    ALL_COUNTRIES("SELECT Code,country.name,Continent,Region, country.Population, city.name " +
            "FROM country JOIN city on city.id = Capital ORDER BY population DESC"),
    ALL_CITIES("SELECT city.name, country.name, district, city.population" +
            " FROM city JOIN country on CountryCode = code ORDER BY city.population DESC"),
    ALL_CAPITAL_CITIES("SELECT city.name, country.name, city.Population " +
            "FROM country JOIN city on city.id = Capital ORDER BY population DESC");

    public final String label;
    private final static ArrayList<String> continents = FileManager.readFile(Constants.CONTINENT_DATA);
    private final static ArrayList<String> regions = FileManager.readFile(Constants.REGION_DATA);
    private final static ArrayList<String> countries = FileManager.readFile(Constants.COUNTRY_DATA);
    private final static ArrayList<String> districts = FileManager.readFile(Constants.DISTRICT_DATA);


    Query(String label) {
        this.label = label;
    }

    /* if desired all the methods can be refactored to use allInListByPop, result doesn't change but code would be a bit cleaner*/

    public static @NotNull HashMap<String, String> countryByContinent() {
        HashMap<String, String> continentQueries = new HashMap<>();

        String beg = "SELECT Code,country.name,Continent,Region, country.Population, city.name FROM country JOIN city on" +
                " city.id = Capital WHERE continent =";
        String end = Constants.POP_DESC;
        for (String continent : continents) {
            continentQueries.put(continent, beg + String.format("\"%s\" ", continent) + end);
        }
        return continentQueries;
    }

    public static @NotNull HashMap<String, String> countryByRegion() {
        HashMap<String, String> regionQueries = new HashMap<>();
        String beg = "SELECT Code,country.name,Continent,Region, country.Population, city.name FROM country JOIN city on\n" +
                "    city.id = Capital WHERE region =";
        String end = Constants.POP_DESC;
        for (String region : regions) {
            regionQueries.put(region, beg + String.format("\"%s\" ", region) + end);
        }
        return regionQueries;
    }

    public static @NotNull HashMap<String, String> cityByCountry() {
        HashMap<String, String> cityQueries = new HashMap<>();
        String beg = "SELECT city.name,country.name,district,city.population FROM city JOIN country on" +
                " countryCode = code WHERE country.name =";
        String end = Constants.POP_DESC;
        for (String country : countries) {
            cityQueries.put(country, beg + String.format("\"%s\" ", country) + end);
        }
        return cityQueries;
    }

    public static HashMap<String, String> cityByContinent() {
        return allInListByPop("SELECT city.name,country.name,district,city.population FROM city JOIN country on" +
                " countryCode = code WHERE continent =", continents);
    }

    public static HashMap<String, String> cityByRegion() {
        return allInListByPop("SELECT city.name,country.name,district,city.population \n" +
                "FROM city JOIN country on countryCode = code \n" +
                "WHERE Region =", regions);
    }

    public static HashMap<String, String> cityByDistrict() {
        return allInListByPop("SELECT city.name,country.name,district,city.population \n" +
                "FROM city JOIN country on countryCode = code \n" +
                "WHERE district =", districts);
    }

    public static HashMap<String, String> capitalsByContinent() {
        return allInListByPop("SELECT city.name, country.name, city.Population\n" +
                "FROM country JOIN city on city.id = Capital\n" +
                "WHERE continent =", continents);
    }

    public static HashMap<String, String> capitalsByRegion() {
        return allInListByPop("SELECT city.name, country.name, city.Population\n" +
                "FROM country JOIN city on city.id = Capital\n" +
                "WHERE region =", regions);
    }

    public static HashMap<String, String> allInListByPop(String beg, ArrayList<String> data) {
        if (data == null) {
            return null;
        }
        HashMap<String, String> returnable = new HashMap<>();
        String end = Constants.POP_DESC;
        for (String databit : data) {
            returnable.put(databit, beg + String.format("\"%s\" ", databit) + end);
        }
        return returnable;
    }
}
