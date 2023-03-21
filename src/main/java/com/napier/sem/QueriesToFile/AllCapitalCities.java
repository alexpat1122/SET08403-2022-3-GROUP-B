package com.napier.sem.QueriesToFile;

import com.napier.sem.FileManager;
import com.napier.sem.constant.Constants;
import com.napier.sem.database.Query;
import com.napier.sem.structs.CapitalCity;
import org.jetbrains.annotations.NotNull;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AllCapitalCities {


    /**/

    public static void cityReports(java.sql.Connection con) throws IOException {
        Files.createDirectories(Paths.get(Constants.ALL_CAPITAL_CITY_REPORTS_DIRECTORY));
        Files.createDirectories(Paths.get(Constants.ALL_CAPITAL_CITY_REPORTS_DIRECTORY + "Continent/"));
        Files.createDirectories(Paths.get(Constants.ALL_CAPITAL_CITY_REPORTS_DIRECTORY + "Region/"));

        allCitiesQuery(Constants.OTHER_CAPITAL_CITY_REPORTS + "All_Capital_Cities.txt", Query.ALL_CAPITAL_CITIES.label, con);
        reportsForAMap(Constants.CONTINENT_WIDE_CAPITAL_CITY_REPORTS, Query.capitalsByContinent(), con);
        reportsForAMap(Constants.REGION_WIDE_CAPITAL_CITY_REPORTS, Query.capitalsByRegion(), con);
    }


    private static void reportsForAMap(String constantFileName, @NotNull HashMap<String, String> data, java.sql.Connection con) {

        for (Map.Entry<String, String> query : data.entrySet()) {
            String databit = query.getKey().replace("/", ":");
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
                int population = rset.getInt("city.population");
                cities.add(new CapitalCity(name, country, population).toString());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
        }
        return cities;
    }
}
