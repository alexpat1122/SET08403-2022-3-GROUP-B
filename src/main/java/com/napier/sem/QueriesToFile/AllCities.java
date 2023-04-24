package com.napier.sem.QueriesToFile;

import com.napier.sem.FileManager;
import com.napier.sem.constant.Constants;
import com.napier.sem.database.Query;
import com.napier.sem.structs.City;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AllCities {

    /** think some tests for this would be good, for example passed a wrong sql query, though it is testing SQL queries being correct not
    the method working... dunno
     **/

    private Response response;
    public  void cityReports(Response response) throws IOException {
        this.response = response;
        Files.createDirectories(Paths.get(Constants.ALL_CITIES_REPORTS_DIRECTORY));
        Files.createDirectories(Paths.get(Constants.ALL_CITIES_REPORTS_DIRECTORY + "Continent/"));
        Files.createDirectories(Paths.get(Constants.ALL_CITIES_REPORTS_DIRECTORY + "Country/"));
        Files.createDirectories(Paths.get(Constants.ALL_CITIES_REPORTS_DIRECTORY + "Region/"));
        Files.createDirectories(Paths.get(Constants.ALL_CITIES_REPORTS_DIRECTORY + "District/"));

        allCitiesQuery(Constants.OTHER_CITY_REPORTS + "All_Cities.txt", Query.ALL_CITIES.label);
        for (Map.Entry<String, String> query : Query.cityByCountry().entrySet()) {
            allCitiesQuery(Constants.COUNTRY_WIDE_CITY_REPORTS + query.getKey() + ".txt", query.getValue());
        }
        for (Map.Entry<String, String> query : Query.cityByContinent().entrySet()) {
            allCitiesQuery(Constants.CONTINENT_WIDE_CITY_REPORTS + query.getKey() + ".txt", query.getValue());
        }
        reportsForAMap(Constants.REGION_WIDE_CITY_REPORTS, Query.cityByRegion());
        reportsForAMap(Constants.DISTRICT_WIDE_CITY_REPORTS, Query.cityByDistrict());
    }

    /*method to shorten the generating of reports, you don't have to use that if it feels unclear */
    public  void reportsForAMap(String constantFileName, HashMap<String, String> data) {

        for (Map.Entry<String, String> query : data.entrySet()) {
            String databit = query.getKey().replace("/", "_");
            allCitiesQuery(constantFileName + databit + ".txt", query.getValue());
        }
    }

    public  void allCitiesQuery(String fileName, String query) {
        FileManager.createFile(fileName);
        FileManager.writeToFile(fileName, this.response.citiesByPopDesc(query));
    }
}
