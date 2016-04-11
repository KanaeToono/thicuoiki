package com.example.conga.finaltest.interfaces;

/**
 * Created by ConGa on 1/04/2016.
 */
public interface IItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
