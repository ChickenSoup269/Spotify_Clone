package com.example.spotify;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class FragmentLoveList extends Fragment {

    private RecyclerView recyclerView;
    private LoveListAdapter adapter;
    private ArrayList<LoveList> favoriteSongsList; // Danh sách các bài hát yêu thích

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_love_list, container, false);

        recyclerView = view.findViewById(R.id.recyclerview_favoritesong);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Khởi tạo danh sách yêu thích và thêm các bài hát vào đây
        favoriteSongsList = new ArrayList<>();
        // Thêm các bài hát yêu thích vào favoriteSongsList (đây là ví dụ, bạn cần thay thế với dữ liệu thực tế)

        // Khởi tạo adapter và gán dữ liệu
        adapter = new LoveListAdapter(favoriteSongsList, getActivity());
        recyclerView.setAdapter(adapter);

        return view;
    }

    // Method để thêm bài hát vào danh sách yêu thích và cập nhật RecyclerView
    public void addSongToLoveList(LoveList favoriteSong) {
        // Khởi tạo danh sách nếu chưa có
        if (favoriteSongsList == null) {
            favoriteSongsList = new ArrayList<>();
        }
    }
}