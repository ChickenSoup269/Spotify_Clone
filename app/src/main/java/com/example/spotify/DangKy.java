package com.example.spotify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.mindrot.jbcrypt.BCrypt;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class DangKy extends AppCompatActivity {
    // *Đăng ký tài khoản
    FloatingActionButton fabLayoutDangKy;
    TextInputLayout txInLayDangKy_nameUser, txInLayDangKy_email, txInLayDangKy_password,txInLayDangKy_confirmPassword;
    TextInputEditText EdtTxPDangKy_nameUser, EdtTxPDangKy_email, EdtTxPDangKy_password, EdtTxPDangKy_confirmPassword;
    Button btnDangKy;
    CheckBox checkDangKy_xemMatKhau;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        addControls();
        addEvents();
    }
    public void addControls(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
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
        String nameUser = Objects.requireNonNull(txInLayDangKy_nameUser.getEditText()).getText().toString().trim();

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
        String email = Objects.requireNonNull(txInLayDangKy_email.getEditText()).getText().toString().trim();

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
        String password = Objects.requireNonNull(txInLayDangKy_password.getEditText()).getText().toString().trim();

        if(password.isEmpty()){
            txInLayDangKy_password.setError("Thiếu thông tin mật khẩu");
            return false;
        }
        else {
            txInLayDangKy_password.setError(null);
            return true;
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
        boolean allValid2 = !validateNameUser() || !validateEmail() || !validatePassword() || !validateConfirmPassword();
        if (!validateNameUser() && !validateEmail() && !validatePassword() && !validateConfirmPassword()) {
            return;
        }
        else if(allValid2){
            return;
        }
        else {
            dangKyTaiKhoan();
        }
    }

    private void dangKyTaiKhoan(){
        String nameUser = Objects.requireNonNull(txInLayDangKy_nameUser.getEditText()).getText().toString().trim();
        String email = Objects.requireNonNull(txInLayDangKy_email.getEditText()).getText().toString().trim();
        String password = Objects.requireNonNull(txInLayDangKy_password.getEditText()).getText().toString().trim();

        mDatabase.child("users").orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    txInLayDangKy_email.setError("Email này đã tồn tại");
                }
                else {
                    // ?Lấy giá trị số lượng user hiện tại
                    String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
                    mDatabase.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            // Tính số lượng người dùng hiện tại bằng cách đếm số lượng nút con của nút "users"
                            int userCount = (int) dataSnapshot.getChildrenCount();
                            // ?id user VD: user_1 -> _2 _3
                            userCount++;

                            String userId = "user_" + userCount;
                            // Tạo đối tượng User mới
                            User user = new User(userId, nameUser, email, hashedPassword);

                            // lưu user vào Realtime Database
                            mDatabase.child("users").child(userId).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        // User được đăng ký thành công
                                        thongBaoDangKyThanhCong();
                                        Log.d("DangKyActivity", "User information saved successfully.");
                                        thongTinTaiKhoanCoBan(userId);
                                    }
                                    else {
                                        // Lỗi khi lưu thông tin người dùng
                                        Toast.makeText(DangKy.this, "Failed to save user information. " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        Log.e("DangKyActivity", "Failed to save user information: " + task.getException().getMessage());
                                    }
                                }
                            });
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(DangKy.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.e("DangKyActivity", "Database Error: " + databaseError.getMessage());
                        }
                    });
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(DangKy.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("DangKyActivity", "Database Error: " + databaseError.getMessage());
            }
        });
    }

    private void thongBaoDangKyThanhCong(){
        ConstraintLayout successConstraintLayout =  findViewById(R.id.thongBaoDangKyThanhCong);
        View view = LayoutInflater.from(DangKy.this).inflate(R.layout.thong_bao_dang_ky_thanh_cong, successConstraintLayout);
        Button successDone = view.findViewById(R.id.btnDangKyThanhCong);
        TextView txViewDiemNguoc = view.findViewById(R.id.txViewDiemNguoc);

        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(DangKy.this);
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();

        // Thời gian xuất hiện của thông báo -> hết time sang trang giới thiệu
        new CountDownTimer(5000, 1000) {
            public void onTick(long millisUntilFinished) {
                txViewDiemNguoc.setText("(" + millisUntilFinished / 1000 + ")");
            }
            public void onFinish() {
                Intent intent = new Intent(DangKy.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }.start();
        successDone.findViewById(R.id.btnDangKyThanhCong).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                Intent intent = new Intent(DangKy.this, DangNhap.class);
                startActivity(intent);
                finish();
            }
        });
        if (alertDialog.getWindow()!= null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();

    }

    // Lấy ngày
    private String formatDate(long millis) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return sdf.format(new Date(millis));
    }

    private void thongTinTaiKhoanCoBan(String userId) {
        // Đưa thông tin user lên realtime database
        String defaultUserImageURL = "https://cdn-icons-png.flaticon.com/512/3177/3177440.png";
        String defaultBackgroundURL = "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/7d947e1c-d377-48d9-ab3e-6cfc41f6faa7/delgp3k-a94205d9-be04-4e4f-8865-71e321a55922.png/v1/fill/w_1192,h_670,q_70,strp/reflections_in_the_moonlight_by_gydw1n_delgp3k-pre.jpg?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7ImhlaWdodCI6Ijw9NzIwIiwicGF0aCI6IlwvZlwvN2Q5NDdlMWMtZDM3Ny00OGQ5LWFiM2UtNmNmYzQxZjZmYWE3XC9kZWxncDNrLWE5NDIwNWQ5LWJlMDQtNGU0Zi04ODY1LTcxZTMyMWE1NTkyMi5wbmciLCJ3aWR0aCI6Ijw9MTI4MCJ9XV0sImF1ZCI6WyJ1cm46c2VydmljZTppbWFnZS5vcGVyYXRpb25zIl19.Mc5Vmza2uAQR7zqKsObL2zh0PaXpdEU7MvtuhGGSodg";
        String defaultPhone = "N/A";
        String defaultSex = "N/A";
        long dateCreated = System.currentTimeMillis();
        String dateCreatedFormatted = formatDate(dateCreated);

        mDatabase.child("users").child(userId).child("userImg").setValue(defaultUserImageURL);
        mDatabase.child("users").child(userId).child("userBackground").setValue(defaultBackgroundURL);
        mDatabase.child("users").child(userId).child("phone").setValue(defaultPhone);
        mDatabase.child("users").child(userId).child("sex").setValue(defaultSex);
        mDatabase.child("users").child(userId).child("dateCreated").setValue(dateCreatedFormatted);

    }
}