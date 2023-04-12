package com.example.kompas2;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlacesListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlacesListFragment extends Fragment {

    public PlacesListFragment() {
        // Required empty public constructor
    }

    public static PlacesListFragment newInstance() {
        return new PlacesListFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_places_list, container, false);
        final ImageButton jogja = rootView.findViewById(R.id.jogja);
        final ImageButton solo = rootView.findViewById(R.id.solo);
        final ImageButton semarang = rootView.findViewById(R.id.semarang);

        jogja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add_mem = new Intent(rootView.getContext(), Jogja.class);
                startActivity(add_mem);
            }
        });

        solo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add_mem = new Intent(rootView.getContext(), Solo.class);
                startActivity(add_mem);
            }
        });

        semarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add_mem = new Intent(rootView.getContext(), Semarang.class);
                startActivity(add_mem);
            }
        });

        return rootView;
    }
}