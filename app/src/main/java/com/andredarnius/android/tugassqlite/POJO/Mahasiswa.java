package com.andredarnius.android.tugassqlite.POJO;


/**
 * Created by Asus on 27-Mar-17.
 */

public class Mahasiswa {

    public Mahasiswa(){

    }

    //Constructor
    public Mahasiswa(String nim, String nama, String jurusan){
        this.nim = nim;
        this.nama = nama;
        this.jurusan = jurusan;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {

        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

    public String nim;
    public String nama;
    public String jurusan;


}
