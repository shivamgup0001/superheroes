package com.example.superheroes;

public class Appearance {
    String gender;
    String race;
    String height[];
    String weight[];

    public Appearance(String gender, String race, String[] height, String[] weight) {
        this.gender = gender;
        this.race = race;
        this.height = height;
        this.weight = weight;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String[] getHeight() {
        return height;
    }

    public void setHeight(String[] height) {
        this.height = height;
    }

    public String[] getWeight() {
        return weight;
    }

    public void setWeight(String[] weight) {
        this.weight = weight;
    }
}
