package com.napier.sem.database;

// a class to do queries as enums, to reduce user error, since all queries are constant

import com.napier.sem.constant.Constants;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public enum Query {
    ALL_COUNTRIES_BY_POP_DESC ("SELECT Code,country.name,Continent,Region, country.Population, city.name " +
            "FROM country JOIN city on city.id = Capital ORDER BY population DESC"),
    ALL_COUNTRIES_IN_ASIA_DESC(continentQuery(1)),
    ALL_COUNTRIES_IN_EUROPE_DESC(continentQuery(2)),
    ALL_COUNTRIES_IN_NA_DESC(continentQuery(3)),
    ALL_COUNTRIES_IN_AFRICA_DESC(continentQuery(4)),
    ALL_COUNTRIES_IN_SA_DESC(continentQuery(5)),
    ALL_COUNTRIES_IN_OCEANIA_DESC(continentQuery(6)),
    ALL_COUNTRIES_IN_ANTARCTICA_DESC(continentQuery(7)),

    ALL_CITIES_BY_POP_DESC("SELECT city.name, country.name, district, city.population FROM city JOIN country on CountryCode = code ORDER BY city.population DESC");

    public final String label;
    Query(String label) {
        this.label = label;
    }


    public static String continentQuery(int input) {
        String beg = "SELECT Code,country.name,Continent,Region, country.Population, city.name FROM country JOIN city on\n" +
                "    city.id = Capital WHERE continent =";
        String end = Constants.POP_DESC;
        switch (input) {
            case 1: return  beg + "\"Asia\"" + end;
            case 2: return  beg + "\"Europe\"" + end;
            case 3: return  beg + "\"North America\"" + end;
            case 4: return  beg + "\"Africa\"" + end;
            case 5: return  beg + "\"South America\"" + end;
            case 6: return  beg + "\"Oceania\"" + end;
            case 7: return  beg + "\"Antarctica\"" + end;
            default: return null;
        }
    }

    public static HashMap<String, String> regionQueries(java.sql.Connection con) {
        String beg = "SELECT Code,country.name,Continent,Region, country.Population, city.name FROM country JOIN city on\n" +
                "    city.id = Capital WHERE region =";
        String end = Constants.POP_DESC;
        HashMap<String, String> regionsQueries = new HashMap<>();
        try {
            Statement stm = con.createStatement();
            ResultSet rset = stm.executeQuery("SELECT region FROM country GROUP BY region;");
            while (rset.next()) {
                String region = rset.getString("Region");
                regionsQueries.put(region, beg + String.format("\"%s\" ", region) + end);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return regionsQueries;
    }

    public static HashMap<String, String> countryqueries(java.sql.Connection con) {
        String beg = "SELECT city.name,country.name,district,city.population FROM city JOIN country on" +
                " countryCode = code WHERE country.name =";
        String end = Constants.POP_DESC;
        HashMap<String, String> countryQueries = new HashMap<>();
        try {
            Statement stm = con.createStatement();
            ResultSet rset = stm.executeQuery("SELECT name FROM country;");
            while (rset.next()) {
                String country = rset.getString("name");
                countryQueries.put(country, beg + String.format("\"%s\" ", country) + end);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return countryQueries;
    }
}
