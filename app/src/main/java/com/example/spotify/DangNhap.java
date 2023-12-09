package com.example.spotify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.mindrot.jbcrypt.BCrypt;

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
    DatabaseReference mDatabase;
    // Lưu thông tin tài khoản
    SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_REMEMBER_ME = "rememberMe";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        addControls();
        addEvents();
        luuThongTinTaiKhoan();
    }

    public void addControls(){
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        fabLayoutDangNhap = (FloatingActionButton) findViewById(R.id.fabLayoutDangNhap);
        txViewQuenMatKhau = (TextView) findViewById(R.id.txViewQuenMatKhau);
        btnDangNhap = (Button) findViewById(R.id.btnDangNhap);
        imgButton_HideShow = (ImageButton) findViewById(R.id.imgButton_HideShow);
        txInLayDangNhap_email = (TextInputLayout) findViewById(R.id.txInLayDangNhap_email);
        txInLayDangNhap_password = (TextInputLayout)  findViewById(R.id.txInLayDangNhap_password);
        EdtTxPDangNhap_email = (TextInputEditText) findViewById(R.id.EdtTxPDangNhap_email);
        EdtTxPDangNhap_password = (TextInputEditText) findViewById(R.id.EdtTxPDangNhap_password);
        checkBoxLuuTaiKhoan = (CheckBox) findViewById(R.id.checkLuuTaiKhoan);
        mDatabase = FirebaseDatabase.getInstance().getReference();
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

                if (isPasswordVisible) {
                    imgButton_HideShow.setImageResource(R.drawable.eye_show);
                    if (imgButton_HideShow != null) {
                        EdtTxPDangNhap_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    }
                }
                else {
                    imgButton_HideShow.setImageResource(R.drawable.eye_hide);
                    if (EdtTxPDangNhap_password != null) {
                        EdtTxPDangNhap_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    }
                }
                if (EdtTxPDangNhap_password != null) {
                    EdtTxPDangNhap_password.setSelection(EdtTxPDangNhap_password.getText().length());
                }
            }
        });
    }

    private boolean validateEmail(){
        String email = Objects.requireNonNull(txInLayDangNhap_email.getEditText()).getText().toString().trim();
        if(email.isEmpty()){
            txInLayDangNhap_email.setError("Thiếu thông tin email");
            imgButton_HideShow.setVisibility(View.INVISIBLE);
            return false;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            txInLayDangNhap_email.setError("Email không hợp lệ");
            imgButton_HideShow.setVisibility(View.INVISIBLE);
            return false;
        }
        else {
            txInLayDangNhap_email.setError(null);
            imgButton_HideShow.setVisibility(View.VISIBLE);
            return true;
        }
    }

    private boolean validatePassword(){
        String password = Objects.requireNonNull(txInLayDangNhap_password.getEditText()).getText().toString().trim();

        if(password.isEmpty()){
            txInLayDangNhap_password.setError("Thiếu thông tin mật khẩu");
            imgButton_HideShow.setVisibility(View.INVISIBLE);
            return false;
        }
        else {
            txInLayDangNhap_password.setError(null);
            imgButton_HideShow.setVisibility(View.VISIBLE);
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
            dangNhapUser();
        }
    }
    private void luuThongTinTaiKhoan() {
        // Nếu người dùng đã lưu thông tin tài khoản trong SharedPreferences
        // và checkbox remember me được chọn, hiển thị thông tin tài khoản lên màn hình đăng nhập
        if (sharedPreferences.getBoolean(KEY_REMEMBER_ME, false)) {
            String savedEmail = sharedPreferences.getString(KEY_EMAIL, "");
            String savedPassword = sharedPreferences.getString(KEY_PASSWORD, "");
            EdtTxPDangNhap_email.setText(savedEmail);
            EdtTxPDangNhap_password.setText(savedPassword);
            checkBoxLuuTaiKhoan.setChecked(true);
        }
    }

    private void dangNhapUser() {
        String email = Objects.requireNonNull(txInLayDangNhap_email.getEditText()).getText().toString().trim();
        String password = Objects.requireNonNull(txInLayDangNhap_password.getEditText()).getText().toString().trim();

        //  ?Xác thực thông tin đăng nhập bằng Firebase Realtime Database
        mDatabase.child("users").orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                        // Tìm thấy người dùng có email trùng khớp
                        User user = userSnapshot.getValue(User.class);
                        // kiểm tra password và mã hóa xem có trùng khớp không
                        if (user != null && BCrypt.checkpw(password, user.getPassword())) {

                            //  *Nếu checkbox được check sẽ lưu thông tin tài khoản vào SharedPreferences
                            if (checkBoxLuuTaiKhoan.isChecked()) {
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString(KEY_EMAIL, email);
                                editor.putString(KEY_PASSWORD, password);
                                editor.putBoolean(KEY_REMEMBER_ME, true);
                                editor.apply();
                            } else {
                                // *Nếu không check xóa thông tin tài khoản khỏi SharedPreferences
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.remove(KEY_EMAIL);
                                editor.remove(KEY_PASSWORD);
                                editor.remove(KEY_REMEMBER_ME);
                                editor.apply();
                            }

                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("USER_ID",user.getId());
                            editor.putString("USER_NAME", user.getUsername());
                            editor.putString("USER_EMAIL", user.getEmail());
                            editor.putString("USER_IMAGE", user.getUserImg());
                            editor.putString("USER_BACKGROUND", user.getUserBackground());
//                            editor.putString("USER_DATE_CREATED", user.getDateCreated());
                            editor.apply();

                            Intent intent = new Intent(DangNhap.this, TrangChu.class);
                            startActivity(intent);
                            finish();
                        } else {
                            txInLayDangNhap_password.setError("Sai mật khẩu");
                        }
                    }
                } else {
                    txInLayDangNhap_email.setError("Email không tồn tại!");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // !Xử lý lỗi nếu hiện ở đây
                Toast.makeText(DangNhap.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}