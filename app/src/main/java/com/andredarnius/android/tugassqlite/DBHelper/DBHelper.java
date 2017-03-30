package com.andredarnius.android.tugassqlite.DBHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Asus on 27-Mar-17.
 */

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "mahasiswa.db";
    public static final String TABLE_NAME = "mahasiswa";
    public static final int DB_VERSION = 1;

    public static final String KEY_NPM = "nim";
    public static final  String KEY_NAMA = "nama";
    public static final String KEY_JURUSAN = "jurusan";
//    public static final String KEY_IMAGE_NAME = "image_name";
//    public static final String KEY_IMAGE_DATA = "image_data";



    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + " (" + KEY_NPM + " TEXT PRIMARY KEY, "+ KEY_NAMA + " TEXT, "+ KEY_JURUSAN + " TEXT);";
        db.execSQL(sql);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop tabel jika sudah pernah ada
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        //create table again
        onCreate(db);
    }
}
