package com.napier.sem.database;

import java.sql.DriverManager;
import java.sql.SQLException;

/** responsible for setting connection **/
public final class Connection {



    public static java.sql.Connection con = Connection.connect("localhost:33060",3000);
    public static java.sql.Connection connect(String location, int delay) {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e);
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        // Connection to the database
        int retries = 20;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                //uncomment for git actions
                Thread.sleep(delay);
                // Connect to database
                //uncomment for git actions
                java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://"+ location + "/world?useSSL=true", "root", "example");

              ///uncomment to use database with database navigator
   //             java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:33060/world?useSSL=true", "root", "example");

                System.out.println("Successfully connected");
                return con;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public static void disconnect() {

//        if (con != null)
        {
            try {
                // Close connection
                con.close();
            } catch (Exception e) {
                throw new RuntimeException(("Error closing connection to database"));
            }
        }
    }

}
