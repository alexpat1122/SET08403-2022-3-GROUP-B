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

    public static  void generateReport() throws IOException {
        Files.createDirectories(Paths.get(directoryPath));
        FileManager.writeToFile(directoryPath + "All.txt", "World: " + ResponseFromDB.pop(COL,
                Query.ALL_POP.label), true);
        popForContinent();
        popForRegion();
        popForCountry();
        popForDistrict();
        popForCity();
    }

    private static void popForContinent()  {
        ArrayList<String> list = getArrayList(Query.totalPopulationByContinent());
        FileManager.writeToFile(directoryPath + "Population_For_Continent.txt",list);
    }

    private static void popForRegion() {
        ArrayList<String> list = getArrayList(Query.totalPopulationByRegion());
        FileManager.writeToFile(directoryPath + "Population_For_Region.txt",list);
    }

    private static void popForCountry() {
        ArrayList<String> list = getArrayList(Query.totalPopulationByCountry());
        FileManager.writeToFile(directoryPath + "Population_For_Country.txt",list);
    }

    private static void popForDistrict() throws IOException {
        ArrayList<String> list = getArrayList(Query.totalPopulationByDistrict());
        FileManager.writeToFile(directoryPath + "Population_For_District.txt",list);
    }

    private static void popForCity() {
        String query = Query.ALL_POP_BY_CITY.label;
        ArrayList<String> list = ResponseFromDB.popByCity(query);
        FileManager.writeToFile(directoryPath + "Population_For_City.txt",list);
    }


    private static ArrayList<String> getArrayList(Map<String, String> queryPassed) {
        ArrayList<String> list = new ArrayList<>();
        for (Map.Entry<String, String> query: queryPassed.entrySet()) {
            String result = query.getKey() + ": ";
            long pop = ResponseFromDB.pop(COL,query.getValue());
            result += pop;
            list.add(result);
        }
        return list;
    }

}
