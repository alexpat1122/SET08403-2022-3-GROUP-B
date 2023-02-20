package com.napier.sem;

import java.sql.*;

public class App {

    private static Connection con = null;

    public static void main(String[] args)
    {
        connect();
        prompts();
        disconnect();

    }

    public  static void connect() {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println(e);
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        // Connection to the database
        int retries = 3;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=true", "root", "example");
                System.out.println("Successfully connected");
                // Wait a bit
                Thread.sleep(10000);
                // Exit for loop
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    public static void disconnect() {

        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }

    public static void prompts() {
        System.out.println("Select a query option: ");
        System.out.println("1: All the countries in the world ....");
        System.out.println("2: The top N populated countries ....");
        System.out.println("3: All the cities ....");
        System.out.println("4: The top N populated cities ....");
        System.out.println("5: All the capital cities ....");
        System.out.println("6: The top N populated capital cities in....");
        System.out.println("6: The population of people, people living in cities, and people not living in cities in each....");
    }
}
