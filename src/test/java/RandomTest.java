import com.napier.sem.App;
import com.napier.sem.FileManager;
import com.napier.sem.QueriesToFile.AllCountries;
import com.napier.sem.database.Connection;
import com.napier.sem.database.Query;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


class MyTest
{

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
    void isAsia() {
        assertEquals("SELECT Code,country.name,Continent,Region, country.Population, city.name FROM country JOIN city on\n" +
                "    city.id = Capital WHERE continent =" + "\"Asia\"" + "ORDER BY population DESC", Query.continentQuery(1));
    }


    @Test
    void isEurope()
    {
        assertEquals("SELECT Code,country.name,Continent,Region, country.Population, city.name FROM country JOIN city on\n" +
                "    city.id = Capital WHERE continent =" + "\"Europe\"" + "ORDER BY population DESC", Query.continentQuery(2));
    }

    @Test
    void isNorthAmerica()
    {
        assertEquals("SELECT Code,country.name,Continent,Region, country.Population, city.name FROM country JOIN city on\n" +
                "    city.id = Capital WHERE continent =" + "\"North America\"" + "ORDER BY population DESC", Query.continentQuery(3));
    }

    @Test
    void isAfrica()
    {
        assertEquals("SELECT Code,country.name,Continent,Region, country.Population, city.name FROM country JOIN city on\n" +
                "    city.id = Capital WHERE continent =" + "\"Africa\"" + "ORDER BY population DESC", Query.continentQuery(4));
    }

    @Test
    void isSouthAmerica()
    {
        assertEquals("SELECT Code,country.name,Continent,Region, country.Population, city.name FROM country JOIN city on\n" +
                "    city.id = Capital WHERE continent =" + "\"South America\"" + "ORDER BY population DESC", Query.continentQuery(5));
    }

    @Test
    void isOceania()
    {
        assertEquals("SELECT Code,country.name,Continent,Region, country.Population, city.name FROM country JOIN city on\n" +
                "    city.id = Capital WHERE continent =" + "\"Oceania\"" + "ORDER BY population DESC", Query.continentQuery(6));
    }

    @Test
    void isAntarctica()
    {
        assertEquals("SELECT Code,country.name,Continent,Region, country.Population, city.name FROM country JOIN city on\n" +
                "    city.id = Capital WHERE continent =" + "\"Antarctica\"" + "ORDER BY population DESC", Query.continentQuery(7));
    }

    @Test
    void noContinent()
    {
        assertEquals(null, Query.continentQuery(8));
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
        RuntimeException exc = assertThrows(RuntimeException.class, () -> {
            FileManager.createFile(   "../Reports/All_Countries.txt");
        });
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
        RuntimeException exc = assertThrows(RuntimeException.class, () -> {
            FileManager.writeCountriesToFile(   "../Reports/All_Countries.txt", null);
        });
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
        assertEquals(25, Query.regionQueries(con).size());
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
}
