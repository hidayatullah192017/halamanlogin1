package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DaftarActivity extends AppCompatActivity {

    EditText username, password, confpassword;
    Button Btndaftar;
    TextView txtLogin;
    ImageView Btnback;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        Btndaftar = findViewById(R.id.btn_daftar);
        txtLogin = findViewById(R.id.txt_login);
        confpassword = findViewById(R.id.conf_password);
        Btnback = findViewById(R.id.btn_back);
        DB = new DBHelper(this);

        Btndaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Isi action button

                String Username = username.getText().toString();
                String Password = password.getText().toString();
                String Confpassword = confpassword.getText().toString();

                if (Username.equals("") || Password.equals("") || Confpassword.equals("")){
                    Toast.makeText(DaftarActivity.this, "Mohon isi semua kolom", Toast.LENGTH_SHORT).show();
                }else{
                    if (Password.equals(Confpassword)){
                        boolean checkuser = DB.checkusername(Username);
                        if (!checkuser){
                            boolean insert = DB.insertData(Username, Password);
                            if (insert){
                                Toast.makeText(DaftarActivity.this, "Pendaftaran berhasil!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(DaftarActivity.this, "Pendaftaran gagal! Coba lagi", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(DaftarActivity.this, "Username sudah didaftarkan, segera login", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(DaftarActivity.this, "Password tidak sama", Toast.LENGTH_SHORT).show();
                    }
                }

                if (Username.isEmpty()) {
                    username.setError("Username/email wajib diisi");
                    return;
                }
                if (Password.isEmpty()) {
                    password.setError("Password harus diisi");
                    return;
                }
                if (Confpassword.isEmpty()) {
                    confpassword.setError("Konfirmasi password harus diisi");
                    return;
                }
            }
        });
        Btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DaftarActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Ketika teks "Daftar" diklik, pindah ke DaftarActivity
                Intent intent = new Intent(DaftarActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
