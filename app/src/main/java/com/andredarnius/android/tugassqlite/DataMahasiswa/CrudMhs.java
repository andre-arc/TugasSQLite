package com.andredarnius.android.tugassqlite.DataMahasiswa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.andredarnius.android.tugassqlite.DBHelper.DBHelper;
import com.andredarnius.android.tugassqlite.POJO.Mahasiswa;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 28-Mar-17.
 */

public class CrudMhs {
    private SQLiteDatabase database;
    private DBHelper dbHelper;

    public CrudMhs(Context context){
        dbHelper = new DBHelper(context);
    }

    private void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    private void close(){
        dbHelper.close();
    }

    public void addMahasiswa(Mahasiswa mahasiswa){
        ContentValues values = new ContentValues();
        values.put(DBHelper.KEY_NAMA, mahasiswa.getNama());
        values.put(DBHelper.KEY_NPM, mahasiswa.getNim());
        values.put(DBHelper.KEY_JURUSAN, mahasiswa.getJurusan());

        //inserting row
        open();
        database.insert(DBHelper.TABLE_NAME, null, values);
        close();
    }

    public List<Mahasiswa> getAll(){
        List<Mahasiswa> listMhs = new ArrayList<>();

        //select all data mahasiswa
        String allMahasiswa = "SELECT * FROM " + DBHelper.TABLE_NAME;
        open();
        Cursor cursor = database.rawQuery(allMahasiswa, null);


        //looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Mahasiswa mahasiswa = new Mahasiswa();
                mahasiswa.setNim(cursor.getString(0));
                mahasiswa.setNama(cursor.getString(1));
                mahasiswa.setJurusan(cursor.getString(2));

                //adding mahasiswa to the list
                listMhs.add(mahasiswa);
            } while (cursor.moveToNext());
        }

        cursor.close();
        close();
        return listMhs;
    }

    public Mahasiswa getMahasiswa(String idMahasiswa){
        String getMhs = "SELECT * FROM " + DBHelper.TABLE_NAME +" WHERE "+ DBHelper.KEY_NPM + " = "+String.valueOf(idMahasiswa);
        open();
        Cursor cursor = database.rawQuery(getMhs, null);

        if (cursor != null)
            cursor.moveToFirst();

        Mahasiswa mahasiswa = new Mahasiswa(cursor.getString(0), cursor.getString(1), cursor.getString(2));

        cursor.close();
        close();
        return mahasiswa;
    }

    public void updateMahasiswa(Mahasiswa mahasiswa){
        ContentValues values = new ContentValues();
        values.put(DBHelper.KEY_NAMA, mahasiswa.getNama());
        values.put(DBHelper.KEY_JURUSAN, mahasiswa.getJurusan());

        open();
        database.update(DBHelper.TABLE_NAME, values, DBHelper.KEY_NPM + " = ?",
                new String[]{String.valueOf(mahasiswa.getNim())});
        close();
    }


    public void deleteMahasiswa(Mahasiswa mahasiswa){
        open();
        database.delete(DBHelper.TABLE_NAME, DBHelper.KEY_NPM + " = ?",
                new String[]{String.valueOf(mahasiswa.getNim())});
        close();
    }
}
