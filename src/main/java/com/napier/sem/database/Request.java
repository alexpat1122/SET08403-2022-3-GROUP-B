package com.napier.sem.database;

import com.napier.sem.constant.Constants;
import com.napier.sem.structs.Continent;
import com.napier.sem.structs.Country;

import java.sql.*;
import java.util.ArrayList;

public class Request {



    public static void selectRequest(String type, java.sql.Connection con) {

        switch (type) {
            case Constants.ALL_COUNTRIES_DESC: {
                String query = Query.ALL_COUNTRIES_BY_POP_DESC.label;
                countriesByPopDesc(con, query);
                break;
            }
            default: System.out.println("No such operation possible");
        }
    }

    public static void countriesInAContinent(String type, java.sql.Connection con, int input) {
        switch (type) {
            case Constants.ALL_COUNTRIES_IN_CONTINENT_DESC: {
               String query = String.valueOf(selectCountriesByContinent(input));
               countriesByPopDesc(con, query);
                break;
            }
            default: System.out.println("No such operation possible");
            }

        }
        private static void countriesByPopDesc(java.sql.Connection con, String query) {
        ArrayList<Country> countries = new ArrayList<>();
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(query);
            // Return new country if valid.
            // Check one is returned
            while (rset.next()) {
                String code = rset.getString("Code");
                String name = rset.getString("country.Name");
                Continent continent = Continent.getContinent(rset.getString("Continent"));
                String region = rset.getString("Region");
                int population = rset.getInt("country.Population");
                String capital = rset.getString("city.name");
                countries.add(new Country(code, name, continent, region, population, capital));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
        }
        for (Country country:countries) {
            System.out.println(country);
        }
    }

    private static String selectCountriesByContinent(int option) {
        switch (option) {
            case 1: return Query.ALL_COUNTRIES_IN_ASIA_DESC.label;
            case 2: return  Query.ALL_COUNTRIES_IN_EUROPE_DESC.label;
            case 3: return  Query.ALL_COUNTRIES_IN_NA_DESC.label;
            case 4: return  Query.ALL_COUNTRIES_IN_AFRICA_DESC.label;
            case 5: return  Query.ALL_COUNTRIES_IN_SA_DESC.label;
            case 6: return  Query.ALL_COUNTRIES_IN_OCEANIA_DESC.label;
            case 7: return  Query.ALL_COUNTRIES_IN_ANTARCTICA_DESC.label;
            default: return null;
        }
}
}
