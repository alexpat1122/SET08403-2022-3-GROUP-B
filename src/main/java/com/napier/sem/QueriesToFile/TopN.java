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
/** Reports of top N reportable units (cities, countries etc) in a category) **/
public class TopN {

   private final static String CONTINENT_FOLDER = Constants.TOPN + "Continent/";
   private final  static String REGION_FOLDER = Constants.TOPN + "Region/";

    private Response response;


    public  void allReports( int n, Response response) throws IOException {
        this.response = response;
        final String LIMIT_FOR_N = " LIMIT " + n;
        Files.createDirectories(Paths.get(Constants.TOPN));
        Files.createDirectories(Paths.get(CONTINENT_FOLDER));
        Files.createDirectories(Paths.get(REGION_FOLDER));


        countryReports(n,LIMIT_FOR_N);
        cityReports(n,LIMIT_FOR_N);
        capitalReports(n,LIMIT_FOR_N);
    }

    public  void capitalReports(int n, String limitForN) throws IOException {
        String continentPath = CONTINENT_FOLDER + "Top_" + n + "_Capital_Cities/";
        String regionPath = REGION_FOLDER + "Top_" + n + "_Capital_Cities/";
        Files.createDirectories(Paths.get(continentPath));
        Files.createDirectories(Paths.get(regionPath));
        topNCapitalCities(Constants.TOPN + "Top_" + n + "_Capital_Cities", Query.ALL_CAPITAL_CITIES.label +
                limitForN);
        reportsForAMapCapitalCity(continentPath,Query.capitalsByContinent(), limitForN);
        reportsForAMapCapitalCity(regionPath,Query.capitalsByRegion(),limitForN);
    }

    public  void countryReports(int n, String limitForN) throws IOException {
        final String continentPath = CONTINENT_FOLDER + "Top_" + n + "_Countries/";
        final String regionPath = REGION_FOLDER + "Top_" + n + "_Countries/";
        Files.createDirectories(Paths.get(continentPath));
        Files.createDirectories(Paths.get(regionPath));
        topNCountries(Constants.TOPN +
                "Top_" + n + "_Countries.txt", Query.ALL_COUNTRIES.label + limitForN);
       reportsForAMapCountry(continentPath,Query.countryByContinent(), limitForN);
       reportsForAMapCountry(regionPath, Query.countryByRegion(), limitForN);
    }

    public  void cityReports(int n, String limitForN) throws IOException {
        final String continentPath = CONTINENT_FOLDER + "Top_" + n + "_Cities/";
        final String regionPath = REGION_FOLDER + "Top_" + n + "_Cities/";
        final  String countryPath = Constants.TOPN + "Country/";
        final String districtPath = Constants.TOPN + "District/";
        Files.createDirectories(Paths.get(continentPath));
        Files.createDirectories(Paths.get(regionPath));
        Files.createDirectories(Paths.get(countryPath));
        Files.createDirectories(Paths.get(districtPath));
        topNCities(Constants.TOPN + "Top_" + n + "_Cities.txt", Query.ALL_CITIES.label + limitForN);
        reportsForAMapCity(continentPath,Query.cityByContinent(),limitForN);
        reportsForAMapCity(regionPath, Query.cityByRegion(),limitForN);
        reportsForAMapCity(countryPath, Query.cityByCountry(),limitForN);
        reportsForAMapCity(districtPath, Query.cityByDistrict(), limitForN);
    }

    /*method to shorten the generating of reports, you don't have to use that if it feels unclear */
    public  void reportsForAMapCountry(String constantFileName,
                                              HashMap<String, String> data, String limitForN) {

        for (Map.Entry<String, String> query : data.entrySet()) {
            String databit = query.getKey().replace("/", "_");
            topNCountries(constantFileName + databit + ".txt", query.getValue() + limitForN);
        }
    }

    public  void reportsForAMapCity(String constantFileName,
                                       HashMap<String, String> data, String limitForN) {

        for (Map.Entry<String, String> query : data.entrySet()) {
            String databit = query.getKey().replace("/", "_");
            topNCities(constantFileName + databit + ".txt", query.getValue() + limitForN);
        }
    }

    public void reportsForAMapCapitalCity(String constantFileName,
                                           HashMap<String, String> data, String limitForN) {

        for (Map.Entry<String, String> query : data.entrySet()) {
            String databit = query.getKey().replace("/", "_");
            topNCapitalCities(constantFileName + databit + ".txt", query.getValue() + limitForN);
        }
    }

    public  void topNCountries(String fileName, String query) {
        FileManager.createFile(fileName);
        FileManager.writeToFile(fileName, response.countriesByPopDesc(query));
    }

    public  void topNCities(String fileName, String query) {
        FileManager.createFile(fileName);
        FileManager.writeToFile(fileName, response.citiesByPopDesc(query));
    }

    public void topNCapitalCities(String fileName, String query) {
        FileManager.createFile(fileName);
        FileManager.writeToFile(fileName, response.capitalCitiesByPopDesc(query));
    }
}
