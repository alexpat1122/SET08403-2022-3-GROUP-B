package com.napier.sem.database;

public enum Query {
    ALL_COUNTRIES_BY_POP_DESC ("SELECT Code,country.name,Continent,Region, country.Population, city.name " +
            "FROM country JOIN city on city.id = Capital ORDER BY population DESC"),
    ALL_COUNTRIES_IN_ASIA_DESC("SELECT Code,country.name,Continent,Region, country.Population, city.name FROM country JOIN city on\n" +
            "    city.id = Capital WHERE continent = \"Asia\" ORDER BY population DESC"),
    ALL_COUNTRIES_IN_EUROPE_DESC("SELECT Code,country.name,Continent,Region, country.Population, city.name FROM country JOIN city on\n" +
            "    city.id = Capital WHERE continent = \"Europe\" ORDER BY population DESC"),
    ALL_COUNTRIES_IN_NA_DESC("SELECT Code,country.name,Continent,Region, country.Population, city.name FROM country JOIN city on\n" +
            "    city.id = Capital WHERE continent = \"North America\" ORDER BY population DESC"),
    ALL_COUNTRIES_IN_AFRICA_DESC("SELECT Code,country.name,Continent,Region, country.Population, city.name FROM country JOIN city on\n" +
            "    city.id = Capital WHERE continent = \"Africa\" ORDER BY population DESC"),
    ALL_COUNTRIES_IN_SA_DESC("SELECT Code,country.name,Continent,Region, country.Population, city.name FROM country JOIN city on\n" +
            "    city.id = Capital WHERE continent = \"South America\" ORDER BY population DESC"),
    ALL_COUNTRIES_IN_OCEANIA_DESC("SELECT Code,country.name,Continent,Region, country.Population, city.name FROM country JOIN city on\n" +
            "    city.id = Capital WHERE continent = \"Oceania\" ORDER BY population DESC"),
    ALL_COUNTRIES_IN_ANTARCTICA_DESC("SELECT Code,country.name,Continent,Region, country.Population, city.name FROM country JOIN city on\n" +
            "    city.id = Capital WHERE continent = \"Antarctica\" ORDER BY population DESC");

    public final String label;
    Query(String label) {
        this.label = label;
    }


    public static String continentQuery(int input) {
        String beg = "SELECT Code,country.name,Continent,Region, country.Population, city.name FROM country JOIN city on\n" +
                "    city.id = Capital WHERE continent =";
        String end = "ORDER BY population DESC";
        switch (input) {
            case 1: return  beg + "Asia" + end;
            case 2: return  beg + "Europe" + end;
            case 3: return  beg + "North America" + end;
            case 4: return  beg + "Africa" + end;
            case 5: return  beg + "South America" + end;
            case 6: return  beg + "Oceania" + end;
            case 7: return  beg + "Antarctica" + end;
            default: return null;
        }
    }
}
