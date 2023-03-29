package io.carbonchain.hiring.java.domain;

public class Asset {

  private final String name;
  private final String country;
  private final String continent;

  private final String commodity;

  public Asset(String name, String country, String continent, String commodity) {
    this.name = name;
    this.country = country;
    this.continent = continent;
    this.commodity = name;
  }

  public boolean nameMatches(String term) {
    return name.equals(term);
  }

    public String getName() {
        return name;
    }

  public String getCountry() {
    return country;
  }

  public String getContinent() {
    return continent;
  }

  public String getCommodity() {
    return name;
  }
}
