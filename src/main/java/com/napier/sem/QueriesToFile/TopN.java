package com.napier.sem.QueriesToFile;

import com.napier.sem.FileManager;
import com.napier.sem.constant.Constants;
import com.napier.sem.database.Query;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class TopN {
    /** Reports of top N reportable units (cities, countries etc) in a category) **/
    final static String CONTINENT_FOLDER = Constants.TOPN + "Continent/";
    final  static String REGION_FOLDER = Constants.TOPN + "Region/";


    public  static void allReports(java.sql.Connection con, int n) throws IOException {
        final String LIMIT_FOR_N = " LIMIT " + n;
        Files.createDirectories(Paths.get(Constants.TOPN));
        Files.createDirectories(Paths.get(CONTINENT_FOLDER));
        Files.createDirectories(Paths.get(REGION_FOLDER));


        countryReports(con,n, LIMIT_FOR_N);
        cityReports(con, n, LIMIT_FOR_N);
        capitalReports(con, n, LIMIT_FOR_N);
    }

    private static void capitalReports(Connection con, int n, String limitForN) throws IOException {
        String continentPath = CONTINENT_FOLDER + "Top_" + n + "_Capital_Cities/";
        String regionPath = REGION_FOLDER + "Top_" + n + "_Capital_Cities/";
        Files.createDirectories(Paths.get(continentPath));
        Files.createDirectories(Paths.get(regionPath));
        topNCapitalCities(Constants.TOPN + "Top_" + n + "_Capital_Cities", Query.ALL_CAPITAL_CITIES.label +
                limitForN, con);
        reportsForAMapCapitalCity(continentPath,Query.capitalsByContinent(),con, limitForN);
        reportsForAMapCapitalCity(regionPath,Query.capitalsByRegion(),con,limitForN);
    }

    private static void countryReports(java.sql.Connection con, int n, String limitForN) throws IOException {
        final String continentPath = CONTINENT_FOLDER + "Top_" + n + "_Countries/";
        final String regionPath = REGION_FOLDER + "Top_" + n + "_Countries/";
        Files.createDirectories(Paths.get(continentPath));
        Files.createDirectories(Paths.get(regionPath));
        topNCountries(Constants.TOPN +
                "Top_" + n + "_Countries.txt", Query.ALL_COUNTRIES.label + limitForN, con);
       reportsForAMapCountry(continentPath,Query.countryByContinent(), con, limitForN);
       reportsForAMapCountry(regionPath, Query.countryByRegion(), con, limitForN);
    }

    private static void cityReports(java.sql.Connection con, int n, String limitForN) throws IOException {
        final String continentPath = CONTINENT_FOLDER + "Top_" + n + "_Cities/";
        final String regionPath = REGION_FOLDER + "Top_" + n + "_Cities/";
        final  String countryPath = Constants.TOPN + "Country/";
        final String districtPath = Constants.TOPN + "District/";
        Files.createDirectories(Paths.get(continentPath));
        Files.createDirectories(Paths.get(regionPath));
        Files.createDirectories(Paths.get(countryPath));
        Files.createDirectories(Paths.get(districtPath));
        topNCities(Constants.TOPN + "Top_" + n + "_Cities.txt", Query.ALL_CITIES.label + limitForN, con);
        reportsForAMapCity(continentPath,Query.cityByContinent(), con, limitForN);
        reportsForAMapCity(regionPath, Query.cityByRegion(), con, limitForN);
        reportsForAMapCity(countryPath, Query.cityByCountry(), con, limitForN);
        reportsForAMapCity(districtPath, Query.cityByDistrict(), con, limitForN);
    }

    /*method to shorten the generating of reports, you don't have to use that if it feels unclear */
    private static void reportsForAMapCountry(String constantFileName,
                                              HashMap<String, String> data, java.sql.Connection con, String limitForN) {

        for (Map.Entry<String, String> query : data.entrySet()) {
            String databit = query.getKey().replace("/", "_");
            topNCountries(constantFileName + databit + ".txt", query.getValue() + limitForN, con);
        }
    }

    private static void reportsForAMapCity(String constantFileName,
                                       HashMap<String, String> data, java.sql.Connection con, String limitForN) {

        for (Map.Entry<String, String> query : data.entrySet()) {
            String databit = query.getKey().replace("/", "_");
            topNCities(constantFileName + databit + ".txt", query.getValue() + limitForN, con);
        }
    }

    private static void reportsForAMapCapitalCity(String constantFileName,
                                           HashMap<String, String> data, java.sql.Connection con, String limitForN) {

        for (Map.Entry<String, String> query : data.entrySet()) {
            String databit = query.getKey().replace("/", "_");
            topNCapitalCities(constantFileName + databit + ".txt", query.getValue() + limitForN, con);
        }
    }

    private static void topNCountries(String fileName, String query, java.sql.Connection con) {
        FileManager.createFile(fileName);
        FileManager.writeToFile(fileName, ResponseFromDB.countriesByPopDesc(con, query));
    }

    private static void topNCities(String fileName, String query, java.sql.Connection con) {
        FileManager.createFile(fileName);
        FileManager.writeToFile(fileName, ResponseFromDB.citiesByPopDesc(con, query));
    }

    private static void topNCapitalCities(String fileName, String query, java.sql.Connection con) {
        FileManager.createFile(fileName);
        FileManager.writeToFile(fileName, ResponseFromDB.capitalCitiesByPopDesc(con, query));
    }
}
