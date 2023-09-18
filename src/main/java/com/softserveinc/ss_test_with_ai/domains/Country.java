package com.softserveinc.ss_test_with_ai.domains;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Comparator;

public class Country {

    public Country() {
    }
    public Country(String name, long population) {
        setName(new Names(name));
        this.population = population;
    }

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

        public Names() {
        }

        public Names(String common) {
            setCommon(common);
        }

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
