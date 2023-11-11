package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;


public class MainActivity extends AppCompatActivity {

    EditText username, password;
    Button Btnlogin;
    TextView txtDaftar;
    CheckBox ingatSaya;
    SharedPreferences sharedPreferences;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        username = findViewById(R.id.username1);
        password = findViewById(R.id.password1);
        Btnlogin = findViewById(R.id.btn_login);
        txtDaftar = findViewById(R.id.txt_daftar);
        ingatSaya = findViewById(R.id.remember_me);
        DB = new DBHelper(this);

        sharedPreferences = getSharedPreferences("myapp-data",MODE_PRIVATE);

        if (sharedPreferences.getString("username", null) != null) {
            username.setText(sharedPreferences.getString("username", null));
        }
        if (sharedPreferences.getString("password", null) != null) {
            password.setText(sharedPreferences.getString("password", null));
        }


        Btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Isi action button

                String Username = username.getText().toString().trim();
                String Password = password.getText().toString().trim();

                if(Username.equals("")||Password.equals(""))
                    Toast.makeText(MainActivity.this, "Mohon isi semua kolom", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkusernamepassword(Username, Password);
                    if (checkuserpass==true){
                        if (ingatSaya.isChecked()) {
                            // Jika kotak centang "ingatSaya" dicentang, simpan data ke SharedPreferences
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("username", Username);
                            editor.putString("password", Password);
                            editor.apply();
                        } else {
                            // Jika tidak dicentang, hapus data yang disimpan sebelumnya
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.remove("username");
                            editor.remove("password");
                            editor.apply();
                        }
                        Toast.makeText(MainActivity.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(MainActivity.this, "Email/Password Salah!", Toast.LENGTH_SHORT).show();
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
            }
        });

        txtDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Ketika teks "Daftar" diklik, pindah ke DaftarActivity
                Intent intent = new Intent(MainActivity.this, DaftarActivity.class);
                startActivity(intent);
            }
        });

    }
}
