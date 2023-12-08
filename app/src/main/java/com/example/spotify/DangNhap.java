package com.example.spotify;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class DangNhap extends AppCompatActivity {
    // Đăng nhập tài khoản
    FloatingActionButton fabLayoutDangNhap;
    TextView txViewQuenMatKhau;
    Button btnDangNhap;
    ImageButton imgButton_HideShow;
    TextInputLayout txInLayDangNhap_email,txInLayDangNhap_password;
    TextInputEditText EdtTxPDangNhap_email, EdtTxPDangNhap_password;
    CheckBox checkBoxLuuTaiKhoan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        addControls();
        addEvents();
    }

    public void addControls(){
        fabLayoutDangNhap = (FloatingActionButton) findViewById(R.id.fabLayoutDangNhap);
        txViewQuenMatKhau = (TextView) findViewById(R.id.txViewQuenMatKhau);
        btnDangNhap = (Button) findViewById(R.id.btnDangNhap);
        imgButton_HideShow = (ImageButton) findViewById(R.id.imgButton_HideShow);
        txInLayDangNhap_email = (TextInputLayout) findViewById(R.id.txInLayDangNhap_email);
        txInLayDangNhap_password = (TextInputLayout)  findViewById(R.id.txInLayDangNhap_password);
        EdtTxPDangNhap_email = (TextInputEditText) findViewById(R.id.EdtTxPDangNhap_email);
        EdtTxPDangNhap_password = (TextInputEditText) findViewById(R.id.EdtTxPDangNhap_password);
        checkBoxLuuTaiKhoan = (CheckBox) findViewById(R.id.checkLuuTaiKhoan);
    }

    public void addEvents(){
        fabLayoutDangNhap.setOnClickListener(v -> onBackPressed());
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkDangNhap(v);
            }
        });
        txViewQuenMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangNhap.this, TaoMoiMatKhau.class);
                startActivity(intent);
            }
        });
        imgButton_HideShow.setOnClickListener(new View.OnClickListener() {
            boolean isPasswordVisible = false;
            @Override
            public void onClick(View v) {
                isPasswordVisible = !isPasswordVisible;
                // Thay đổi icon khi nhấn vào ImageButton
                if (isPasswordVisible) {
                    imgButton_HideShow.setImageResource(R.drawable.eye_show);
                    // Đổi thành kiểu text để hiển thị mật khẩu
                    if (imgButton_HideShow != null) {
                        EdtTxPDangNhap_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    }
                } else {
                    imgButton_HideShow.setImageResource(R.drawable.eye_hide);
                    // Đổi lại thành kiểu password để ẩn mật khẩu
                    if (EdtTxPDangNhap_password != null) {
                        EdtTxPDangNhap_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    }
                }
                // Đặt con trỏ ở cuối chuỗi mật khẩu
                if (EdtTxPDangNhap_password != null) {
                    EdtTxPDangNhap_password.setSelection(EdtTxPDangNhap_password.getText().length());
                }
            }
        });
    }

    private boolean validateEmail(){
        String email = Objects.requireNonNull(txInLayDangNhap_email.getEditText().getText().toString().trim());
        if(email.isEmpty()){
            txInLayDangNhap_email.setError("Thiếu thông tin email");
            imgButton_HideShow.setVisibility(View.VISIBLE);
            return false;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            txInLayDangNhap_email.setError("Email không hợp lệ");
            imgButton_HideShow.setVisibility(View.VISIBLE);
            return false;
        }
        else {
            txInLayDangNhap_email.setError(null);
            imgButton_HideShow.setVisibility(View.INVISIBLE);
            return true;
        }
    }

    private boolean validatePassword(){
        String password = Objects.requireNonNull(txInLayDangNhap_password.getEditText().getText().toString().trim());

        if(password.isEmpty()){
            txInLayDangNhap_password.setError("Thiếu thông tin mật khẩu");
            imgButton_HideShow.setVisibility(View.VISIBLE);
            return false;
        }
        else {
            txInLayDangNhap_password.setError(null);
            imgButton_HideShow.setVisibility(View.INVISIBLE);
            return true;
        }
    }
    public void checkDangNhap(View view){
        if(!validateEmail() && !validatePassword())  {
            return;
        }
        if(!validateEmail() || !validatePassword())  {
            return;
        }
        else {
            String email = Objects.requireNonNull(txInLayDangNhap_email.getEditText()).getText().toString();
            String password = Objects.requireNonNull(txInLayDangNhap_password.getEditText()).getText().toString();

            // Kiểm tra và lưu thông tin tài khoản nếu ô "Lưu tài khoản" được chọn
            if (checkBoxLuuTaiKhoan.isChecked()) {
                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("savedEmail", email);
                editor.putString("savedPassword", password);
                editor.apply();
            }
            Intent intent = new Intent(DangNhap.this, TrangChu.class);
            startActivity(intent);
        }
    }
}