package com.example.lastliteral.Model;

public class userModel {

    String itemName, itemHeight, itemWeight, itemBirthDate;

    public userModel(String itemName, String itemHeight, String itemWeight, String itemBirthDate) {
        this.itemName = itemName;
        this.itemHeight = itemHeight;
        this.itemWeight = itemWeight;
        this.itemBirthDate = itemBirthDate;
    }

    public userModel() {
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemHeight() {
        return itemHeight;
    }

    public void setItemHeight(String itemHeight) {
        this.itemHeight = itemHeight;
    }

    public String getItemWeight() {
        return itemWeight;
    }

    public void setItemWeight(String itemWeight) {
        this.itemWeight = itemWeight;
    }

    public String getItemBirthDate() {
        return itemBirthDate;
    }

    public void setItemBirthDate(String itemBirthDate) {
        this.itemBirthDate = itemBirthDate;
    }
}
