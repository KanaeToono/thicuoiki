package com.example.conga.finaltest.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.conga.finaltest.R;

/**
 * Created by ConGa on 11/04/2016.
 */
public class SuperAwesomeCardFragment extends Fragment {
    private static final String ARG_POSITION = "position";

    private int position;

    public static SuperAwesomeCardFragment newInstance(int position) {
        SuperAwesomeCardFragment f = new SuperAwesomeCardFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt(ARG_POSITION);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_view_task_fragment,container,false);
       // ButterKnife.inject(this, rootView);
        ViewCompat.setElevation(rootView, 50);
//        textView.setText("CARD "+position);
        return rootView;
    }
}


