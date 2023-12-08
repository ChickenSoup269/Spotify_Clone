package com.example.spotify;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class DangKy extends AppCompatActivity {
    // Đăng ký tài khoản
    FloatingActionButton fabLayoutDangKy;
    TextInputLayout txInLayDangKy_nameUser, txInLayDangKy_email, txInLayDangKy_password,txInLayDangKy_confirmPassword;
    TextInputEditText EdtTxPDangKy_nameUser, EdtTxPDangKy_email, EdtTxPDangKy_password, EdtTxPDangKy_confirmPassword;
    Button btnDangKy;
    CheckBox checkDangKy_xemMatKhau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        addControls();
        addEvents();
    }
    public void addControls(){
        fabLayoutDangKy = (FloatingActionButton) findViewById(R.id.fabLayoutDangKy);
        // TextInputLayout
        txInLayDangKy_nameUser = (TextInputLayout) findViewById(R.id.txInLayDangKy_nameUser);
        txInLayDangKy_email = (TextInputLayout) findViewById(R.id.txInLayDangKy_email);
        txInLayDangKy_password =  (TextInputLayout) findViewById(R.id.txInLayDangKy_password);
        txInLayDangKy_confirmPassword = (TextInputLayout) findViewById(R.id.txInLayDangKy_confirmPassword);
        // TextInputEditText
        EdtTxPDangKy_nameUser = (TextInputEditText) findViewById(R.id.EdtTxPDangKy_nameUser);
        EdtTxPDangKy_email = (TextInputEditText) findViewById(R.id.EdtTxPDangKy_email);
        EdtTxPDangKy_password = (TextInputEditText) findViewById(R.id.EdtTxPDangKy_password);
        EdtTxPDangKy_confirmPassword = (TextInputEditText) findViewById(R.id.EdtTxPDangKy_confirmPassword);
        // ----
        btnDangKy = (Button) findViewById(R.id.btnDangKy);
        checkDangKy_xemMatKhau = (CheckBox) findViewById(R.id.checkDangKy_xemMatKhau);

    }

    public void addEvents(){
        fabLayoutDangKy.setOnClickListener(v -> onBackPressed());
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkDangKy(v);
            }
        });
        checkDangKy_xemMatKhau.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Hiển thị mật khẩu
                    EdtTxPDangKy_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    EdtTxPDangKy_confirmPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    // Ẩn mật khẩu
                    EdtTxPDangKy_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    EdtTxPDangKy_confirmPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });

    }

    private boolean validateNameUser(){
        String nameUser = Objects.requireNonNull(txInLayDangKy_nameUser.getEditText().getText().toString().trim());

        if(nameUser.isEmpty()){
            txInLayDangKy_nameUser.setError("Thiếu thông tin người dùng");
            return false;
        }
         else if (nameUser.length() > 15){
            txInLayDangKy_nameUser.setError("Tên người dùng quá dài");
            return false;
        }
         else {
             txInLayDangKy_nameUser.setError(null);
             return true;
        }
    }

    private boolean validateEmail(){
        String email = Objects.requireNonNull(txInLayDangKy_email.getEditText().getText().toString().trim());

        if(email.isEmpty()){
            txInLayDangKy_email.setError("Thiếu thông tin email");
            return false;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            txInLayDangKy_email.setError("Email không hợp lệ");
            return false;
        }
        else {
            txInLayDangKy_email.setError(null);
            return true;
        }
    }

    private boolean validatePassword(){
        String password = Objects.requireNonNull(txInLayDangKy_password.getEditText().getText().toString().trim());

        if(password.isEmpty()){
            txInLayDangKy_password.setError("Thiếu thông tin mật khẩu");
            return false;
        }
        else {
            txInLayDangKy_password.setError(null);
            return false;
        }
    }

    private boolean validateConfirmPassword(){
        String confirmPassword = Objects.requireNonNull(txInLayDangKy_confirmPassword.getEditText()).getText().toString().trim();
        String password = Objects.requireNonNull(txInLayDangKy_password.getEditText()).getText().toString().trim();

        if (confirmPassword.isEmpty()) {
            txInLayDangKy_confirmPassword.setError("Thiếu thông tin xác nhận mật khẩu");
            return false;
        }
        else if (!confirmPassword.equals(password)) {
            txInLayDangKy_confirmPassword.setError("Mật khẩu xác nhận không khớp");
            return false;
        }
        else {
            txInLayDangKy_confirmPassword.setError(null);
            return true;
        }
    }

    public void checkDangKy(View view){
        if(!validateNameUser() && !validateEmail() && !validatePassword() && !validateConfirmPassword()) {
            return;
        }
        if(!validateNameUser() || !validateEmail() || !validatePassword() || !validateConfirmPassword())  {
            return;
        }

//            // Tạo dialog thông báo thành công
//            AlertDialog.Builder builder = new AlertDialog.Builder(DangKy.this);
//            builder.setTitle("Đăng ký thành công");
//            builder.setMessage("Bạn đã đăng ký thành công!");
//
//            // Nút OK để đóng dialog và chuyển qua activity đăng nhập
//            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int which) {
//                    Intent intent = new Intent(DangKy.this, DangNhap.class);
//                    startActivity(intent);
//                    dialog.dismiss();
//                    finish(); // Đóng activity đăng ký sau khi chuyển qua đăng nhập
//                }
//            });
//            // Hiển thị dialog
//            AlertDialog dialog = builder.create();
//            dialog.show();
    }
}