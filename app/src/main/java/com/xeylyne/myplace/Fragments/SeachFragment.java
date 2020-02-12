package com.xeylyne.myplace.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xeylyne.myplace.Models.RequestPlace;
import com.xeylyne.myplace.Models.ResultPlace;
import com.xeylyne.myplace.R;
import com.xeylyne.myplace.Adapters.SearchAdapter;
import com.xeylyne.myplace.Utilities.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class SeachFragment extends Fragment {

    SearchAdapter searchAdapter;
    RecyclerView recyclerView;
    ArrayList<ResultPlace> resultPlaces = new ArrayList<>();

    public SeachFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_seach, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);

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
        Call<RequestPlace> call = RetrofitInstance.getInstance().getPlace();
        call.enqueue(new Callback<RequestPlace>() {
            @Override
            public void onResponse(Call<RequestPlace> call, Response<RequestPlace> response) {
                if (response.isSuccessful()){
                    Log.d("SC","SC1");
                    resultPlaces = response.body().getResult();
                    Log.d("SC", response.body().getResult().get(0).getNAMEPLACE());
                    searchAdapter = new SearchAdapter(getContext(), resultPlaces);
                    recyclerView.setAdapter(searchAdapter);
                    searchAdapter.notifyDataSetChanged();
                }
                else {
                    Log.d("ERROR2","SALAH PARAM");
                }
            }

            @Override
            public void onFailure(Call<RequestPlace> call, Throwable t) {
                Log.d("ERROR1", t.getMessage());
            }
        });
    }
}
