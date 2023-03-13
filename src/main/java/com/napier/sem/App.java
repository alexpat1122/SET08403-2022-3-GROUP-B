package com.napier.sem;

import com.napier.sem.QueriesToFile.AllCities;
import com.napier.sem.QueriesToFile.AllCountries;
import com.napier.sem.database.Connection;


public class App {

    private static java.sql.Connection con = null;

    public static void main(String[] args)  {
        con = Connection.connect();
        if (connected(con)) {
            AllCountries.countryReports(con);
            AllCities.cityReports(con);
        }
        else {
            System.out.println("Not connected to database");
        }
    }



    public static boolean connected(java.sql.Connection con) {
        return con != null;
    }
}
