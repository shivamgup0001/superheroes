package com.example.superheroes;

public class data {
   String name;
   Images images;
   Powerstats powerstats;
   Appearance appearance;

    public data(String name, Images images, Powerstats powerstats, Appearance appearance) {
        this.name = name;
        this.images = images;
        this.powerstats = powerstats;
        this.appearance = appearance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public Powerstats getPowerstats() {
        return powerstats;
    }

    public void setPowerstats(Powerstats powerstats) {
        this.powerstats = powerstats;
    }

    public Appearance getAppearance() {
        return appearance;
    }

    public void setAppearance(Appearance appearance) {
        this.appearance = appearance;
    }
}
