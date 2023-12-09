package com.example.spotify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // Trang giới thiệu
    Button btnDangNhap_gioiThieu, btnDangKy_gioiThieu;
    TextView txViewSkip;
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
        txViewSkip = (TextView) findViewById(R.id.txViewSkip);
    }

    public void addControls(){
        btnDangNhap_gioiThieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DangNhap.class);
                startActivity(intent);
            }
        });

        btnDangKy_gioiThieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DangKy.class);
                startActivity(intent);
            }
        });

        txViewSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TrangChu.class);
                startActivity(intent);
            }
        });
    }
}