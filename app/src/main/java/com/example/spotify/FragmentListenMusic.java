package com.example.spotify;

import static java.lang.String.valueOf;
import androidx.fragment.app.FragmentManager;
import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.ArrayList;

public class FragmentListenMusic extends Fragment {
    public static LoveList favoriteSong;
    private MediaPlayer mediaPlayer;
    private TextView txtSongName;
    private TextView txtArtistName;
    private ImageView imgSong;
    private boolean isPlaying = true; // Biến để kiểm tra trạng thái play/pause nhạc
    private ImageButton imgButtonPlay, imgButtonNextSong, imgButtonAddLoveList;
    private FloatingActionButton fabLayoutNgheNhac;
    private MusicViewModel musicViewModel;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listen_music, container, false);

        txtSongName = view.findViewById(R.id.songNamePlayTextView);
        txtArtistName = view.findViewById(R.id.artistNamePlayTextView);
        imgSong = view.findViewById(R.id.image_song);
        imgButtonPlay = view.findViewById(R.id.img_btnPlayward);
        imgButtonNextSong = view.findViewById(R.id.img_btnNextward);
        imgButtonAddLoveList = view.findViewById(R.id.img_sound_add);
        fabLayoutNgheNhac = view.findViewById(R.id.fabLayoutNgheNhac);
        musicViewModel = new ViewModelProvider(requireActivity()).get(MusicViewModel.class);


        fabLayoutNgheNhac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Quay về Fragment trước đó
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                fragmentManager.popBackStack();
            }
        });

        // Nhận dữ liệu từ Bundle
        Bundle bundle = getArguments();
        if (bundle != null) {
            String songImage = bundle.getString("thumbnail");
            String songName = bundle.getString("title");
            String artistName = bundle.getString("artist");
            String songAudioUrl = bundle.getString("audio");

            // Hiển thị dữ liệu trên TextViews
            txtSongName.setText(songName);
            txtArtistName.setText(artistName);

            // Sử dụng Glide để tải hình ảnh từ URL và đặt vào ImageView
            Glide.with(this)
                    .load(songImage)
                    .into(imgSong);

            // Khởi tạo và phát nhạc từ songAudioUrl sử dụng MediaPlayer
            mediaPlayer = new MediaPlayer();
            try {
                mediaPlayer.setDataSource(songAudioUrl);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Bắt đầu phát nhạc khi Fragment được tạo
            mediaPlayer.start();

            imgButtonPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    togglePlayPause(); // Gọi hàm xử lý play/pause
                }
            });

            imgButtonNextSong.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    playNextSong(); // Gọi hàm để chuyển đến bài hát tiếp theo
                }
            });

            imgButtonAddLoveList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Lấy dữ liệu từ FragmentListenMusic
                    Bundle bundle = getArguments();
                    if (bundle != null) {
                        String songImage = bundle.getString("thumbnail");
                        String songName = bundle.getString("title");

                        // Tạo một đối tượng LoveList mới từ dữ liệu bài hát
                        LoveList favoriteSong = new LoveList(songImage, songName);

                        // Sử dụng ViewModel để chuyển dữ liệu sang FragmentLoveList
                        musicViewModel.selectSong(favoriteSong);
                    }
                }
            });
        }
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Giải phóng MediaPlayer khi Fragment bị hủy
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    // Hàm xử lý toggle play/pause
    private void togglePlayPause() {
        if (!isPlaying) {
            if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
                mediaPlayer.start();
                isPlaying = true;
                updateImageButton();
            }
        } else {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                isPlaying = false;
                updateImageButton();
            }
        }
    }

    // Hàm cập nhật hình ảnh cho ImageButton
    private void updateImageButton() {
        if (isPlaying) {
            imgButtonPlay.setImageResource(R.drawable.sound_icon_pause); // Đổi ImageView thành Pause
        } else {
            imgButtonPlay.setImageResource(R.drawable.sound_icon_play); // Đổi ImageView thành Play
        }
    }

    // Hàm xử lý chuyển đến bài hát tiếp theo
    private void playNextSong() {
        String nextSongAudioUrl = "URL của bài hát tiếp theo";
        try {
            mediaPlayer.reset(); // Đặt lại MediaPlayer để chuyển đến bài hát mới
            mediaPlayer.setDataSource(nextSongAudioUrl);
            mediaPlayer.prepare();
            mediaPlayer.start();

            isPlaying = true; // Đặt lại trạng thái phát nhạc

            // Cập nhật thông tin bài hát mới trên giao diện người dùng
            txtSongName.setText("Tên bài hát mới");
            txtArtistName.setText("Nghệ sĩ mới");
            Glide.with(FragmentListenMusic.this)
                    .load("URL hình ảnh mới")
                    .into(imgSong);

            // Cập nhật icon play/pause
            updateImageButton();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Error playing next song", Toast.LENGTH_SHORT).show();
        }
    }

    private void addToLoveList(LoveList favoriteSong) {
        FragmentLoveList fragment = new FragmentLoveList();
        Bundle bundle = new Bundle();
        bundle.putString("title", favoriteSong.getSongName());
        bundle.putString("thumbnail", favoriteSong.getHeroImage());

        Log.d("NOTI 2", favoriteSong.toString());

        fragment.addSongToLoveList(favoriteSong);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}