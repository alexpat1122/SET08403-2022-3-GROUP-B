package com.napier.sem.QueriesToFile;

import com.napier.sem.FileManager;
import com.napier.sem.constant.Constants;
import com.napier.sem.database.Query;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

public class PopulationFor {

    /** A different population report generator where it will generate all reports for a specific type, was replaced with
     * single report, since that fits the brief a bit better **/


    private static final String COL = "SUM(population)";
    private static final String directoryPath = Constants.OTHER_REPORTS_DIRECTORY + "Populations_For/";

    private Response response;

    public  void generateReport(Response response) throws IOException {
        this.response = response;
        Files.createDirectories(Paths.get(directoryPath));
        FileManager.writeToFile(directoryPath + "All.txt", "World: " + response.pop(COL,
                Query.ALL_POP.label), true);
        popForContinent();
        popForRegion();
        popForCountry();
        popForDistrict();
        popForCity();
    }

    private  void popForContinent()  {
        ArrayList<String> list = getArrayList(Query.totalPopulationByContinent());
        FileManager.writeToFile(directoryPath + "Population_For_Continent.txt",list);
    }

    private void popForRegion() {
        ArrayList<String> list = getArrayList(Query.totalPopulationByRegion());
        FileManager.writeToFile(directoryPath + "Population_For_Region.txt",list);
    }

    private  void popForCountry() {
        ArrayList<String> list = getArrayList(Query.totalPopulationByCountry());
        FileManager.writeToFile(directoryPath + "Population_For_Country.txt",list);
    }

    private void popForDistrict() throws IOException {
        ArrayList<String> list = getArrayList(Query.totalPopulationByDistrict());
        FileManager.writeToFile(directoryPath + "Population_For_District.txt",list);
    }

    private  void popForCity() {
        String query = Query.ALL_POP_BY_CITY.label;
        ArrayList<String> list = response.popByCity(query);
        FileManager.writeToFile(directoryPath + "Population_For_City.txt",list);
    }


    private  ArrayList<String> getArrayList(Map<String, String> queryPassed) {
        ArrayList<String> list = new ArrayList<>();
        for (Map.Entry<String, String> query: queryPassed.entrySet()) {
            String result = query.getKey() + ": ";
            long pop = response.pop(COL,query.getValue());
            result += pop;
            list.add(result);
        }
        return list;
    }

}
