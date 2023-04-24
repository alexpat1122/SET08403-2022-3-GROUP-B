import com.napier.sem.App;
import com.napier.sem.FileManager;
import com.napier.sem.QueriesToFile.*;
import com.napier.sem.constant.Constants;
import com.napier.sem.constant.Languages;
import com.napier.sem.database.Query;
import com.napier.sem.database.SetupQueries;
import com.napier.sem.database.StringTuple;
import com.napier.sem.structs.*;
import com.sun.source.tree.AssertTree;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;


class MyTest
{

    /** RESET Thread.sleep(3000) at line 27 in Connection class for these to run lightning fast **/

    /** These are probably integration tests, I think, also the tests for ResponseFromDB also should be integration tests **/

    Response response = new TestResponse();

    @Test
    void fileExists()
    {
        FileManager.createFile("test.txt");
        assertFalse(FileManager.createFile("test.txt"));
        FileManager.deleteFile("test.txt");
    }

    @Test
    void fileException() throws RuntimeException
    {
        RuntimeException exc = assertThrows(RuntimeException.class, () ->
                FileManager.createFile(   "../Reports/All_Countries.txt"));
        assertEquals(exc.getClass(), RuntimeException.class);
    }

    @Test
    void fileNotExists()
    {
        assertTrue(FileManager.createFile("test.txt"));
        FileManager.deleteFile("test.txt");
    }
//
    @Test
    void deleteSuccess()
    {
        FileManager.createFile("test.txt");
        assertTrue(FileManager.deleteFile("test.txt"));
    }

    @Test
    void deleteFail()
    {
        assertFalse(FileManager.deleteFile("test.txt"));
    }

    @Test
    void writeException() throws RuntimeException
    {
        RuntimeException exc = assertThrows(RuntimeException.class, () -> FileManager.writeToFile(   "../Reports/All_Countries.txt", null));
        assertEquals(RuntimeException.class, exc.getClass());
    }
    @Test
    void writeOK()
    {
        FileManager.createFile("test");
       FileManager.writeToFile("test","test",true);
    }


    @Test
    void readException() throws RuntimeException
    {
        RuntimeException exc = assertThrows(RuntimeException.class, () -> FileManager.readFile(   "./....."));
        assertEquals(exc.getClass(), RuntimeException.class);
    }
    @Test
    void allRegionsAreIn()
    {
        assertEquals(25, FileManager.readFile(Constants.REGION_DATA).size());
    }

    @Test
    void allContinentsAreIn()
    {
        assertEquals(7, FileManager.readFile(Constants.CONTINENT_DATA).size());
    }

    @Test
    void allCountriesAreIn()
    {
        assertEquals(239, FileManager.readFile(Constants.COUNTRY_DATA).size());
    }
    @Test
    void capitalCityYes()
    {
        assertEquals("Vilnius", new CapitalCity("Vilnius", "Lithuania", 500000).getName());
    }
    @Test
    void getSizeCapital()
    {
        CapitalCity test = new CapitalCity("Vilnius", "Lithuania", 500000);
        assertEquals(500000, test.getPopulation());
    }

    @Test
    void getCountryCapital()
    {
        assertEquals("Lithuania", new CapitalCity("Vilnius", "Lithuania", 500000).getCountry());
    }

    @Test
    void capitalCityToString()
    {
        assertEquals("CapitalCity{" +
                "name=" + "Vilnius" + ", country=" + "Lithuania" +
                ", population=" + 500000 +
                '}', new CapitalCity("Vilnius", "Lithuania", 500000).toString());
    }

    @Test
    void cityYes()
    {
        assertEquals("Edinburgh", new City("Edinburgh", "UK", 500000, "sth").getName());
    }

    @Test
    void getCountryCity()
    {
        assertEquals("UK", new City("Edinburgh", "UK", 500000, "sth").getCountry());
    }

    @Test
    void getPopCity()
    {
        assertEquals(500000, new City("Edinburgh", "UK", 500000, "sth").getPopulation());
    }

    @Test
    void getCityString()
    {
        assertEquals("City{name=" + "Edinburgh" + ", country=" + "UK" + ", district=" + "sth" +
                ", population=" + 500000 + '}', new City("Edinburgh", "UK", 500000, "sth").toString());
    }

    @Test
    void isAsia()
    {
        assertEquals(Continent.ASIA, Continent.getContinent("Asia"));
    }

    @Test
    void isEurope()
    {
        assertEquals(Continent.EUROPE, Continent.getContinent("Europe"));
    }

    @Test
    void isNA()
    {
        assertEquals(Continent.NORTH_AMERICA, Continent.getContinent("North America"));
    }

    @Test
    void isAfrica()
    {
        assertEquals(Continent.AFRICA, Continent.getContinent("Africa"));
    }

    @Test
    void isOceania()
    {
        assertEquals(Continent.OCEANIA, Continent.getContinent("Oceania"));
    }

    @Test
    void isAntarctica()
    {
        assertEquals(Continent.ANTARCTICA, Continent.getContinent("Antarctica"));
    }

    @Test
    void isSA()
    {
        assertEquals(Continent.SOUTH_AMERICA, Continent.getContinent("South America"));
    }
    @Test
    void isIncorrectContinent()
    {
        assertEquals(Continent.INCORRECT_DATA_FORMAT_PROVIDED, Continent.getContinent("not Continent"));
    }

    @Test
    void countryCreated()
    {
        assertEquals("Country{" +
                "code=" + "AXA"  + ", name=" + "HOHO" +
                ", continent=" + Continent.ASIA + ", region=" + "KK" +
                ", population=" + 200 + ", capital=" + "NOM" + '}',
                new Country("AXA", "HOHO",Continent.ASIA, "KK", 200, "NOM").toString());
    }


    @Test
    void setupRegion()
    {
        assertEquals( new ArrayList<>(), SetupQueries.regionList(null));
    }

    @Test
    void setupDistrict()
    {
        assertEquals( new ArrayList<>(), SetupQueries.districtList(null));
    }

    @Test
    void setupCountry()
    {
        assertEquals( new ArrayList<>(), SetupQueries.countryList(null));
    }

    @Test
    void setupContinent()
    {
        assertEquals( new ArrayList<>(), SetupQueries.continentList(null));
    }


    @Test
    void unitTest()
    {
        assertEquals(5, 5);
    }

    @Test
    void unitTest3()
    {
        assertEquals(5, 5, "Messages are equal");
    }

    @Test
    void unitTest4()
    {
        assertEquals(5.0, 5.01, 0.02);
    }

    @Test
    void unitTest5()
    {
        int[] a = {1, 2, 3};
        int[] b = {1, 2, 3};
        assertArrayEquals(a, b);
    }

    @Test
    void unitTest6()
    {
        assertTrue(5 == 5);
    }

    @Test
    void unitTest7()
    {
        assertFalse(5 == 4);
    }

    @Test
    void unitTest8()
    {
        assertNull(null);
    }

    @Test
    void unitTest9()
    {
        assertNotNull("Hello");
    }

    @Test
    void unitTest10()
    {
        assertThrows(NullPointerException.class, this::throwsException);
    }

    void throwsException() throws NullPointerException
    {
        throw new NullPointerException();
    }
    @Test
    void allDistrictsAreIn()
    {
        assertEquals(4079, FileManager.readFile(Constants.DISTRICT_DATA).size());
    }
    @Test
    void readCorrect() throws RuntimeException
    {
        Object[] continent = {"North America", "Asia", "Africa", "Europe", "South America", "Oceania", "Antarctica"};
        Object [] readContinents = FileManager.readFile(Constants.CONTINENT_DATA).stream().toArray();
        assertArrayEquals(continent,readContinents);
    }

    @Test
    void ALL_COUNTRIES() {
        assertEquals(Query.ALL_COUNTRIES.label,"SELECT Code,country.name,Continent,Region, country.Population, city.name " +
                "FROM country JOIN city on city.id = Capital ORDER BY population DESC");
    }
    @Test
    void ALL_CITIES() {
        assertEquals(Query.ALL_COUNTRIES.label,"SELECT Code,country.name,Continent,Region," +
                " country.Population, city.name FROM country JOIN city on city.id = Capital ORDER BY population DESC");
    }

    @Test
    void ALL_CAPITALS() {
        assertEquals(Query.ALL_COUNTRIES.label,"SELECT Code,country.name,Continent,Region, country.Population, city.name " +
                "FROM country JOIN city on city.id = Capital ORDER BY population DESC");
    }

    @Test
    void ALL_POP() {
        assertEquals(Query.ALL_COUNTRIES.label,"SELECT Code,country.name,Continent,Region, country.Population, city.name FROM country JOIN city on city.id = Capital ORDER BY population DESC");
    }

    @Test
    void ALL_POP_IN_CITIES() {
        assertEquals(Query.ALL_COUNTRIES.label,"SELECT Code,country.name,Continent,Region, country.Population, city.name FROM country JOIN city on city.id = Capital ORDER BY population DESC");
    }

    @Test
    void ALL_POP_BY_CITIES() {
        assertEquals(Query.ALL_COUNTRIES.label,"SELECT Code,country.name,Continent,Region, country.Population, city.name FROM country JOIN city on city.id = Capital ORDER BY population DESC");
    }
    @Test
    void allSingleQueries() {
        assertEquals(Query.allSingleQueries(null, null, null, null,null).size(),1);
    }

    @Test
    void allSingleQueries2() {
        assertEquals(Query.allSingleQueries("test", null, null, null,null).get("test")
                ,"SELECT SUM(population) AS population FROM country WHERE continent = " + String.format("\"%s\" ", "test"));
    }

    @Test
    void languageQuery() {
        assertEquals(5, Query.languageQueries().size());
    }

    @Test
    void popByContinentQuery() {
        assertEquals(7, Query.populationByContinent().size());
    }

    @Test
    void totalPopByContinentQuery() {
        assertEquals(7, Query.totalPopulationByContinent().size());
    }

    @Test
    void totalPopByRegionQuery() {
        assertEquals(25, Query.totalPopulationByRegion().size());
    }

    @Test
    void buildLanguageQuery() {
        assertEquals(239, Query.totalPopulationByCountry().size());
    }

    @Test
    void totalPopByDistrictQuery() {
        assertEquals(1367, Query.totalPopulationByDistrict().size());
    }

    @Test
    void totalPopByDistrictQuery2() {
        assertEquals("SELECT SUM(population) from city where district = " +
                String.format("\"%s\" ", "test"), Query.totalPopulationByDistrict("test"));
    }

    @Test
    void popByRegionQuery() {
        assertEquals(25, Query.populationByRegion().size());
    }

    @Test
    void popByCountryQuery() {
        assertEquals(239, Query.populationByCountry().size());
    }

    @Test
    void countryByRegionQuery() {
        assertEquals(25, Query.countryByRegion().size());
    }

    @Test
    void countryByContinentQuery() {
        assertEquals(7, Query.countryByContinent().size());
    }

    @Test
    void cityByContinentQuery() {
        assertEquals(7, Query.cityByContinent().size());
    }

    @Test
    void cityByCountryQuery() {
        assertEquals(239, Query.cityByCountry().size());
    }

    @Test
    void cityByRegionQuery() {
        assertEquals(25, Query.cityByRegion().size());
    }

    @Test
    void cityByDistrictQuery() {
        assertEquals(1367, Query.cityByDistrict().size());
    }

    @Test
    void capitalByContinentQuery() {
        assertEquals(7, Query.capitalsByContinent().size());
    }

    @Test
    void capitalByRegionQuery() {
        assertEquals(25, Query.capitalsByRegion().size());
    }

    @Test
    void allInListByPopQuery2() {
        assertEquals(0, Query.allInListByPop("",new ArrayList<>()).size());
    }

    @Test
    void allInListByPopQuery3() {
        ArrayList<String> test = new ArrayList();
        test.add("test");
        assertEquals(String.format("\"%s\" ", "test") + Constants.POP_DESC, Query.allInListByPop("",test).get("test"));
    }

    @Test
    void getAllPops() {
        StringTuple test = new StringTuple("100","1");
        assertEquals("100", test.getAllPop());
    }

    @Test
    void getCityPops() {
        StringTuple test = new StringTuple("100","1");
        assertEquals("1", test.getCityPop());
    }

    @Test
    void languageGood() {
        new Language("test",0,11);
    }

    @Test
    void languageToString() {
        assertEquals("Language{" +
                "name='" + "test"  +
                ", population=" + 0 +
                ", world percentage=" +0.0 +
                '}',new Language("test",0,0.0).toString());
    }

    @Test
    void compareLanguage() {
        assertEquals(-1, new Language("test",0,0).compareTo(1));
    }

    @Test
    void compareLanguage1() {
        assertEquals(0, new Language("test",0,0).compareTo(new Language("test1",1,0)));
    }

    @Test
    void compareLanguage2() {
        assertEquals(1, new Language("test",0,1).compareTo(new Language("test1",1,0)));
    }

    @Test
    void popOk() {
        new Population("test",0,0,0,0,0);
    }

    @Test
    void popToString() {
        assertEquals("Population{name=test, total=100, inCity=1, inCountry=1, percentageInCity=0.0, percentageInCountry=0.0}", new Population("test",100,1,1,0.0,0.0).toString());
    }
        @Test
    void runsMain() throws IOException
    {
        App.main(null);
   }

   @Test
    void AllCapitals() {
       NullPointerException exc = assertThrows(NullPointerException.class, () -> new AllCapitalCities().cityReports(response));
       assertEquals(exc.getClass(), NullPointerException.class);
   }

    @Test
    void AllCities() {
        NullPointerException exc = assertThrows(NullPointerException.class, () -> new AllCities().cityReports(response));
        assertEquals(exc.getClass(), NullPointerException.class);
    }

    @Test
    void AllCountries() {
        NullPointerException exc = assertThrows(NullPointerException.class, () -> new AllCountries().countryReports(response));
        assertEquals(exc.getClass(), NullPointerException.class);
    }

    @Test
    void AllLanguages() {
         new AllLanguages().allLanguages(response);
    }

    @Test
    void AllPopulations() throws IOException {
        new AllPopulations().allPop(response);
    }

    @Test
    void PopFor() {
        NullPointerException exc = assertThrows(NullPointerException.class, () -> new PopulationFor().generateReport(response));
        assertEquals(exc.getClass(), NullPointerException.class);
    }

    @Test
    void SinglePop() throws IOException {
        new SinglePopulationFor().singlePopulationsFor("","","","","",response);
    }

    @Test
    void TopN() {
        NullPointerException exc = assertThrows(NullPointerException.class, () -> new TopN().allReports(2,response));
        assertEquals(exc.getClass(), NullPointerException.class);
    }

    @Test
    void TopNCapital() {
        NullPointerException exc = assertThrows(NullPointerException.class, () -> new TopN().capitalReports(2,""));
        assertEquals(exc.getClass(), NullPointerException.class);
    }

    @Test
    void TopNCity() {
        NullPointerException exc = assertThrows(NullPointerException.class, () -> new TopN().cityReports(2,""));
        assertEquals(exc.getClass(), NullPointerException.class);
    }

    @Test
    void TopNCountry() {
        NullPointerException exc = assertThrows(NullPointerException.class, () -> new TopN().countryReports(2,""));
        assertEquals(exc.getClass(), NullPointerException.class);
    }

    @Test
    void TopNCapitals() {
        RuntimeException exc = assertThrows(RuntimeException.class, () -> new TopN().topNCapitalCities("",""));
        assertEquals(exc.getClass(), RuntimeException.class);
    }

    @Test
    void TopNCities() {
        RuntimeException exc = assertThrows(RuntimeException.class, () -> new TopN().topNCities("",""));
        assertEquals(exc.getClass(), RuntimeException.class);
    }

    @Test
    void TopNCountries() {
        RuntimeException exc = assertThrows(RuntimeException.class, () -> new TopN().topNCountries("",""));
        assertEquals(exc.getClass(), RuntimeException.class);
    }

    @Test
    void reportForAMapCity() {
        NullPointerException exc = assertThrows(NullPointerException.class, () -> new TopN().reportsForAMapCity("",null,null));
        assertEquals(exc.getClass(), NullPointerException.class);
    }

    @Test
    void reportForAMapCountry() {
        NullPointerException exc = assertThrows(NullPointerException.class, () -> new TopN().reportsForAMapCountry("",null,null));
        assertEquals(exc.getClass(), NullPointerException.class);
    }

    @Test
    void reportForAMapCapital() {
        NullPointerException exc = assertThrows(NullPointerException.class, () -> new TopN().reportsForAMapCapitalCity("",null,null));
        assertEquals(exc.getClass(), NullPointerException.class);
    }

    @Test
    void AllCapitalsMap() {
       new AllCapitalCities().reportsForAMap("",new HashMap<>());
    }

    @Test
    void AllCapitalsMapQuery() {
       RuntimeException exc = assertThrows(RuntimeException.class, ()-> new AllCapitalCities().allCitiesQuery("",""));
       assertEquals(RuntimeException.class, exc.getClass());
    }

    @Test
    void AllCitiesMapQuery() {
        RuntimeException exc = assertThrows(RuntimeException.class, ()-> new AllCities().allCitiesQuery("",""));
        assertEquals(RuntimeException.class, exc.getClass());
    }

    @Test
    void AllCitiesMapReportsQuery() {
        new AllCities().reportsForAMap("",new HashMap<>());
    }

    @Test
    void appConnected() {
        assertTrue(App.connected(true));
    }

    @Test
    void appConnected2() throws IOException {
       App.createConstantFiles(false);
    }

    @Test
    void isChinese() {
        assertEquals("Chinese", Languages.CHINESE);
    }

    @Test
    void isEnglish() {
        assertEquals("English", Languages.ENGLISH);
    }

    @Test
    void isHindi() {
        assertEquals("Hindi", Languages.HINDI);
    }

    @Test
    void isSpanish() {
        assertEquals("Spanish", Languages.SPANISH);
    }

    @Test
    void isArabic() {
        assertEquals("Arabic", Languages.ARABIC);
    }

    @Test
    void newLanguages() {
        new Languages();
    }

    @Test
    void newConstants() {
        new Constants();
    }
}
