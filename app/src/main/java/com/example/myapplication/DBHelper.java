package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Login.db";

    public DBHelper(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        // Membuat tabel "users" dengan kolom email dan password.
        MyDB.execSQL("CREATE TABLE users (Username TEXT PRIMARY KEY, Password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        // Hapus tabel "users" jika sudah ada.
        MyDB.execSQL("DROP TABLE IF EXISTS users");
    }

    public Boolean insertData(String Username, String Password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Username", Username);
        contentValues.put("Password", Password);
        long result = MyDB.insert("users", null, contentValues);
        if (result==-1)return false;
        else
            return true;
    }

    public Boolean checkusername(String Username) {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM users WHERE Username = ?", new String[]{Username});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String Username, String Password) {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM users WHERE Username = ? AND Password = ?", new String[]{Username, Password});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }
}
