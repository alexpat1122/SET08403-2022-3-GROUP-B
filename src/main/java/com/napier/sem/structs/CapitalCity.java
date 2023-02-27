package com.napier.sem.structs;

public class CapitalCity {
    private final String name;
    private final String country;
    private final int population;

    public CapitalCity(String name, String country, int population) {
        this.name = name;
        this.country = country;
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public int getPopulation() {
        return population;
    }

    @Override
    public String toString() {
        return "CapitalCity{" +
                "name='" + name + ", country='" + country +
                ", population=" + population +
                '}';
    }
}
