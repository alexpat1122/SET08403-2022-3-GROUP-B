package com.napier.sem.QueriesToFile;

import java.util.ArrayList;

public class TestResponse implements Response{
    @Override
    public ArrayList<String> countriesByPopDesc(String query) {
        return null;
    }

    @Override
    public ArrayList<String> citiesByPopDesc(String query) {
        return null;
    }

    @Override
    public ArrayList<String> capitalCitiesByPopDesc(String query) {
        return null;
    }

    @Override
    public long pop(String col, String query) {
        return 0;
    }

    @Override
    public int pop(String query) {
        return 0;
    }

    @Override
    public ArrayList<String> popByCity(String query) {
        return null;
    }

    @Override
    public long populationForLanguage(String query) {
        return 0;
    }
}
