package com.napier.sem;

import com.napier.sem.database.Connection;
import com.napier.sem.user.UserChoices;
import java.io.IOException;


public class App {

    private static java.sql.Connection con = null;

    public static void main(String[] args) throws IOException {
        con = Connection.connect();
        //UserChoices.chooseAReport(con);
        Connection.disconnect(con);
    }


}
