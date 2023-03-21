import com.napier.sem.App;
import com.napier.sem.FileManager;
import com.napier.sem.QueriesToFile.AllCapitalCities;
import com.napier.sem.QueriesToFile.AllCities;
import com.napier.sem.QueriesToFile.AllCountries;
import com.napier.sem.constant.Constants;
import com.napier.sem.database.Connection;
import com.napier.sem.database.Query;
import com.napier.sem.database.SetupQueries;
import com.napier.sem.structs.*;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class MyTest
{

    /* RESET Thread.sleep(3000) at line 27 in Connection class for these to run lightning fast */

    java.sql.Connection con = Connection.connect();

    @Test
    void appConnected() {
        assertTrue(App.connected(con));
    }

    @Test
    void appNotConnected() {
        assertFalse(App.connected(null));
    }


    @Test
    void graciousDisconnect() {
        Connection.disconnect(con);
    }
   @Test
    void failDisconnect()  {
        RuntimeException exc = assertThrows(RuntimeException.class, () -> Connection.disconnect(null));
       assertEquals(exc.getClass(), RuntimeException.class);
    }

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
        IllegalArgumentException exc = assertThrows(IllegalArgumentException.class, () -> FileManager.writeToFile(   "../Reports/All_Countries.txt", null));
        assertEquals(IllegalArgumentException.class, exc.getClass());
    }

    @Test
    void readException() throws RuntimeException
    {
        RuntimeException exc = assertThrows(RuntimeException.class, () -> FileManager.readFile(   "./....."));
        assertEquals(exc.getClass(), RuntimeException.class);
    }

    @Test
    void allCountriesQueryFailIfSlash()
    {
        assertThrows(Exception.class, () ->  AllCountries.allCountriesQuery("Micronesia/Caribbean.txt","SELECT",con));
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
//
//
//
    @Test
    void queryByPopNullArrayPassed() {
        assertNull(Query.allInListByPop(null, null));
    }

    @Test
    void queryAllDataFromArrayAdded() {
        HashMap<String,String> map = Query.allInListByPop(null, new ArrayList<>(List.of("Zero")));
        assertTrue(map.containsKey("Zero"));
        assertEquals(1, map.size());
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
    void getcountryCity()
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
    void isChinese()
    {
        assertEquals("Chinese", Language.getLanguage(Language.CHINESE));
    }

    @Test
    void isHindi()
    {
        assertEquals("Hindi", Language.getLanguage(Language.HINDI));
    }

    @Test
    void isArabic()
    {
        assertEquals("Arabic", Language.getLanguage(Language.ARABIC));
    }

    @Test
    void isEnglish()
    {
        assertEquals("English", Language.getLanguage(Language.ENGLISH));
    }

    @Test
    void isSpanish()
    {
        assertEquals("Spanish", Language.getLanguage(Language.SPANISH));
    }

    @Test
    void countriesByPopNoCon()
    {
       assertEquals( new ArrayList<>(),AllCountries.countriesByPopDesc(null,null));
    }

    @Test
    void countriesByPopThrowsException() throws IOException {
        AllCountries.countryReports(null);
    }

    @Test
    void countriesByPopGood() {

        assertEquals( 232,AllCountries.countriesByPopDesc(con,Query.ALL_COUNTRIES.label).size());
    }

    @Test
    void citiesByPopNoCon()
    {
        assertEquals( new ArrayList<>(),AllCities.citiesByPopDesc(null,null));
    }


    @Test
    void citiesByPopThrowsException() throws IOException {
        AllCities.cityReports(null);
    }

    @Test
    void citiesByPopGood() {

        assertEquals( 4079,AllCities.citiesByPopDesc(con,Query.ALL_CITIES.label).size());
    }

    @Test
    void capitalCitiesByPopNoCon()
    {
        assertEquals( new ArrayList<>(), AllCapitalCities.citiesByPopDesc(null,null));
    }


    @Test
    void capitalCitiesByPopThrowsException() throws IOException {
        AllCapitalCities.cityReports(null);
    }

    @Test
    void capitalCitiesByPopGood() {

        assertEquals( 232, AllCapitalCities.citiesByPopDesc(con,Query.ALL_CAPITAL_CITIES.label).size());
    }

    @Test
    void setupRegion()
    {
        assertEquals( new ArrayList<>(), SetupQueries.regionList(null));
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
    void allCitiesByDistrictQuery() { assertEquals(4079,Query.cityByDistrict().size());}

    @Test
    void allCapitalsByRegionQuery() { assertEquals(232,Query.capitalsByRegion().size());}

    @Test
    void allCountriesByContinentQuery() { assertEquals(232,Query.countryByContinent().size());}

    @Test
    void allInListByPopQuery() { assertEquals(null,Query.allInListByPop(null, null));}
}
