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
/** Helper class to have a cleaner file creation for all countries **/
public class AllCountries {



    private Response response;
    public void countryReports(Response response) throws IOException {
        this.response = response;
        Files.createDirectories(Paths.get(Constants.ALL_COUNTRIES_REPORTS_DIRECTORY ));
        Files.createDirectories(Paths.get(Constants.ALL_COUNTRIES_REPORTS_DIRECTORY + "Continent/"));
        Files.createDirectories(Paths.get(Constants.ALL_COUNTRIES_REPORTS_DIRECTORY + "Region/"));

        allCountriesQuery(Constants.OTHER_COUNTRY_REPORTS + "All_Countries.txt", Query.ALL_COUNTRIES.label);
        for (Map.Entry<String,String> query: Query.countryByContinent().entrySet()) {
            String thisContinent = query.getKey().replace("/","");
            allCountriesQuery(Constants.CONTINENT_WIDE_COUNTRY_REPORTS + thisContinent + ".txt", query.getValue());
        }
        for (Map.Entry<String, String> query: Query.countryByRegion().entrySet()) {
            String region = query.getKey().replace("/", "_");
            allCountriesQuery(Constants.REGION_WIDE_Country_REPORTS + region + ".txt", query.getValue());
        }
    }

    public  void allCountriesQuery(String fileName, String query) {
        FileManager.createFile(fileName);
        FileManager.writeToFile(fileName, this.response.countriesByPopDesc(query));
    }
}
