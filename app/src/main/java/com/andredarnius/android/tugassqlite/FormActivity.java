package com.andredarnius.android.tugassqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.andredarnius.android.tugassqlite.Adapter.RecyclerViewAdapter;
import com.andredarnius.android.tugassqlite.DataMahasiswa.CrudMhs;
import com.andredarnius.android.tugassqlite.POJO.Mahasiswa;

import java.util.List;

public class FormActivity extends AppCompatActivity {
    Button delete, submit;
    EditText nim, nama, jurusan;
    Mahasiswa mhs;
    CrudMhs db;
    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initComponent();
    }

    public void initComponent(){
        Bundle extras = getIntent().getExtras();
        delete = (Button) findViewById(R.id.btnDelete);
        submit = (Button) findViewById(R.id.btnSubmit);
        nama = (EditText) findViewById(R.id.nama);
        nim = (EditText) findViewById(R.id.nim);
        jurusan = (EditText) findViewById(R.id.jurusan);

        db = new CrudMhs(this);


        switch (extras.getString("mode")) {
            case "tambah":
                getSupportActionBar().setTitle(R.string.tambah_data);
                delete.setVisibility(View.GONE);

                submit.setText("Tambah");
                submit.setOnClickListener(btnTambah(submit));
                break;

            case "edit":
                getSupportActionBar().setTitle(R.string.edit_data);
                String idMhs = extras.getString("id_mhs");
                Mahasiswa mhs = db.getMahasiswa(idMhs);

                nama.setText(mhs.getNama());
                nim.setText(mhs.getNim());
                jurusan.setText(mhs.getJurusan());

                nim.setEnabled(false);
                submit.setText("Update");
                submit.setOnClickListener(btnUpdate(submit));
                delete.setOnClickListener(btnDelete(delete));
                break;

        }
    }

    View.OnClickListener btnTambah(final Button button){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = new CrudMhs(FormActivity.this);
                formValidation();
            }
        };
    }

    View.OnClickListener btnUpdate(final Button button){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = new CrudMhs(FormActivity.this);
                formValidation();
            }
        };
    }

    View.OnClickListener btnDelete(final Button button){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String formNim = nim.getText().toString();

                db = new CrudMhs(FormActivity.this);
                db.deleteMahasiswa(new Mahasiswa(formNim, null, null));
                List<Mahasiswa> mahasiswaList = db.getAll();
                Toast.makeText(FormActivity.this, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();
                adapter = new RecyclerViewAdapter(mahasiswaList);
                adapter.notifyDataSetChanged();
                startActivity(new Intent(FormActivity.this, MainActivity.class));
                finish();
            }
        };
    }

    // FUNGSI INI UNTUK MEMVALIDASI FORM JIKA ADA YANG KOSONG ATAU TIDAK
    // LALU DILANJUT UNTUK MENJALANKAN PERINTAH SELANJUTNYA
    private void formValidation(){
        Bundle extras = getIntent().getExtras();
        String formNama = nama.getText().toString();
        String formNim = nim.getText().toString();
        String formJurusan = jurusan.getText().toString();

        if(formNama.isEmpty()){
            nama.setError("Nama Harus Diisi");
            nama.requestFocus();

        }
        if(formNim.isEmpty()){
            nim.setError("NIM Harus Diisi");
            nim.requestFocus();

        }
        if(formJurusan.isEmpty()){
            jurusan.setError("Jurusan Harus Diisi");
            jurusan.requestFocus();

        }
        else{
            List<Mahasiswa> mahasiswaList = null;
            switch (extras.getString("mode")) {
                case "tambah":
                    db.addMahasiswa(new Mahasiswa(formNim, formNama,formJurusan));
                    mahasiswaList = db.getAll();
                    Toast.makeText(this, "Data Berhasil Ditambah", Toast.LENGTH_SHORT).show();
                    break;

                case "edit":
                    db.updateMahasiswa(new Mahasiswa(formNim, formNama,formJurusan));
                     mahasiswaList = db.getAll();
                    Toast.makeText(this, "Data Berhasil Diupdate", Toast.LENGTH_SHORT).show();
                    break;

                default:;break;

            }
            adapter = new RecyclerViewAdapter(mahasiswaList);
            adapter.notifyDataSetChanged();
            startActivity(new Intent(FormActivity.this, MainActivity.class));
            finish();
        }

    }
}
