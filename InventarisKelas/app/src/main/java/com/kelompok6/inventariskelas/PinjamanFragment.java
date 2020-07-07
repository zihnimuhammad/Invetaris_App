package com.kelompok6.inventariskelas;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;

public class PinjamanFragment extends Fragment {
    ListView lvPinjaman;
    SimpleAdapter adapter;

    HashMap<String, String> map;
    ArrayList<HashMap<String, String>> listPinjaman;
    String[] kodePinjaman;
    String[] tglPinjaman;
    String[] durasiPinjaman;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.frag_pinjaman, container, false);

        lvPinjaman = (ListView) fragmentView.findViewById(R.id.list_pinjaman);

        kodePinjaman = new String[] {
                "#P123","#P124","#ABC123"
        };
        tglPinjaman = new String[]{
                "02-Mar-2020","02-Mar-2020","02-Mar-2020"
        };
        durasiPinjaman = new String[]{
                "50","30","12"
        };

        listPinjaman = new ArrayList<HashMap<String, String>>();

        for (int i=0; i<kodePinjaman.length; i++){
            map = new HashMap<String, String>();
            map.put("Nomor", kodePinjaman[i]);
            map.put("Tgl", tglPinjaman[i]);
            map.put("Durasi", durasiPinjaman[i]);
            listPinjaman.add(map);
        }

        adapter = new SimpleAdapter(fragmentView.getContext(), listPinjaman, R.layout.item_peminjaman,
                new String[]{"Nomor", "Tgl", "Durasi"}, new int[]{R.id.kode_peminjaman,(R.id.tgl_peminjaman),(R.id.durasi_tersisa)});
        lvPinjaman.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent toDetailPinjaman = new Intent(view.getContext(),DetailPinjamanActivity.class);
                startActivity(toDetailPinjaman);
            }
        });
        lvPinjaman.setAdapter(adapter);

        return fragmentView;
    }
}
