package com.napier.sem.QueriesToFile;

import com.napier.sem.FileManager;
import com.napier.sem.constant.Constants;
import com.napier.sem.database.Query;
import com.napier.sem.database.StringTuple;
import com.napier.sem.structs.Population;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AllPopulations {

    /** Responsible for generating all population reports (Not just population count but the full info **/

    public static void allPop() throws IOException {
        Files.createDirectories(Paths.get(Constants.OTHER_REPORTS_DIRECTORY ));
        allPopulationQuery(Constants.OTHER_REPORTS_DIRECTORY + "Population_All_World.txt");
        allPopulationInContinents();
        allPopulationInRegions();
        allPopulationInCountries();
    }

    public static void allPopulationQuery(String fileName) {
        FileManager.createFile(fileName);
        long allPop = ResponseFromDB.pop("SUM(population)",Query.ALL_POP.label);
        long cityPop = ResponseFromDB.pop("SUM(population)",Query.ALL_POP_IN_CITIES.label);
        long countryPop = allPop - cityPop;
        double percentageCity = ((double) cityPop / allPop) * 100;;
       double percentageCountry = 100 - percentageCity;
        Population world = new Population("world",allPop,cityPop,countryPop, percentageCity,
                percentageCountry);
        ArrayList<String> result = new ArrayList<>();
        result.add(world.toString());
        FileManager.writeToFile(fileName,result);
    }

    private static void allPopulationInContinents() {
        String fileName = Constants.OTHER_REPORTS_DIRECTORY + "Population_By_Continent.txt";
        FileManager.createFile(fileName);
        HashMap<String, StringTuple> continents = Query.populationByContinent();
        FileManager.writeToFile(fileName, allPopulationIN(continents));
    }

    private static void allPopulationInRegions() {
        String fileName = Constants.OTHER_REPORTS_DIRECTORY + "Population_By_Region.txt";
        FileManager.createFile(fileName);
        HashMap<String, StringTuple> regions = Query.populationByRegion();
        FileManager.writeToFile(fileName, allPopulationIN(regions));
    }

    private static void allPopulationInCountries() {
        String fileName = Constants.OTHER_REPORTS_DIRECTORY + "Population_By_Country.txt";
        FileManager.createFile(fileName);
        HashMap<String, StringTuple> countries = Query.populationByCountry();
        FileManager.writeToFile(fileName,allPopulationIN(countries));
    }

    public static ArrayList<String> allPopulationIN(HashMap<String, StringTuple> query) {
        DecimalFormat df = new DecimalFormat("0.00");
        ArrayList<String> popIn = new ArrayList<>();
        for (Map.Entry<String, StringTuple> querySet : query.entrySet()) {
            long allPop = ResponseFromDB.pop("SUM(population)",querySet.getValue().getAllPop());
            long cityPop = ResponseFromDB.pop("SUM(city.population)", querySet.getValue().getCityPop());
            long countryPop = allPop - cityPop;
            double percentageCity;
            double percentageCountry;
            if(allPop != 0) {
               percentageCity = ((double) cityPop / allPop) * 100;
               percentageCountry = 100 - percentageCity;
            }
            else {
                percentageCity =0;
                percentageCountry = 0;
            }
            Population pop = new Population(querySet.getKey(),allPop,cityPop,countryPop, percentageCity,
                    percentageCountry);
            popIn.add(pop.toString());
        }
       return popIn;
    }

}
