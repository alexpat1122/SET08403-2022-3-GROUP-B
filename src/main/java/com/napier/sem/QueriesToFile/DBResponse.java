package com.napier.sem.QueriesToFile;

import com.napier.sem.database.Connection;
import com.napier.sem.structs.CapitalCity;
import com.napier.sem.structs.City;
import com.napier.sem.structs.Continent;
import com.napier.sem.structs.Country;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DBResponse implements  Response{

        /** Class that makes queries to the database itself **/

        private static java.sql.Connection con = Connection.con;



        public  ArrayList<String> countriesByPopDesc(String query) {
            ArrayList<String> countries = new ArrayList<>();
            try {
                // Create an SQL statement
                Statement stmt = con.createStatement();
                // Execute SQL statement
                ResultSet rset = stmt.executeQuery(query);
                // Return new country if valid.
                // Check one is returned
                while (rset.next()) {
                    String code = rset.getString("Code");
                    String name = rset.getString("country.Name");
                    Continent continent = Continent.getContinent(rset.getString("Continent"));
                    String region = rset.getString("Region");
                    int population = rset.getInt("country.Population");
                    String capital = rset.getString("city.name");
                    countries.add(new Country(code, name, continent, region, population, capital).toString());
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Failed to get details");
            }
            return countries;
        }

        public   ArrayList<String> citiesByPopDesc(String query) {

            ArrayList<String> cities = new ArrayList<>();
            try {
                // Create an SQL statement
                Statement stmt = con.createStatement();
                // Execute SQL statement
                ResultSet rset = stmt.executeQuery(query);
                // Return new country if valid.
                // Check one is returned

                while (rset.next()) {
                    String name = rset.getString("city.Name");
                    String country = rset.getString("country.Name");
                    String district = rset.getString("district");
                    int population = rset.getInt("city.population");
                    cities.add(new City(name, country, population, district).toString());
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Failed to get details");
            }
            return cities;
        }

        public ArrayList<String> capitalCitiesByPopDesc(String query) {

            ArrayList<String> cities = new ArrayList<>();
            try {
                // Create an SQL statement
                Statement stmt = con.createStatement();
                // Execute SQL statement
                ResultSet rset = stmt.executeQuery(query);
                // Return new country if valid.
                // Check one is returned

                while (rset.next()) {
                    String name = rset.getString("city.Name");
                    String country = rset.getString("country.Name");
                    int population = rset.getInt("city.population");
                    cities.add(new CapitalCity(name, country, population).toString());
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Failed to get details");
            }
            return cities;
        }

        public  long pop(String col, String query) {

            try {
                // Create an SQL statement
                Statement stmt = con.createStatement();
                // Execute SQL statement
                ResultSet rset = stmt.executeQuery(query);
                // Check one is returned
                while(rset.next()) {
                    return rset.getLong(col);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Failed to get details");
            }
            return 0;
        }

        public  int pop(String query) {
            try {
                // Create an SQL statement
                Statement stmt = con.createStatement();
                // Execute SQL statement
                ResultSet rset = stmt.executeQuery(query);
                // Check one is returned
                while(rset.next()) {
                    return rset.getInt("population");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Failed to get details");
            }
            return 0;
        }

        public  ArrayList<String> popByCity(String query) {
            ArrayList<String> result = new ArrayList<>();
            try{
                Statement stmt = con.createStatement();
                ResultSet rset = stmt.executeQuery(query);
                while(rset.next()) {
                    String country = rset.getString("name");
                    int pop = rset.getInt("population");
                    result.add(country + ": " + pop);
                }
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Failed to get details");
            }
            return result;
        }

        public  long populationForLanguage(String query) {
            long sum = 0;
            try {
                Statement stmt = con.createStatement();
                ResultSet rset = stmt.executeQuery(query);
                while (rset.next()) {
                    double percentage = rset.getDouble("percentage");
                    long population = rset.getLong("population");
                    sum += population * percentage / 100;
                }
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Failed to get details");
            }
            return sum;
        }
}
