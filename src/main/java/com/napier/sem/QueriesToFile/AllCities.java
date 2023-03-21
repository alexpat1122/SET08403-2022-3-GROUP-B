package com.napier.sem.QueriesToFile;

import com.napier.sem.FileManager;
import com.napier.sem.constant.Constants;
import com.napier.sem.database.Query;
import com.napier.sem.structs.City;
import org.jetbrains.annotations.NotNull;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AllCities {

    /* think some tests for this would be good, for example passed a wrong sql query, though it is testing SQL queries being correct not
    the method working... dunno
     */

    public static void cityReports(java.sql.Connection con) throws IOException {

        Files.createDirectories(Paths.get(Constants.ALL_CITIES_REPORTS_DIRECTORY));
        Files.createDirectories(Paths.get(Constants.ALL_CITIES_REPORTS_DIRECTORY + "Continent/"));
        Files.createDirectories(Paths.get(Constants.ALL_CITIES_REPORTS_DIRECTORY + "Country/"));
        Files.createDirectories(Paths.get(Constants.ALL_CITIES_REPORTS_DIRECTORY + "Region/"));
        Files.createDirectories(Paths.get(Constants.ALL_CITIES_REPORTS_DIRECTORY + "District/"));

        allCitiesQuery(Constants.OTHER_CITY_REPORTS + "All_Cities.txt", Query.ALL_CITIES.label, con);
        for (Map.Entry<String, String> query : Query.cityByCountry().entrySet()) {
            allCitiesQuery(Constants.COUNTRY_WIDE_CITY_REPORTS + query.getKey() + ".txt", query.getValue(), con);
        }
        for (Map.Entry<String, String> query : Query.cityByContinent().entrySet()) {
            allCitiesQuery(Constants.CONTINENT_WIDE_CITY_REPORTS + query.getKey() + ".txt", query.getValue(), con);
        }
        reportsForAMap(Constants.REGION_WIDE_CITY_REPORTS, Query.cityByRegion(), con);
        reportsForAMap(Constants.DISTRICT_WIDE_CITY_REPORTS, Query.cityByDistrict(), con);
    }

    /*method to shorten the generating of reports, you don't have to use that if it feels unclear */
    private static void reportsForAMap(String constantFileName, @NotNull HashMap<String, String> data, java.sql.Connection con) {

        for (Map.Entry<String, String> query : data.entrySet()) {
            String databit = query.getKey().replace("/", "_");
            allCitiesQuery(constantFileName + databit + ".txt", query.getValue(), con);
        }
    }

    private static void allCitiesQuery(String fileName, String query, java.sql.Connection con) {
        FileManager.createFile(fileName);
        FileManager.writeToFile(fileName, citiesByPopDesc(con, query));
    }


    public static @NotNull ArrayList<String> citiesByPopDesc(java.sql.Connection con, String query) {

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
                cities.add(new City(name, country, population, district).toString());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
        }
        return cities;
    }
}
