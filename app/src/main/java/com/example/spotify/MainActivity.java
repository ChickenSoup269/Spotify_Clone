package com.example.spotify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    // Trang giới thiệu

    Button btnDangNhap_gioiThieu, btnDangKy_gioiThieu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addEvents();
        addControls();
    }

    public void addEvents(){
        btnDangNhap_gioiThieu = (Button) findViewById(R.id.btnDangNhap_gioiThieu);
        btnDangKy_gioiThieu = (Button) findViewById(R.id.btnDangKy_gioiThieu);
    }

    public void addControls(){
        // Chuyển đến trang đăng nhập
        btnDangNhap_gioiThieu.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, DangNhap.class);
            startActivity(intent);
        }
    });
        // Chuyển đến trang đăng ký
        btnDangKy_gioiThieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DangKy.class);
                startActivity(intent);
            }
        });
    }
}