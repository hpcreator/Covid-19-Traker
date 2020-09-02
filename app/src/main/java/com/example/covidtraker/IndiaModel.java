package com.example.covidtraker;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class IndiaModel {
    private String activeCases;
    private String activeCasesNew;
    private String recovered;
    private String recoveredNew;
    private String deaths;
    private String deathsNew;
    private String totalCases;

    @SerializedName("regionData")
    private List<StateModel> stateModel;

    public IndiaModel(String activeCases, String activeCasesNew, String recovered, String recoveredNew, String deaths, String deathsNew, String totalCases, List<StateModel> stateModel) {
        this.activeCases = activeCases;
        this.activeCasesNew = activeCasesNew;
        this.recovered = recovered;
        this.recoveredNew = recoveredNew;
        this.deaths = deaths;
        this.deathsNew = deathsNew;
        this.totalCases = totalCases;
        this.stateModel = stateModel;
    }

    public String getActiveCases() {
        return activeCases;
    }

    public String getActiveCasesNew() {
        return activeCasesNew;
    }

    public String getRecovered() {
        return recovered;
    }

    public String getRecoveredNew() {
        return recoveredNew;
    }

    public String getDeaths() {
        return deaths;
    }

    public String getDeathsNew() {
        return deathsNew;
    }

    public String getTotalCases() {
        return totalCases;
    }

    public List<StateModel> getStateModel() {
        return stateModel;
    }

    public static class StateModel {
        private String region;
        private String totalInfected;
        private String newInfected;
        private String recovered;
        private String newRecovered;

        @SerializedName("deceased")
        private String deaths;

        @SerializedName("newDeceased")
        private String newDeaths;

        public StateModel(String region, String totalInfected, String newInfected, String recovered, String newRecovered, String deaths, String newDeaths) {
            this.region = region;
            this.totalInfected = totalInfected;
            this.newInfected = newInfected;
            this.recovered = recovered;
            this.newRecovered = newRecovered;
            this.deaths = deaths;
            this.newDeaths = newDeaths;
        }

        public String getRegion() {
            return region;
        }

        public String getTotalInfected() {
            return totalInfected;
        }

        public String getNewInfected() {
            return newInfected;
        }

        public String getRecovered() {
            return recovered;
        }

        public String getNewRecovered() {
            return newRecovered;
        }

        public String getDeaths() {
            return deaths;
        }

        public String getNewDeaths() {
            return newDeaths;
        }
    }
}
