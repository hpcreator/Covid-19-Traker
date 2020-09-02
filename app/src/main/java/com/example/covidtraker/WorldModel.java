package com.example.covidtraker;

public class WorldModel {

    private String cases;
    private String recovered;
    private String critical;
    private String active;
    private String todayCases;
    private String deaths;
    private String todayDeaths;
    private String affectedCountries;

    public WorldModel() {
    }

    public WorldModel(String cases, String recovered, String critical, String active, String todayCases, String deaths, String todayDeaths, String affectedCountries) {
        this.cases = cases;
        this.recovered = recovered;
        this.critical = critical;
        this.active = active;
        this.todayCases = todayCases;
        this.deaths = deaths;
        this.todayDeaths = todayDeaths;
        this.affectedCountries = affectedCountries;
    }

    public String getCases() {
        return cases;
    }

    public String getRecovered() {
        return recovered;
    }

    public String getCritical() {
        return critical;
    }

    public String getActive() {
        return active;
    }

    public String getTodayCases() {
        return todayCases;
    }

    public String getDeaths() {
        return deaths;
    }

    public String getTodayDeaths() {
        return todayDeaths;
    }

    public String getAffectedCountries() {
        return affectedCountries;
    }
}
