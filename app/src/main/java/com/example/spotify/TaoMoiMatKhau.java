package com.example.spotify;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;


public class TaoMoiMatKhau extends AppCompatActivity {
    // Tạo mới mật khẩu
    FloatingActionButton fabLayoutTaoMoiMatKhau;
    TextInputLayout txInLayTaoMoiMatKhau_email, txInLayTaoMoiMatKhau_password, txInLayTaoMoiMatKhau_confirmPassword;
    TextInputEditText EdtTxPTaoMoiMatKhau_email, EdtTxPTaoMoiMatKhau_password, EdtTxPTaoMoiMatKhau_confirmPassword;
    Button btnTaoMoiMatKhau;
    CheckBox checkTaoMoiMatKhau_xemMatKhau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tao_moi_mat_khau);
        addControls();
        addEvents();
    }

    public void addControls(){
        fabLayoutTaoMoiMatKhau = (FloatingActionButton) findViewById(R.id.fabLayoutTaoMoiMatKhau);
        // TextInputLayout
        txInLayTaoMoiMatKhau_email = (TextInputLayout) findViewById(R.id.txInLayTaoMoiMatKhau_email);
        txInLayTaoMoiMatKhau_password = (TextInputLayout) findViewById(R.id.txInLayTaoMoiMatKhau_password);
        txInLayTaoMoiMatKhau_confirmPassword = (TextInputLayout) findViewById(R.id.txInLayTaoMoiMatKhau_confirmPassword);
        // TextInputEditText
        EdtTxPTaoMoiMatKhau_email = (TextInputEditText) findViewById(R.id.EdtTxPTaoMoiMatKhau_email);
        EdtTxPTaoMoiMatKhau_password = (TextInputEditText) findViewById(R.id.EdtTxPTaoMoiMatKhau_password);
        EdtTxPTaoMoiMatKhau_confirmPassword = (TextInputEditText) findViewById(R.id.EdtTxPTaoMoiMatKhau_confirmPassword);
        // --
        btnTaoMoiMatKhau = (Button) findViewById(R.id.btnTaoMoiMatKhau);
        checkTaoMoiMatKhau_xemMatKhau = (CheckBox) findViewById(R.id.checkTaoMoiMatKhau_xemMatKhau);
    }

    public void addEvents(){
        fabLayoutTaoMoiMatKhau.setOnClickListener(v -> onBackPressed());
        btnTaoMoiMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkTaoMoiMatKhau(v);
            }
        });

        checkTaoMoiMatKhau_xemMatKhau.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Hiển thị mật khẩu
                    EdtTxPTaoMoiMatKhau_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    EdtTxPTaoMoiMatKhau_confirmPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    // Ẩn mật khẩu
                    EdtTxPTaoMoiMatKhau_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    EdtTxPTaoMoiMatKhau_confirmPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
    }

    private boolean validateEmail(){
        String email = Objects.requireNonNull(txInLayTaoMoiMatKhau_email.getEditText()).getText().toString().trim();

        if(email.isEmpty()){
            txInLayTaoMoiMatKhau_email.setError("Thiếu thông tin email");
            return false;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            txInLayTaoMoiMatKhau_email.setError("Email không hợp lệ");
            return false;
        }
        else {
            txInLayTaoMoiMatKhau_email.setError(null);
            return true;
        }
    }

    private boolean validatePassword(){
        String password = Objects.requireNonNull(txInLayTaoMoiMatKhau_password.getEditText()).getText().toString().trim();

        if(password.isEmpty()){
            txInLayTaoMoiMatKhau_password.setError("Thiếu thông tin mật khẩu");
            return false;
        } else {
            txInLayTaoMoiMatKhau_password.setError(null);
            return true;
        }
    }

    private boolean validateConfirmPassword(){
        String confirmPassword = Objects.requireNonNull(txInLayTaoMoiMatKhau_confirmPassword.getEditText()).getText().toString().trim();
        String password = Objects.requireNonNull(txInLayTaoMoiMatKhau_password.getEditText()).getText().toString().trim();

        if (confirmPassword.isEmpty()) {
            txInLayTaoMoiMatKhau_confirmPassword.setError("Thiếu thông tin xác nhận mật khẩu");
            return false;
        }
        else if (!confirmPassword.equals(password)) {
            txInLayTaoMoiMatKhau_confirmPassword.setError("Mật khẩu xác nhận không khớp");
            return false;
        }
        else {
            txInLayTaoMoiMatKhau_confirmPassword.setError(null);
            return true;
        }
    }

    public void checkTaoMoiMatKhau(View view){
        if(!validateEmail() && !validatePassword() && !validateConfirmPassword()) {
            return;
        }
        if(!validateEmail() || !validatePassword() || !validateConfirmPassword())  {
            return;
        }
        else{
            thongBaoTaoMoiMatKhauThanhCong();
        }
    }

    private void thongBaoTaoMoiMatKhauThanhCong(){
        ConstraintLayout successConstraintLayout =  findViewById(R.id.thongBaoTaoMoiMatKhauThanhCong);
        View view = LayoutInflater.from(TaoMoiMatKhau.this).inflate(R.layout.thong_bao_tao_moi_mat_khau_thanh_cong, successConstraintLayout);
        Button successDone = view.findViewById(R.id.btnTaoMoiMatKhauThanhCong);
        TextView txViewDiemNguoc = view.findViewById(R.id.txViewDiemNguoc);

        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(TaoMoiMatKhau.this);
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();

        // Thời gian xuất hiện của thông báo -> hết time sang trang giới thiệu
        new CountDownTimer(5000, 1000) {
            public void onTick(long millisUntilFinished) {
                txViewDiemNguoc.setText("(" + millisUntilFinished / 1000 + ")");
            }
            public void onFinish() {
                Intent intent = new Intent(TaoMoiMatKhau.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }.start();
        successDone.findViewById(R.id.btnTaoMoiMatKhauThanhCong).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                Intent intent = new Intent(TaoMoiMatKhau.this, DangNhap.class);
                startActivity(intent);
                finish();
            }
        });
        if (alertDialog.getWindow()!= null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
    }
}