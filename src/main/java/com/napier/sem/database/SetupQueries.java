package com.napier.sem.database;


import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
/** starting up queries to set up files with constants **/
public final class SetupQueries {


    public static ArrayList<String> regionList(java.sql.Connection con) {
        ArrayList<String> regions = new ArrayList<>();
        try {
            Statement stm = con.createStatement();
            ResultSet rset = stm.executeQuery("SELECT region FROM country GROUP BY region;");
            while (rset.next()) {
                String region = rset.getString("Region");
                regions.add(region);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return regions;
    }

    public static  ArrayList<String> continentList(java.sql.Connection con) {
        ArrayList<String> continents = new ArrayList<>();
        try {
            Statement stm = con.createStatement();
            ResultSet rset = stm.executeQuery("SELECT continent FROM country GROUP BY continent;");
            while (rset.next()) {
                String continent = rset.getString("Continent");
                continents.add(continent);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return continents;
    }

    public static  ArrayList<String> countryList(java.sql.Connection con) {
        ArrayList<String> countries = new ArrayList<>();
        try {
            Statement stm = con.createStatement();
            ResultSet rset = stm.executeQuery("SELECT name FROM country");
            while (rset.next()) {
                String country = rset.getString("Name");
                countries.add(country);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return countries;
    }

    public static  ArrayList<String> districtList(java.sql.Connection con) {
        ArrayList<String> districts = new ArrayList<>();
        try {
            Statement stm = con.createStatement();
            ResultSet rset = stm.executeQuery("SELECT district FROM city");
            while (rset.next()) {
                String district = rset.getString("District");
                districts.add(district);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return districts;
    }
}
