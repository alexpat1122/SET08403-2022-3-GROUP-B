package com.napier.sem.database;



import com.napier.sem.FileManager;
import com.napier.sem.constant.Constants;
import com.napier.sem.constant.Languages;


import java.util.ArrayList;
import java.util.HashMap;
/** query class, consisting of enums for static queries and methods to build queries on the go **/
public enum Query {

    ALL_COUNTRIES("SELECT Code,country.name,Continent,Region, country.Population, city.name " +
            "FROM country JOIN city on city.id = Capital ORDER BY population DESC"),
    ALL_CITIES("SELECT city.name, country.name, district, city.population" +
            " FROM city JOIN country on CountryCode = code ORDER BY city.population DESC"),
    ALL_CAPITAL_CITIES("SELECT city.name, country.name, city.Population " +
            "FROM country JOIN city on city.id = Capital ORDER BY population DESC"),

    ALL_POP("SELECT SUM(population) FROM country"),
    ALL_POP_IN_CITIES("SELECT SUM(population) FROM city"),

    ALL_POP_BY_CITY("SELECT population, name FROM city");

    public final String label;
    private final static ArrayList<String> continents = FileManager.readFile(Constants.CONTINENT_DATA);
    private final static ArrayList<String> regions = FileManager.readFile(Constants.REGION_DATA);
    private final static ArrayList<String> countries = FileManager.readFile(Constants.COUNTRY_DATA);
    private final static ArrayList<String> districts = FileManager.readFile(Constants.DISTRICT_DATA);

    Query(String label) {
        this.label = label;
    }

    /* if desired all the methods can be refactored to use allInListByPop, result doesn't change but code would be a bit cleaner*/

    public static HashMap<String, String> allSingleQueries( String continent, String region, String country,
                                                      String district, String city) {
        HashMap<String, String> queries = new HashMap<>();
        String beg = "SELECT SUM(population) AS population FROM country WHERE ";
        queries.put(continent,beg + "continent = " + String.format("\"%s\" ", continent));
        queries.put(region,beg + "region = " + String.format("\"%s\" ", region));
        beg = "SELECT SUM(population) AS population FROM city WHERE ";
        queries.put(district,beg + "district = "  + String.format("\"%s\" ", district));
        queries.put(city,"SELECT population FROM city WHERE name = " +   String.format("\"%s\" ", city));
        queries.put(country,"SELECT population FROM country WHERE name = " +   String.format("\"%s\" ", country));
        return queries;
    }


    public static HashMap<String, String> languageQueries() {
        HashMap<String, String> queries = new HashMap<>();
//        String beg = "SELECT percentage, population FROM country JOIN countrylanguage  WHERE language =";
//        String end = " ON country.code = countryCode";
        String beg = "select percentage, population FROM country JOIN countrylanguage ON country.code = countryCode WHERE language = ";
        queries.put(Languages.CHINESE, buildLanguageQuery(Languages.CHINESE,beg));
        queries.put(Languages.ENGLISH, buildLanguageQuery(Languages.ENGLISH,beg));
        queries.put(Languages.HINDI, buildLanguageQuery(Languages.HINDI,beg));
        queries.put(Languages.SPANISH,buildLanguageQuery(Languages.SPANISH,beg));
        queries.put(Languages.ARABIC, buildLanguageQuery(Languages.ARABIC,beg));
        return queries;
    }

    public static HashMap<String, StringTuple> populationByContinent() {
        HashMap<String, StringTuple> continentQuery = new HashMap<>();
        for(String continent: continents) {
            continentQuery.put(continent, new StringTuple(totalPopulationByContinent(continent),
                    cityPopulationByContinent(continent)));
        }
        return continentQuery;
    }

    public static HashMap<String, String> totalPopulationByContinent() {
        HashMap<String, String> continentQuery = new HashMap<>();
        for(String continent: continents) {
            continentQuery.put(continent, totalPopulationByContinent(continent));
        }
        return continentQuery;
    }

    public static HashMap<String, String> totalPopulationByRegion() {
        HashMap<String, String> regionQuery = new HashMap<>();
        for(String region: regions) {
            regionQuery.put(region, totalPopulationByRegion(region));
        }
        return regionQuery;
    }

    private static String buildLanguageQuery(String language, String beg) {
        return beg + String.format("\"%s\" ", language);
    }

    public static HashMap<String, String> totalPopulationByCountry() {
        HashMap<String, String> countryQuery = new HashMap<>();
        for(String country: countries) {
            countryQuery.put(country, totalPopulationByCountry(country));
        }
        return countryQuery;
    }

    public static HashMap<String, String> totalPopulationByDistrict() {
        HashMap<String, String> districtQuery = new HashMap<>();
        for(String district: districts) {
            districtQuery.put(district, totalPopulationByDistrict(district));
        }
        return districtQuery;
    }

    public static String totalPopulationByDistrict(String district) {
        String beg = "SELECT SUM(population) from city where district = ";
        return beg + String.format("\"%s\" ", district);
    }

    private static String cityPopulationByContinent(String continent) {
        String beg = "SELECT SUM(city.population) FROM city inner join " +
                "(SELECT Code FROM country WHERE Continent = ";
        String end = ") as countriesContinent on  city.countryCode = countriesContinent.Code";
        return beg + String.format("\"%s\" ", continent) + end;
    }

    private static String totalPopulationByContinent(String continent) {
        String beg = "SELECT SUM(population) FROM country WHERE Continent = ";
        return beg + String.format("\"%s\" ", continent);
    }

    public static HashMap<String, StringTuple> populationByRegion() {
        HashMap<String, StringTuple> regionQuery = new HashMap<>();
        for(String region: regions) {
            regionQuery.put(region, new StringTuple(totalPopulationByRegion(region),
                    cityPopulationByRegion(region)));
        }
        return regionQuery;
    }

    public static HashMap<String, StringTuple> populationByCountry() {
        HashMap<String, StringTuple> countryQuery = new HashMap<>();
        for(String country: countries) {
            countryQuery.put(country, new StringTuple(totalPopulationByCountry(country),
                    cityPopulationByCountry(country)));
        }
        return countryQuery;
    }
    private static String cityPopulationByCountry(String country) {

        String beg = " SELECT SUM(city.population) FROM city inner join  " +
                "(SELECT Code FROM country WHERE name = ";
        String end = ") as countriesContinent on  city.countryCode = countriesContinent.Code;";
        return beg + String.format("\"%s\" ", country) + end;
    }

    private static String totalPopulationByCountry(String country) {
        String beg = "SELECT SUM(population) FROM country WHERE name = ";
        return beg + String.format("\"%s\" ", country);
    }
    private static String cityPopulationByRegion(String region) {
        String beg = "  SELECT SUM(city.population) FROM city inner join " +
                "(SELECT Code FROM country WHERE region = ";
        String end = ")\n" +
                "        as countriesContinent on  city.countryCode = countriesContinent.Code";
        return beg + String.format("\"%s\" ", region) + end;
    }

    private static String totalPopulationByRegion(String region) {
        String beg = "SELECT SUM(population) FROM country WHERE region = ";
        return beg + String.format("\"%s\" ", region);
    }

    public static  HashMap<String, String> countryByContinent() {
        HashMap<String, String> continentQueries = new HashMap<>();

        String beg = "SELECT Code,country.name,Continent,Region, country.Population, city.name FROM country JOIN city on" +
                " city.id = Capital WHERE continent =";
        String end = Constants.POP_DESC;
        for (String continent : continents) {
            continentQueries.put(continent, beg + String.format("\"%s\" ", continent) + end);
        }
        return continentQueries;
    }

    public static  HashMap<String, String> countryByRegion() {
        HashMap<String, String> regionQueries = new HashMap<>();
        String beg = "SELECT Code,country.name,Continent,Region, country.Population, city.name FROM country JOIN city on\n" +
                "    city.id = Capital WHERE region =";
        String end = Constants.POP_DESC;
        for (String region : regions) {
            regionQueries.put(region, beg + String.format("\"%s\" ", region) + end);
        }
        return regionQueries;
    }

    public static  HashMap<String, String> cityByCountry() {
        HashMap<String, String> cityQueries = new HashMap<>();
        String beg = "SELECT city.name,country.name,district,city.population FROM city JOIN country on" +
                " countryCode = code WHERE country.name =";
        String end = Constants.POP_DESC;
        for (String country : countries) {
            cityQueries.put(country, beg + String.format("\"%s\" ", country) + end);
        }
        return cityQueries;
    }

    public static HashMap<String, String> cityByContinent() {
        return allInListByPop("SELECT city.name,country.name,district,city.population FROM city JOIN country on" +
                " countryCode = code WHERE continent =", continents);
    }

    public static HashMap<String, String> cityByRegion() {
        return allInListByPop("SELECT city.name,country.name,district,city.population \n" +
                "FROM city JOIN country on countryCode = code \n" +
                "WHERE Region =", regions);
    }

    public static HashMap<String, String> cityByDistrict() {
        return allInListByPop("SELECT city.name,country.name,district,city.population \n" +
                "FROM city JOIN country on countryCode = code \n" +
                "WHERE district =", districts);
    }

    public static HashMap<String, String> capitalsByContinent() {
        return allInListByPop("SELECT city.name, country.name, city.Population\n" +
                "FROM country JOIN city on city.id = Capital\n" +
                "WHERE continent =", continents);
    }

    public static HashMap<String, String> capitalsByRegion() {
        return allInListByPop("SELECT city.name, country.name, city.Population\n" +
                "FROM country JOIN city on city.id = Capital\n" +
                "WHERE region =", regions);
    }

    public static HashMap<String, String> allInListByPop(String beg, ArrayList<String> data) {
        if (data == null) {
            return null;
        }
        HashMap<String, String> returnable = new HashMap<>();
        String end = Constants.POP_DESC;
        for (String databit : data) {
            returnable.put(databit, beg + String.format("\"%s\" ", databit) + end);
        }
        return returnable;
    }
}
