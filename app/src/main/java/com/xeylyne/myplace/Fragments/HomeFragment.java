package com.xeylyne.myplace.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xeylyne.myplace.Adapters.HomeAdapter;
import com.xeylyne.myplace.Models.Place;
import com.xeylyne.myplace.R;
import com.xeylyne.myplace.Utilities.DataHelper;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    HomeAdapter homeAdapter;
    ArrayList<Place> results;
    DataHelper dataHelper;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        dataHelper = new DataHelper(getContext());

        setInitRecyclerView();
        return view;
    }

    private void setInitRecyclerView() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        loadData();
    }

    private void loadData() {
        results = dataHelper.getAllData();
        homeAdapter = new HomeAdapter(getContext(), results);
        recyclerView.setAdapter(homeAdapter);
        homeAdapter.notifyDataSetChanged();
    }
}
