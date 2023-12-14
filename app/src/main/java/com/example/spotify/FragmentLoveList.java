package com.example.spotify;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class FragmentLoveList extends Fragment {

    private RecyclerView recyclerView;
    private LoveListAdapter adapter;
    private ArrayList<LoveList> favoriteSongsList; // Danh sách các bài hát yêu thích
    private MusicViewModel musicViewModel;
    private FloatingActionButton fabLayoutLoveList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_love_list, container, false);
        fabLayoutLoveList = view.findViewById(R.id.fabLayoutLoveList);
        recyclerView = view.findViewById(R.id.recyclerview_favoritesong);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        fabLayoutLoveList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Quay về Fragment trước đó
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                fragmentManager.popBackStack();
            }
        });
        // Khởi tạo danh sách yêu thích và thêm các bài hát vào đây
        favoriteSongsList = new ArrayList<>();
        // Thêm các bài hát yêu thích vào favoriteSongsList (đây là ví dụ, bạn cần thay thế với dữ liệu thực tế)

        // Khởi tạo adapter và gán dữ liệu
        adapter = new LoveListAdapter(favoriteSongsList, getActivity());
        recyclerView.setAdapter(adapter);

        return view;
    }

    // Method để thêm bài hát vào danh sách yêu thích và cập nhật RecyclerView
//    public void addSongToLoveList(LoveList favoriteSong) {
//        // Khởi tạo danh sách nếu chưa có
//        if (favoriteSongsList == null) {
//            favoriteSongsList = new ArrayList<>();
//        }
//        // Thêm bài hát vào danh sách yêu thích
//        favoriteSongsList.add(FragmentListenMusic.favoriteSong);
//        // Nếu adapter chưa được khởi tạo, tạo mới và gán dữ liệu
////        adapter = new LoveListAdapter(favoriteSongsList, getActivity());
////        Log.d("NOTI ADAP", "Adaper rổng");
////        recyclerView.setAdapter(adapter);
//        if (adapter == null) {
//            adapter = new LoveListAdapter(favoriteSongsList, getActivity());
//            recyclerView.setAdapter(adapter);
//            Log.d("NOTI ADAP", "Adaper rổng");
//        } else {
//            // Cập nhật dữ liệu trong adapter và thông báo thay đổi
//            adapter.notifyDataSetChanged();
//        }
    public void addSongToLoveList(LoveList favoriteSong) {
        // Khởi tạo danh sách nếu chưa có
        if (favoriteSongsList == null) {
            favoriteSongsList = new ArrayList<>();
        }

        // Thêm bài hát vào danh sách yêu thích
        favoriteSongsList.add(favoriteSong);

        // Nếu adapter chưa được khởi tạo, tạo mới và gán dữ liệu
        if (adapter == null) {
            adapter = new LoveListAdapter(favoriteSongsList, getActivity());
            recyclerView.setAdapter(adapter);
        } else {
            // Cập nhật dữ liệu trong adapter và thông báo thay đổi
            adapter.notifyDataSetChanged();
        }
    }
}