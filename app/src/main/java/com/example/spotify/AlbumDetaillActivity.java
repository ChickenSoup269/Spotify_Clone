package com.example.spotify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.security.AccessController;
import java.util.ArrayList;
import java.util.List;

public class AlbumDetaillActivity extends AppCompatActivity {
    // Trang chi tiết album
    FloatingActionButton fabLayoutAlbumChiTiet;
    ImageView img_album;
    TextView txvTenAlbum, txvMieuTaAlbum;
    RecyclerView recyclerview_albumSong;
    RecyclerView recyclerView;
    SongsAdapter songsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_detaill);
        addControls();
        addEvents();
    }

    public void addControls(){
        fabLayoutAlbumChiTiet = (FloatingActionButton) findViewById(R.id.fabLayoutAlbumChiTiet);
        img_album = (ImageView) findViewById(R.id.img_album);
        txvTenAlbum = (TextView) findViewById(R.id.txvTenAlbum);
        txvMieuTaAlbum = (TextView) findViewById(R.id.txvMieuTaAlbum);
        recyclerview_albumSong = (RecyclerView) findViewById(R.id.recyclerview_albumSong);
    }

    public void addEvents(){
        fabLayoutAlbumChiTiet.setOnClickListener(v -> onBackPressed());

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("selectedAlbum")) {
            Album selectedAlbum = intent.getParcelableExtra("selectedAlbum");
            // Hiển thị thông tin album trên giao diện
            displayAlbumDetails(selectedAlbum);
        }
        recyclerView = findViewById(R.id.recyclerview_albumSong);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        songsAdapter = new SongsAdapter();
        recyclerView.setAdapter(songsAdapter);

        showSongsAlbum(intent);
    }

    private void displayAlbumDetails(Album album) {
        // Sử dụng thông tin album để hiển thị trên giao diện
        Glide.with(this)
                .load(album.getAlbumThumbnail())
                .into(img_album);
        txvTenAlbum.setText(album.getAlbumTitle());
        txvMieuTaAlbum.setText(album.getSortDescription());
    }

    private void showSongsAlbum(Intent intent) {
        if (intent != null && intent.hasExtra("danhSachBaiHat")) {
            ArrayList<Songs> danhSachBaiHat = (ArrayList<Songs>) intent.getSerializableExtra("danhSachBaiHat");
            if (danhSachBaiHat != null) {
                // Hiển thị danh sách bài hát trong RecyclerView của AlbumDetailActivity
                recyclerView = findViewById(R.id.recyclerview_albumSong);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                songsAdapter = new SongsAdapter(danhSachBaiHat);
                recyclerView.setAdapter(songsAdapter);
            }
        }
    }
}