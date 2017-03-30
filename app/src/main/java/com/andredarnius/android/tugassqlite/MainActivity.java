package com.andredarnius.android.tugassqlite;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.andredarnius.android.tugassqlite.Adapter.RecyclerViewAdapter;
import com.andredarnius.android.tugassqlite.ClickHelper.RecyclerItemClickListener;
import com.andredarnius.android.tugassqlite.DataMahasiswa.CrudMhs;
import com.andredarnius.android.tugassqlite.POJO.Mahasiswa;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private RecyclerViewAdapter adapter;
    private CrudMhs dbHandler;
    private TextView txt_resultadapter;
    private List<Mahasiswa> mahasiswaList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
        initRecyclerView();
        cekDataRecyclerView();

    }

    private void cekDataRecyclerView() {
        if(adapter.getItemCount() == 0){
            txt_resultadapter.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
        else {
            txt_resultadapter.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);

            recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(),
                    new RecyclerItemClickListener.OnItemClickListener() {

                        @Override
                        public void onItemClick(View view, int position) {
                            // TODO Handle item click
                            Mahasiswa mhs = mahasiswaList.get(position);

                            //Toast.makeText(MainActivity.this, "Klik di " + nama, Toast.LENGTH_SHORT).show();
                            Intent editActivity = new Intent(MainActivity.this, FormActivity.class);
                            editActivity.putExtra("mode", "edit");
                            editActivity.putExtra("id_mhs", mhs.getNim());

                            startActivity(editActivity);
                        }
                    }));
        }
    }

    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.rv_mhs);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        dbHandler = new CrudMhs(this);
        mahasiswaList = dbHandler.getAll();

        adapter = new RecyclerViewAdapter(mahasiswaList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initComponents() {
        txt_resultadapter = (TextView) findViewById(R.id.txt_resultadapter);
    }

    public void btnAddAction(View view) {
        Intent addActivity = new Intent(MainActivity.this, FormActivity.class);
        addActivity.putExtra("mode", "tambah");
        startActivity(addActivity);
    }

}
