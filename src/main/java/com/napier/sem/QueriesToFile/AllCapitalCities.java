package com.napier.sem.QueriesToFile;

import com.napier.sem.FileManager;
import com.napier.sem.constant.Constants;
import com.napier.sem.database.Query;
import com.napier.sem.structs.CapitalCity;



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

    /**Generates all capital city reports**/

    private  Response response;

    public  void cityReports(Response response) throws IOException {
        this.response = response;
        Files.createDirectories(Paths.get(Constants.ALL_CAPITAL_CITY_REPORTS_DIRECTORY));
        Files.createDirectories(Paths.get(Constants.ALL_CAPITAL_CITY_REPORTS_DIRECTORY + "Continent/"));
        Files.createDirectories(Paths.get(Constants.ALL_CAPITAL_CITY_REPORTS_DIRECTORY + "Region/"));

        allCitiesQuery(Constants.OTHER_CAPITAL_CITY_REPORTS + "All_Capital_Cities.txt", Query.ALL_CAPITAL_CITIES.label);
        reportsForAMap(Constants.CONTINENT_WIDE_CAPITAL_CITY_REPORTS, Query.capitalsByContinent());
        reportsForAMap(Constants.REGION_WIDE_CAPITAL_CITY_REPORTS, Query.capitalsByRegion());
    }


    public void reportsForAMap(String constantFileName,  HashMap<String, String> data) {

        for (Map.Entry<String, String> query : data.entrySet()) {
            String databit = query.getKey().replace("/", "_");
            allCitiesQuery(constantFileName + databit + ".txt", query.getValue());
        }
    }

    public  void allCitiesQuery(String fileName, String query) {
        FileManager.createFile(fileName);
        FileManager.writeToFile(fileName, this.response.capitalCitiesByPopDesc(query));
    }



}
