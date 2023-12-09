package com.example.spotify;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class FragmentTrangChinh extends Fragment {

    private RecyclerView parentRecyclerView;
    private RecyclerView.Adapter ParentAdapter;
    ArrayList<ParentModel> parentModelArrayList = new ArrayList<>();
    private RecyclerView.LayoutManager parentLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trang_chinh, container, false);

        parentModelArrayList.add(new ParentModel("Album Mùa Xuân"));
        parentModelArrayList.add(new ParentModel("Album Mùa Hè"));
        parentModelArrayList.add(new ParentModel("Album Tết"));
        parentModelArrayList.add(new ParentModel("Album Gái Anime"));
        parentModelArrayList.add(new ParentModel("Album Gái Anime"));
        parentRecyclerView = view.findViewById(R.id.Parent_recyclerView);
        parentRecyclerView.setHasFixedSize(true);
        parentLayoutManager = new LinearLayoutManager(requireContext());
        ParentAdapter = new ParentRecyclerViewAdapter(parentModelArrayList, requireContext()); // or getContext()
        parentRecyclerView.setLayoutManager(parentLayoutManager);
        parentRecyclerView.setAdapter(ParentAdapter);

        return view;
    }
}