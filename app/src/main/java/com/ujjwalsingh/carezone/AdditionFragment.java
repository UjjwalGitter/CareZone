package com.ujjwalsingh.carezone;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class AdditionFragment extends Fragment {
Spinner spinner;
Spinner daySpinner;
Spinner perdaySpinner;
    public AdditionFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_addition_drug, container, false);
        spinner = view.findViewById(R.id.spinner);
        daySpinner = view.findViewById(R.id.spinnerDay);
        perdaySpinner = view.findViewById(R.id.spinnerPerd);

        final String[] items = new String[] {"Every Day", "Two", "Three"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(),
                R.layout.spinner_lay, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                items[0] = "One";
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        return view;
    }

    public void initiateSpinner(){

    }

}