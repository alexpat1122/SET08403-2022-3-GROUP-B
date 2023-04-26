package com.napier.sem.database;

/** Helper class to store 2 queries in a map **/
public class StringTuple {

    private final String allPop;
    private  final String cityPop;

    public StringTuple(String allPop, String cityPop) {
        this.allPop = allPop;
        this.cityPop = cityPop;
    }

    public String getAllPop() {
        return allPop;
    }

    public String getCityPop() {
        return cityPop;
    }
}
