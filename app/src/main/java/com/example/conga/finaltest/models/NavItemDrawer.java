package com.example.conga.finaltest.models;

/**
 * Created by ConGa on 7/04/2016.
 */
public class NavItemDrawer {
    String ItemName;
    int imgResID;

    public NavItemDrawer(String itemName, int imgResID) {
        super();
        ItemName = itemName;
        this.imgResID = imgResID;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public int getImgResID() {
        return imgResID;
    }

    public void setImgResID(int imgResID) {
        this.imgResID = imgResID;

    }
}