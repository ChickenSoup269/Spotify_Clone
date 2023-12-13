package com.example.spotify;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
    private ParentRecyclerViewAdapter parentAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trang_chinh, container, false);
        viewPager2 = view.findViewById(R.id.viewPagerImageSlider);

        // Set up buttons
        Button btnAll = view.findViewById(R.id.btnAll);
        Button btnAlbumAuMy = view.findViewById(R.id.btnAlbumAuMy);
        Button btnAlbumNhacTre = view.findViewById(R.id.btnAlbumNhacTre);
        Button btnAlbumElectronic = view.findViewById(R.id.btnAlbumElectronic);
        Button btnAlbumnNhacTruTinh = view.findViewById(R.id.btnAlbumnNhacTruTinh);
        Button[] buttons = {btnAll, btnAlbumAuMy, btnAlbumNhacTre, btnAlbumElectronic, btnAlbumnNhacTruTinh};

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

                    button.setBackgroundColor(Color.parseColor("#1DB954"));
                    button.setTextColor(Color.parseColor("#FFFFFF"));
                    button.setTypeface(null, Typeface.BOLD);
                }
            });
        }

        // Set default color cho btn All
        btnAll.setBackgroundColor(Color.parseColor("#1DB954"));
        btnAll.setTextColor(Color.parseColor("#FFFFFF"));
        btnAll.setTypeface(null, Typeface.BOLD);

        SliderAdapter sliderAdapter = new SliderAdapter(viewPager2);
        viewPager2.setAdapter(sliderAdapter);
        new ApiDataAlbum(new ApiDataAlbum.ApiDataListener() {
            @Override
            public void onDataFetched(ArrayList<Album> data) {
                List<SliderItem> sliderItems = new ArrayList<>();
                for (Album album : data) {
                    SliderItem sliderItem = new SliderItem();
                    sliderItem.setImageUrl(album.getAlbumThumbnail());
                    sliderItems.add(sliderItem);
                }
                // Cập nhật dữ liệu mới vào SliderAdapter
                sliderAdapter.setSliderItems(sliderItems);
                sliderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String errorMessage) {
                // Xử lý lỗi nếu có
            }
        }).execute();

        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });

        viewPager2.setPageTransformer(compositePageTransformer);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable, 5000);
            }
        });

        // Gọi API để lấy danh sách các album title
        new ApiDataAlbum(new ApiDataAlbum.ApiDataListener() {
            @Override
            public void onDataFetched(ArrayList<Album> data) {
                for (Album album : data) {
                    String albumTitle = album.getAlbumTitle();
                    ArrayList<Songs> songs = (ArrayList<Songs>) album.getSongs(); // Giả sử hàm này trả về danh sách bài hát của album

                    // Tạo mới một đối tượng ParentModel
                    ParentModel parentModel = new ParentModel();
                    parentModel.setAlbumTitle(albumTitle);
                    parentModel.setSongs(songs);

                    // Thêm ParentModel vào danh sách parentModelArrayList
                    parentModelArrayList.add(parentModel);
                }

                // Sau khi cập nhật xong, thông báo cho Adapter biết để cập nhật giao diện
                if (parentAdapter != null) {
                    parentAdapter.notifyDataSetChanged();
                } else {
                    // Nếu adapter không tồn tại, tạo adapter mới và gán vào RecyclerView
                    parentAdapter = new ParentRecyclerViewAdapter(parentModelArrayList, requireContext());
                    parentRecyclerView.setAdapter(parentAdapter);
                }
            }

            @Override
            public void onError(String errorMessage) {
                // Xử lý lỗi nếu có
            }
        }).execute();

        parentRecyclerView = view.findViewById(R.id.Parent_recyclerView);
        parentRecyclerView.setHasFixedSize(true);
        parentLayoutManager = new LinearLayoutManager(requireContext());
        ParentAdapter = new ParentRecyclerViewAdapter(parentModelArrayList, requireContext());
        parentRecyclerView.setLayoutManager(parentLayoutManager);
        parentRecyclerView.setAdapter(ParentAdapter);

        return view;
    }

    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        sliderHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        sliderHandler.postDelayed(sliderRunnable, 3000); // Start the slideshow after 3 seconds of resuming the fragment
    }

}