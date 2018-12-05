package com.example.wk.parserdbproducts.POJOForSaveIRL;

import java.io.Serializable;

public class OneItem implements Serializable {
    private String name;
    private String startNumber;
    private String lastNumber;
    private String url;

    public OneItem() {
    }

    public OneItem(String name, String startNumber, String lastNumber, String url) {
        this.name = name;
        this.startNumber = startNumber;
        this.lastNumber = lastNumber;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartNumber() {
        return startNumber;
    }

    public void setStartNumber(String startNumber) {
        this.startNumber = startNumber;
    }

    public String getLastNumber() {
        return lastNumber;
    }

    public void setLastNumber(String lastNumber) {
        this.lastNumber = lastNumber;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
