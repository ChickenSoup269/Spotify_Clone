package com.example.spotify;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class FragmentTrangChinh extends Fragment {

    private RecyclerView parentRecyclerView;
    private RecyclerView.Adapter ParentAdapter;
    ArrayList<ParentModel> parentModelArrayList = new ArrayList<>();
    private RecyclerView.LayoutManager parentLayoutManager;
    private ViewPager2 viewPager2;
    private Handler sliderHandler = new Handler();
    private SliderAdapter sliderAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trang_chinh, container, false);
        viewPager2 = view.findViewById(R.id.viewPagerImageSlider);

        // Initialize and set up buttons
        Button btnAll = view.findViewById(R.id.btnAll);
        Button btnPopular = view.findViewById(R.id.btnPopular);
        Button btnKids = view.findViewById(R.id.btnkids);
        Button btnAnime = view.findViewById(R.id.btnAnime);
        Button btnAction = view.findViewById(R.id.btnAction);
        Button btnComedies = view.findViewById(R.id.btnComedies);
        Button[] buttons = {btnAll, btnPopular, btnKids, btnAnime, btnAction, btnComedies};

        for (Button button : buttons) {
            button.setBackgroundColor(Color.parseColor("#FFFFFF"));
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (Button b : buttons) {
                        b.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        b.setTextColor(Color.parseColor("#000000"));
                        b.setTypeface(null, Typeface.BOLD);
                    }

                    button.setBackgroundColor(Color.parseColor("#E50914"));
                    button.setTextColor(Color.parseColor("#FFFFFF"));
                    button.setTypeface(null, Typeface.BOLD);
                }
            });
        }

        // Set default color for "All" button
        btnAll.setBackgroundColor(Color.parseColor("#E50914"));
        btnAll.setTextColor(Color.parseColor("#FFFFFF"));
        btnAll.setTypeface(null, Typeface.BOLD);

        List<SliderItem> sliderItems = new ArrayList<>();
        sliderAdapter = new SliderAdapter(sliderItems, viewPager2);


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