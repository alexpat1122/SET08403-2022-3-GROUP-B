package com.napier.sem.structs;

public class Population {

    /****/

    private final String name;
    private final long  total;

    private final long  inCity;

    private final long  inCountry;

    private final double percentageInCity;

    private final double percentageInCountry;

    public Population(String name, long total, long inCity, long inCountry, double percentageInCity, double percentageInCountry) {
        this.name = name;
        this.total = total;
        this.inCity = inCity;
        this.inCountry = inCountry;
        this.percentageInCity = percentageInCity;
        this.percentageInCountry = percentageInCountry;

    }

    @Override
    public String toString() {
        return "Population{" + "name=" + name  +
                ", total=" + total +
                ", inCity=" + inCity +
                ", inCountry=" + inCountry +
                ", percentageInCity=" + percentageInCity +
                ", percentageInCountry=" + percentageInCountry +
                '}';
    }
}
