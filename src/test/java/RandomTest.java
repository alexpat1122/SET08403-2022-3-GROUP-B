import com.napier.sem.App;
import com.napier.sem.FileManager;
import com.napier.sem.QueriesToFile.AllCountries;
import com.napier.sem.constant.Constants;
import com.napier.sem.database.Connection;
import com.napier.sem.database.Query;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class MyTest
{

    /* RESET Thread.sleep(3000) at line 27 in Connection class for these to run lightning fast */

    java.sql.Connection con = Connection.connect();

//    @Test
//    void appConnected() {
//        assertTrue(App.connected(con));
//    }
//
//    @Test
//    void appNotConnected() {
//        assertFalse(App.connected(null));
//    }
//
//
//    @Test
//    void graciousDisconnect() {
//        Connection.disconnect(con);
//    }
//   @Test
//    void failDisconnect()  {
//        RuntimeException exc = assertThrows(RuntimeException.class, () -> {
//           Connection.disconnect(null);
//       });
//       assertEquals(exc.getClass(), RuntimeException.class);
//    }
//
//    @Test
//    void fileExists()
//    {
//        FileManager.createFile("test.txt");
//        assertFalse(FileManager.createFile("test.txt"));
//        FileManager.deleteFile("test.txt");
//    }
//
//    @Test
//    void fileException() throws RuntimeException
//    {
//        RuntimeException exc = assertThrows(RuntimeException.class, () -> {
//            FileManager.createFile(   "../Reports/All_Countries.txt");
//        });
//        assertEquals(exc.getClass(), RuntimeException.class);
//    }
//
//    @Test
//    void fileNotExists()
//    {
//        assertTrue(FileManager.createFile("test.txt"));
//        FileManager.deleteFile("test.txt");
//    }
//
//    @Test
//    void deleteSuccess()
//    {
//        FileManager.createFile("test.txt");
//        assertTrue(FileManager.deleteFile("test.txt"));
//    }
//
//    @Test
//    void deleteFail()
//    {
//        assertFalse(FileManager.deleteFile("test.txt"));
//    }
//
//    @Test
//    void writeException() throws RuntimeException
//    {
//        IllegalArgumentException exc = assertThrows(IllegalArgumentException.class, () -> {
//            FileManager.writeToFile(   "../Reports/All_Countries.txt", null);
//        });
//        assertEquals(IllegalArgumentException.class, exc.getClass());
//    }
//
//    @Test
//    void readException() throws RuntimeException
//    {
//        RuntimeException exc = assertThrows(RuntimeException.class, () -> {
//            FileManager.readFile(   "./.....");
//        });
//        assertEquals(exc.getClass(), RuntimeException.class);
//    }
//
//    @Test
//    void readCorrect() throws RuntimeException
//    {
//      Object[] continent = {"North America", "Asia", "Africa", "Europe", "South America", "Oceania", "Antarctica"};
//      Object [] readContinents = FileManager.readFile(Constants.CONTINENT_DATA).stream().toArray();
//      assertArrayEquals(continent,readContinents);
//    }
//
//    @Test
//    void allCountriesQueryFailIfSlash()
//    {
//        assertThrows(Exception.class, () ->  AllCountries.allCountriesQuery("Micronesia/Caribbean.txt","SELECT",con));
//    }
//
//    @Test
//    void allRegionsAreIn()
//    {
//        assertEquals(25, FileManager.readFile(Constants.REGION_DATA).size());
//    }
//
//    @Test
//    void allContinentsAreIn()
//    {
//        assertEquals(7, FileManager.readFile(Constants.CONTINENT_DATA).size());
//    }
//
//    @Test
//    void allCountriesAreIn()
//    {
//        assertEquals(239, FileManager.readFile(Constants.COUNTRY_DATA).size());
//    }
//
//    @Test
//    void allDistrictsAreIn()
//    {
//        assertEquals(4079, FileManager.readFile(Constants.DISTRICT_DATA).size());
//    }
//
//    @Test
//    void queryByPopNullArrayPassed() {
//        assertNull(Query.allInListByPop(null, null));
//    }
//
//    @Test
//    void queryAllDataFromArrayAdded() {
//        HashMap<String,String> map = Query.allInListByPop(null, new ArrayList<>(List.of("Zero")));
//        assertTrue(map.containsKey("Zero"));
//        assertEquals(1, map.size());
//    }


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

}
