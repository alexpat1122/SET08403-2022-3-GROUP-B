package com.napier.sem.QueriesToFile;

import com.napier.sem.FileManager;
import com.napier.sem.constant.Constants;
import com.napier.sem.database.Query;
import com.napier.sem.database.Request;
import com.napier.sem.structs.Continent;
import com.napier.sem.structs.Country;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AllCountries {

    public static void countryReports(java.sql.Connection con) {
        allCountriesQuery(Constants.OTHER_COUNTRY_REPORTS + "All_Countries.txt", Query.ALL_COUNTRIES_BY_POP_DESC.label, con);
        for (Map.Entry<String,String> continent: Request.countriesByContinent().entrySet()) {
            String thisContinent = continent.getKey().replace("/","");
            allCountriesQuery(Constants.CONTINENT_WIDE_COUNTRY_REPORTS + thisContinent + ".txt", continent.getValue(),con);
        }
        HashMap<String,String> regionQueries = Query.regionQueries(con);
        for (Map.Entry<String, String> query: regionQueries.entrySet()) {
           String region = query.getKey().replace("/",":");
            allCountriesQuery(Constants.REGION_WIDE_Country_REPORTS + region + ".txt", query.getValue(), con);
        }
    }

    public static void allCountriesQuery(String fileName, String query, java.sql.Connection con) {
        FileManager.createFile(fileName);
        FileManager.writeCountriesToFile(fileName, countriesByPopDesc(con, query));
    }


    private static ArrayList<String> countriesByPopDesc(java.sql.Connection con, String query) {
        ArrayList<String> countries = new ArrayList<>();
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
                countries.add(new Country(code, name, continent, region, population, capital).toString());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
        }
        return countries;
    }
}
