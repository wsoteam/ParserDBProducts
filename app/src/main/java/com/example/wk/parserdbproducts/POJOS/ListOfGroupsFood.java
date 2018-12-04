package com.example.wk.parserdbproducts.POJOS;

import java.io.Serializable;
import java.util.List;

public class ListOfGroupsFood implements Serializable {
    private String name;
    private List<GroupOfFood> listOfGroupsOfFood;

    public ListOfGroupsFood() {
    }

    public ListOfGroupsFood(String name, List<GroupOfFood> listOfGroupsOfFood) {
        this.name = name;
        this.listOfGroupsOfFood = listOfGroupsOfFood;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GroupOfFood> getListOfGroupsOfFood() {
        return listOfGroupsOfFood;
    }

    public void setListOfGroupsOfFood(List<GroupOfFood> listOfGroupsOfFood) {
        this.listOfGroupsOfFood = listOfGroupsOfFood;
    }
}
