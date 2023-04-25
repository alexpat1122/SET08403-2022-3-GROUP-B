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

    /** Main class **/
    public static void main(String[] args) throws IOException {
        if(args == null) {
            return;
        }
        if(args.length < 1) {
            Connection.connect("localhost:33060",30);
        }
        else {
            Connection.connect(args[0],3000);
        }

        if (connected(false)) {
            Response response = new DBResponse();
            createConstantFiles(true);
            Files.createDirectories(Paths.get(Constants.REPORTS_DIRECTORY));
            /************** comment below code out if you want to test stuff faster *****************************************
             * *************************************************************************
             */
           new AllCountries().countryReports(response);
            new AllCities().cityReports(response);
            new AllCapitalCities().cityReports(response);
            new TopN().allReports(N, response);
          new  AllPopulations().allPop(response);
           new PopulationFor().generateReport(response);
        new  SinglePopulationFor().singlePopulationsFor(CONTINENT, REGION, COUNTRY, DISTRICT, CITY,response);
            new AllLanguages().allLanguages(response);
        } else {
            System.out.println("Not connected to database");
        }
        Connection.disconnect();
    }

    public static void createConstantFiles(boolean test) throws IOException {
        if (connected(!test)) {
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


    public static boolean connected(boolean test) {
        if(test) {
            return true;
        }
        return Connection.con != null;
    }
}
