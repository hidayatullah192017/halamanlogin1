package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    EditText username, password;
    Button Btnlogin;
    TextView txtDaftar;
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
        DB = new DBHelper(this);


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
