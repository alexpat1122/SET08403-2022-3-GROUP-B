package com.napier.sem.structs;
//in- memory class for language reports
public  class Language implements Comparable {
    ;

    /****/
    private final String name;

    private final long population;
    private final double percentage;

    public Language(String name, long population, double percentage) {
        this.name = name;
        this.population = population;
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return "Language{" +
                "name='" + name  +
                ", population=" + population +
                ", world percentage=" + percentage +
                '}';
    }
    @Override
    public int compareTo(Object o) {
        if(o.getClass() != this.getClass()) {
            return -1;
        }
        else {
            return Double.compare(this.percentage,((Language) o).percentage);
        }
    }
}
