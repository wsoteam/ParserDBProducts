package com.example.wk.parserdbproducts.POJOS;

import java.io.Serializable;
import java.util.List;

public class GroupOfFood implements Serializable {
    private String name;
    private List<ItemOfGlobalBase> listOfFoodItems;
    private String url_of_image;

    public GroupOfFood() {
    }

    public GroupOfFood(String name, List<ItemOfGlobalBase> listOfFoodItems, String url_of_image) {
        this.name = name;
        this.listOfFoodItems = listOfFoodItems;
        this.url_of_image = url_of_image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ItemOfGlobalBase> getListOfFoodItems() {
        return listOfFoodItems;
    }

    public void setListOfFoodItems(List<ItemOfGlobalBase> listOfFoodItems) {
        this.listOfFoodItems = listOfFoodItems;
    }

    public String getUrl_of_image() {
        return url_of_image;
    }

    public void setUrl_of_image(String url_of_image) {
        this.url_of_image = url_of_image;
    }
}
