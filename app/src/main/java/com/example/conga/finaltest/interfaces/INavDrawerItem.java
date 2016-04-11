package com.example.conga.finaltest.interfaces;

/**
 * Created by ConGa on 7/04/2016.
 */
public interface
        INavDrawerItem {
    public int getId();
    public String getLabel();
    public int getType();
    public boolean isEnabled();
    public boolean updateActionBarTitle();
}
