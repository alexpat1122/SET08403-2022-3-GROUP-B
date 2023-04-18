package com.napier.sem;

import com.napier.sem.QueriesToFile.*;
import com.napier.sem.constant.Constants;
import com.napier.sem.database.Connection;
import com.napier.sem.database.Query;
import com.napier.sem.database.SetupQueries;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class App {

    /***main class that will call all the files in docker**/

    private static java.sql.Connection con = null;
    private static final int N = 5;

    private static final String CONTINENT = "Europe";
    private static final String REGION = "North America";

    private static final String COUNTRY = "Lithuania";

    private static final String DISTRICT = "Kabol";

    private static final String CITY = "Edinburgh";

    public static void main(String[] args) throws IOException {
        con = Connection.connect();
        if (connected(con)) {
            createConstantFiles();
            Files.createDirectories(Paths.get(Constants.REPORTS_DIRECTORY));
            /************** comment below code out if you want to test stuff faster *****************************************
             * *************************************************************************
             */
//            AllCountries.countryReports(con);
//            AllCities.cityReports(con);
//            AllCapitalCities.cityReports(con);
//            TopN.allReports(con,N);
            AllPopulations.allPop(con);
//            PopulationFor.generateReport(con);
//            SinglePopulationFor.singlePopulationsFor(con, CONTINENT, REGION, COUNTRY, DISTRICT, CITY);
            AllLanguages.allLanguages(con);
        } else {
            System.out.println("Not connected to database");
        }
        Connection.disconnect(con);
    }

    public static void createConstantFiles() throws IOException {
        if (connected(con)) {
            String continentPath = Constants.CONTINENT_DATA;
            Files.createDirectories(Paths.get(Constants.CONSTANTS_DIRECTORY));
            if (FileManager.createFile(continentPath)) {
                FileManager.writeToFile(continentPath, SetupQueries.continentList(con));
            }
            String regionPath = Constants.REGION_DATA;
            if (FileManager.createFile(regionPath)) {
                FileManager.writeToFile(regionPath, SetupQueries.regionList(con));
            }
            String countryPath = Constants.COUNTRY_DATA;
            if (FileManager.createFile(countryPath)) {
                FileManager.writeToFile(countryPath, SetupQueries.countryList(con));
            }
            String districtPath = Constants.DISTRICT_DATA;
            if (FileManager.createFile(districtPath)) {
                FileManager.writeToFile(districtPath, SetupQueries.districtList(con));
            }
        }
    }


    public static boolean connected(java.sql.Connection con) {
        return con != null;
    }
}
