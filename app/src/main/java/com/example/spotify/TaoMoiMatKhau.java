package com.example.spotify;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TaoMoiMatKhau extends AppCompatActivity {
    // Tạo mới mật khẩu
    FloatingActionButton fabLayoutTaoMoiMatKhau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tao_moi_mat_khau);
        addEvents();
        addControls();
    }
    public void addEvents(){
        fabLayoutTaoMoiMatKhau = (FloatingActionButton) findViewById(R.id.fabLayoutTaoMoiMatKhau);
    }

    public void addControls(){
        fabLayoutTaoMoiMatKhau.setOnClickListener(v -> onBackPressed());
    }
}