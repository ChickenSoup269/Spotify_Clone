package com.example.spotify;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;


public class FragmentSearch extends Fragment {
    // Trang fragment tìm kiếm
    RecyclerView recyclerView;
    SearchView searchView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        //controller
        recyclerView  = view.findViewById(R.id.ryclerview_search);
        searchView = view.findViewById(R.id.search_list);
        //evenlts
        return view;
    }
}