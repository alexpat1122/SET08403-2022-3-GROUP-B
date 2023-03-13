package com.napier.sem.QueriesToFile;

import com.napier.sem.FileManager;
import com.napier.sem.constant.Constants;
import com.napier.sem.database.Query;
import com.napier.sem.structs.City;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

public class AllCities {

    public static void  cityReports(java.sql.Connection con) {
        allCitiesQuery(Constants.OTHER_CITY_REPORTS + "AllCities.txt", Query.ALL_CITIES_BY_POP_DESC.label, con);
        for (Map.Entry<String, String> query: Query.countryqueries(con).entrySet()) {
            allCitiesQuery(Constants.COUNTRY_WIDE_CITY_REPORTS + query.getKey() + ".txt", query.getValue(), con);
        }
    }

    private static void allCitiesQuery(String fileName, String query, java.sql.Connection con) {
        FileManager.createFile(fileName);
        FileManager.writeCountriesToFile(fileName, citiesByPopDesc(con, query));
    }

    private static ArrayList<String> citiesByPopDesc(java.sql.Connection con, String query) {
        ArrayList<String> cities = new ArrayList<>();
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(query);
            // Return new country if valid.
            // Check one is returned

            while (rset.next()) {
                String name = rset.getString("city.Name");
                String country = rset.getString("country.Name");
                String district = rset.getString("district");
                int population = rset.getInt("city.population");
                cities.add(new City(name,country,population,district).toString());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
        }
        return cities;
    }
}
