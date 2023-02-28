package com.napier.sem.structs;

//in- memory class for  city reports
public class City extends CapitalCity {
    private final String district;

    public City(String name, String country, int population, String district) {
        super(name, country, population);
        this.district = district;
    }

    @Override
    public String toString() {
        return "City{" + "district='" + district + ", name='" + this.getName() + ", country=" + this.getCountry() +
                ", population=" + this.getPopulation() + '}';
    }
}
