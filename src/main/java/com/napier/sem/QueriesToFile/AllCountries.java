package com.napier.sem.QueriesToFile;

import com.napier.sem.FileManager;
import com.napier.sem.constant.Constants;
import com.napier.sem.database.Query;
import com.napier.sem.structs.Continent;
import com.napier.sem.structs.Country;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

public class AllCountries {

    /** Helper class to have a cleaner file creation for all countries **/

    public static void countryReports(java.sql.Connection con) throws IOException {
        Files.createDirectories(Paths.get(Constants.ALL_COUNTRIES_REPORTS_DIRECTORY ));
        Files.createDirectories(Paths.get(Constants.ALL_COUNTRIES_REPORTS_DIRECTORY + "Continent/"));
        Files.createDirectories(Paths.get(Constants.ALL_COUNTRIES_REPORTS_DIRECTORY + "Region/"));

        allCountriesQuery(Constants.OTHER_COUNTRY_REPORTS + "All_Countries.txt", Query.ALL_COUNTRIES.label, con);
        for (Map.Entry<String,String> query: Query.countryByContinent().entrySet()) {
            String thisContinent = query.getKey().replace("/","");
            allCountriesQuery(Constants.CONTINENT_WIDE_COUNTRY_REPORTS + thisContinent + ".txt", query.getValue(),con);
        }
        for (Map.Entry<String, String> query: Query.countryByRegion().entrySet()) {
            String region = query.getKey().replace("/", "_");
            allCountriesQuery(Constants.REGION_WIDE_Country_REPORTS + region + ".txt", query.getValue(), con);
        }
    }

    public static void allCountriesQuery(String fileName, String query, java.sql.Connection con) {
        FileManager.createFile(fileName);
        FileManager.writeToFile(fileName, ResponseFromDB.countriesByPopDesc(con, query));
    }
}
