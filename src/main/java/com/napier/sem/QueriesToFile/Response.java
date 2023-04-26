package com.napier.sem.QueriesToFile;

import com.napier.sem.structs.CapitalCity;
import com.napier.sem.structs.City;
import com.napier.sem.structs.Continent;
import com.napier.sem.structs.Country;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
/**Response interface**/
public interface Response {

    public ArrayList<String> countriesByPopDesc(String query);

    public ArrayList<String> citiesByPopDesc(String query);

    public ArrayList<String> capitalCitiesByPopDesc(String query);

    public long pop(String col, String query);

    public  int pop(String query);

    public ArrayList<String> popByCity(String query);

    public long populationForLanguage(String query);

}
