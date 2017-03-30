package com.andredarnius.android.tugassqlite.Adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andredarnius.android.tugassqlite.POJO.Mahasiswa;
import com.andredarnius.android.tugassqlite.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 28-Mar-17.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {


    private List<Mahasiswa> mahasiswaList = new ArrayList<>();

    public RecyclerViewAdapter(List<Mahasiswa> mahasiswaList) {
        this.mahasiswaList = mahasiswaList;
        for(Mahasiswa mahasiswa : mahasiswaList){
            String log = "NIM: " + mahasiswa.getNim() + " , NAMA: " + mahasiswa.getNama() + ", JURUSAN: " + mahasiswa.getJurusan();
            Log.d("Profil Mahasiswa", log);
        }
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_mhs, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
        holder.nama.setText(mahasiswaList.get(position).getNama());
        holder.nim.setText(mahasiswaList.get(position).getNim());
        holder.jurusan.setText(mahasiswaList.get(position).getJurusan());
    }

    @Override
    public int getItemCount() {
        return mahasiswaList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        // di tutorial ini kita hanya menggunakan data String untuk tiap item
        public TextView nama;
        public TextView nim;
        public TextView jurusan;

        public ViewHolder(View v) {
            super(v);
            nama = (TextView) v.findViewById(R.id.item_nama);
            nim = (TextView) v.findViewById(R.id.item_nim);
            jurusan = (TextView) v.findViewById(R.id.item_jurusan);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
