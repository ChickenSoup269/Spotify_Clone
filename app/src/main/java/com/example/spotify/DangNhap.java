package com.example.spotify;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DangNhap extends AppCompatActivity {
    // Đăng nhập tài khoản
    FloatingActionButton fabLayoutDangNhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        addEvents();
        addControls();
    }

    public void addEvents(){
        fabLayoutDangNhap = (FloatingActionButton) findViewById(R.id.fabLayoutDangNhap);
    }

    public void addControls(){
        fabLayoutDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {onBackPressed();}});
    }
}