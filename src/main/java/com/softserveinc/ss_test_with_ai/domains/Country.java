package com.softserveinc.ss_test_with_ai.domains;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Country {

    private Names name;

    @JsonProperty("population")
    private long population;

    public Names getName() {
        return name;
    }

    public void setName(Names name) {
        this.name = name;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public static class Names {
        private String common;

        @JsonProperty("common")
        public String getCommon() {
            return common;
        }

        @JsonProperty("common")
        public void setCommon(String common) {
            this.common = common;
        }
    }
}
