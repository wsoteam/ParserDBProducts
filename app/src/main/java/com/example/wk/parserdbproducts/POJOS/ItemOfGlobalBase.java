package com.example.wk.parserdbproducts.POJOS;

import java.io.Serializable;

public class ItemOfGlobalBase implements Serializable {
    private String name;
    private String description;
    private String composition;
    private String properties;
    private String calories;
    private String protein;
    private String fat;
    private String carbohydrates;
    private String url_of_images;

    public ItemOfGlobalBase() {
    }

    public ItemOfGlobalBase(String name, String description, String composition, String properties, String calories, String protein, String fat, String carbohydrates, String url_of_images) {
        this.name = name;
        this.description = description;
        this.composition = composition;
        this.properties = properties;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.carbohydrates = carbohydrates;
        this.url_of_images = url_of_images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComposition() {
        return composition;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public String getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(String carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public String getUrl_of_images() {
        return url_of_images;
    }

    public void setUrl_of_images(String url_of_images) {
        this.url_of_images = url_of_images;
    }
}
