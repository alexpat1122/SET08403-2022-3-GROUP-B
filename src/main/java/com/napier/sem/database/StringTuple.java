package com.napier.sem.database;

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
