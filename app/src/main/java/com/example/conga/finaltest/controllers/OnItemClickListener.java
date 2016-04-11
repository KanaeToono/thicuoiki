package com.example.conga.finaltest.controllers;

import android.view.View;
import android.widget.AdapterView;

/**
 * Created by ConGa on 6/04/2016.
 */
public class OnItemClickListener implements View.OnClickListener {
    private static String TAG = AdapterView.OnItemClickListener.class.getSimpleName();
    private int position;
    private OnItemClickCallBack mOnItemClickCallBack;
    @Override
    public void onClick(View view) {
          mOnItemClickCallBack.onItemClicked(view, position);
    }
}
