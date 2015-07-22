package com.example.vijayyalasangimath.firstapp;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NavigationDrawerFragment extends Fragment {
    TextView text;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle args) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        String menu = getArguments().getString("Menu");
        text= (TextView) view.findViewById(R.id.section_label);
        text.setText(menu);
        return view;
    }

}