package com.example.spotify;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DangKy extends AppCompatActivity {
    // Đăng ký tài khoản
    FloatingActionButton fabLayoutDangKy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        addEvents();
        addControls();
    }
    public void addEvents(){
        fabLayoutDangKy = (FloatingActionButton) findViewById(R.id.fabLayoutDangKy);
    }

    public void addControls(){
        fabLayoutDangKy.setOnClickListener(v -> onBackPressed());
    }
}