package com.example.conga.finaltest.models;

import android.view.View;

/**
 * Created by ConGa on 6/04/2016.
 */
public class PendingDismissData implements Comparable<PendingDismissData> {
    public int position;
    public View view;

    public PendingDismissData(int position, View view) {
        this.position = position;
        this.view = view;
    }

    @Override
    public int compareTo(PendingDismissData other) {
        // Sort by descending position
        return other.position - position;
    }
}