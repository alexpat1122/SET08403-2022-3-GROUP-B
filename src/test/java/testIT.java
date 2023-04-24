import com.napier.sem.App;
import com.napier.sem.QueriesToFile.DBResponse;
import com.napier.sem.QueriesToFile.Response;
import com.napier.sem.database.Connection;
import com.napier.sem.structs.CapitalCity;
import com.napier.sem.structs.City;
import com.napier.sem.structs.Continent;
import com.napier.sem.structs.Country;
import org.junit.jupiter.api.*;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class testIT {
    Response response = new DBResponse();

    @Test
    void appConnected() {
        assertTrue(App.connected(false));
    }

    @Test
    void graciousDisconnect() {
        Connection.disconnect();
    }

    @Test
    void countriesByPopDesc() {
        assertEquals(0, response.countriesByPopDesc("").size());
    }

    @Test
    void citiesByPopDesc() {
        assertEquals(0, response.citiesByPopDesc("").size());
    }

    @Test
    void capitalsByPopDesc() {
        assertEquals(0, response.capitalCitiesByPopDesc("").size());
    }

    @Test
    void pop() {
        assertEquals(0, response.pop("", ""));
    }

    @Test
    void pop1() {
        assertEquals(0, response.pop(""));
    }

    @Test
    void popByCity() {
        assertEquals(0, response.popByCity("").size());
    }

    @Test
    void popByLanguage() {
        assertEquals(0, response.populationForLanguage(""));
    }
}
