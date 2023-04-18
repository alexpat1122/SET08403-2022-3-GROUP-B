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

    private static final String COL = "SUM(population)";
    private static final String directoryPath = Constants.OTHER_REPORTS_DIRECTORY + "Populations_For/";

    public static  void generateReport(java.sql.Connection con) throws IOException {
        Files.createDirectories(Paths.get(directoryPath));
        FileManager.writeToFile(directoryPath + "All.txt", "World: " + ResponseFromDB.pop(con,COL,
                Query.ALL_POP.label), true);
        popForContinent(con);
        popForRegion(con);
        popForCountry(con);
        popForDistrict(con);
        popForCity(con);
    }

    private static void popForContinent(java.sql.Connection con)  {
        ArrayList<String> list = getArrayList(con, Query.totalPopulationByContinent());
        FileManager.writeToFile(directoryPath + "Population_For_Continent.txt",list);
    }

    private static void popForRegion(java.sql.Connection con) {
        ArrayList<String> list = getArrayList(con, Query.totalPopulationByRegion());
        FileManager.writeToFile(directoryPath + "Population_For_Region.txt",list);
    }

    private static void popForCountry(java.sql.Connection con) {
        ArrayList<String> list = getArrayList(con, Query.totalPopulationByCountry());
        FileManager.writeToFile(directoryPath + "Population_For_Country.txt",list);
    }

    private static void popForDistrict(java.sql.Connection con) throws IOException {
        ArrayList<String> list = getArrayList(con, Query.totalPopulationByDistrict());
        FileManager.writeToFile(directoryPath + "Population_For_District.txt",list);
    }

    private static void popForCity(java.sql.Connection con) {
        String query = Query.ALL_POP_BY_CITY.label;
        ArrayList<String> list = ResponseFromDB.popByCity(con,query);
        FileManager.writeToFile(directoryPath + "Population_For_City.txt",list);
    }


    private static ArrayList<String> getArrayList(java.sql.Connection con, Map<String, String> queryPassed) {
        ArrayList<String> list = new ArrayList<>();
        for (Map.Entry<String, String> query: queryPassed.entrySet()) {
            String result = query.getKey() + ": ";
            long pop = ResponseFromDB.pop(con,COL,query.getValue());
            result += pop;
            list.add(result);
        }
        return list;
    }

}
