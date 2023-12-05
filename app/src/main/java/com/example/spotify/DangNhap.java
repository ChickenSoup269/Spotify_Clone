package com.example.spotify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DangNhap extends AppCompatActivity {
    // Đăng nhập tài khoản
    FloatingActionButton fabLayoutDangNhap;
    TextView txViewQuenMatKhau;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        addEvents();
        addControls();
    }

    public void addEvents(){
        fabLayoutDangNhap = (FloatingActionButton) findViewById(R.id.fabLayoutDangNhap);
        txViewQuenMatKhau = (TextView) findViewById(R.id.txViewQuenMatKhau);
    }

    public void addControls(){
        fabLayoutDangNhap.setOnClickListener(v -> onBackPressed());
        txViewQuenMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangNhap.this, TaoMoiMatKhau.class);
                startActivity(intent);
            }
        });
    }
}