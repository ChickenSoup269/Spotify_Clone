package com.example.spotify;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentListenMusic extends Fragment {
    // Trang fragment Nghe nhạc

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listen_music, container, false);

        // Lấy thông tin bài hát từ Bundle
        Bundle bundle = getArguments();
        if (bundle != null) {
            Songs selectedSong = bundle.getParcelable("selectedSong");
            // Sử dụng thông tin của bài hát để hiển thị trong Fragment này
        }

        return view;
    }
}