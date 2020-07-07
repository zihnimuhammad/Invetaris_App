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

public class LaporanFragment extends Fragment {
    ListView lvLaporan;
    SimpleAdapter adapter;

    HashMap<String, String> map;
    ArrayList<HashMap<String, String>> listLaporan;
    String[] nmrLaporan;
    String[] tglLaporan;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.frag_laporan, container, false);

        lvLaporan = (ListView) fragmentView.findViewById(R.id.list_laporanku);

        nmrLaporan = new String[] {
                "#LAP123","#LAP124","#LAP125"
        };
        tglLaporan = new String[]{
                "02-Mar-2020","02-Mar-2020","02-Mar-2020"
        };

        listLaporan = new ArrayList<HashMap<String, String>>();

        for (int i=0; i<nmrLaporan.length; i++){
            map = new HashMap<String, String>();
            map.put("Nomor", nmrLaporan[i]);
            map.put("Tgl", tglLaporan[i]);
            listLaporan.add(map);
        }

        adapter = new SimpleAdapter(fragmentView.getContext(), listLaporan, R.layout.item_laporanku,
                new String[]{"Nomor", "Tgl"}, new int[]{R.id.nomor_laporan,(R.id.tgl_laporan)});
        lvLaporan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent toDetailLaporan = new Intent(view.getContext(),DetailLaporanActivity.class);
                startActivity(toDetailLaporan);
            }
        });
        lvLaporan.setAdapter(adapter);

        return fragmentView;
    } 
}
