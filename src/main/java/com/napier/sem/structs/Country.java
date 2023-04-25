package com.napier.sem.structs;

//in- memory class for country reports
public class Country {

    /****/
  private final String code;
  private final String name;
  private final Continent continent;
  private final String region;
  private final int population;
  private final String capital;

  public Country(String code, String name, Continent continent, String region, int population, String capital) {
      this.code = code;
      this.name = name;
      this.continent = continent;
      this.region = region;
      this.population = population;
      this.capital = capital;
  }

    @Override
    public String toString() {
        return "Country{" +
                "code=" + code  + ", name=" + name +
                ", continent=" + continent + ", region=" + region +
                ", population=" + population + ", capital=" + capital + '}';
    }
}
