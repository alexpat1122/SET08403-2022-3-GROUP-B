package com.napier.sem.structs;

/**store all capital city data**/
public class City extends CapitalCity {


    private final String district;

    public City(String name, String country, int population, String district) {
        super(name, country, population);
        this.district = district;
    }

    @Override
    public String toString() {
        return "City{name=" + this.getName() + ", country=" + this.getCountry() + ", district=" + district +
                ", population=" + this.getPopulation() + '}';
    }
}
