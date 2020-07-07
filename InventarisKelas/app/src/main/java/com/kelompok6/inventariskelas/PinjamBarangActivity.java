package com.kelompok6.inventariskelas;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class PinjamBarangActivity extends AppCompatActivity {

    ImageButton btnMin, btnPlus;
    Button btnPinjam;
    ListView lvPinjam;
    SimpleAdapter adapter;
    TimePickerDialog picker;
    EditText etMulai, etSelesai;

    HashMap<String, String> map;
    ArrayList<HashMap<String, String>> listBarang;
    String[] idBarang;
    String[] nmBarang;
    String[] jnsBarang;
    String[] jmlBarang;
    String[] icBarang;
    int cal_quantity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinjam_barang);

        btnPinjam = (Button) findViewById(R.id.btn_pinjam_barang);
        lvPinjam = (ListView) findViewById(R.id.list_pinjam);
        etMulai =(EditText)findViewById(R.id.time_mulai);
        etMulai.setInputType(InputType.TYPE_NULL);
        etSelesai =(EditText)findViewById(R.id.time_selesai);
        etSelesai.setInputType(InputType.TYPE_NULL);

        etMulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);
                // time picker dialog
                picker = new TimePickerDialog(PinjamBarangActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                etMulai.setText(sHour + ":" + sMinute);
                            }
                        }, hour, minutes, true);
                picker.show();
            }
        });

        etSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);
                // time picker dialog
                picker = new TimePickerDialog(PinjamBarangActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                etSelesai.setText(sHour + ":" + sMinute);
                            }
                        }, hour, minutes, true);
                picker.show();
            }
        });

        idBarang = new  String[]{
                "0","1","2","3","4"
        };
        nmBarang = new String[] {
                "Meja","Kursi","White Board","Meja Dosen","Kursi Dosen"
        };
        jnsBarang = new String[]{
                "DMB-02","DCB-02","Sakura","Lion 106 N","Vortex V Series"
        };
        jmlBarang = new String[]{
                "0","0","0","0","0"
        };
        icBarang = new String[]{
                Integer.toString(R.drawable.ic_meja),Integer.toString(R.drawable.ic_kursi),Integer.toString(R.drawable.ic_whiteboard),
                Integer.toString(R.drawable.ic_meja),Integer.toString(R.drawable.ic_kursi)
        };
        listBarang = new ArrayList<HashMap<String, String>>();

        for (int i=0; i<idBarang.length; i++){
            map = new HashMap<String, String>();
            map.put("ID Barang", idBarang[i]);
            map.put("Nama Barang", nmBarang[i]);
            map.put("Jenis Barang", jnsBarang[i]);
            map.put("Jumlah Barang", jmlBarang[i]);
            map.put("Gambar Barang", icBarang[i]);
            listBarang.add(map);
        }
        String[] from = {"Nama Barang", "Jenis Barang", "Jumlah Barang","Gambar Barang"};
        int[] to = {R.id.nama_barang,R.id.jenismerk_barang,R.id.jumlah_barang,R.id.icon_barang};

        adapter = new SimpleAdapter(this, listBarang, R.layout.item_pinjam_barang, from, to){
            @Override
            public View getView(final int position, View convertView, ViewGroup parent){
                View v = super.getView(position, convertView, parent);
                final TextView tv_jumlah_barang = (TextView) v.findViewById(R.id.jumlah_barang);
                final int get_jumlah = Integer.parseInt(tv_jumlah_barang.getText().toString());

                btnMin = (ImageButton) v.findViewById(R.id.btn_minus);
                btnMin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        cal_quantity=Integer.parseInt(tv_jumlah_barang.getText().toString());
                        if(cal_quantity!=1){
                            cal_quantity=cal_quantity-1;
                        }
                        tv_jumlah_barang.setText(cal_quantity+"");
                    }
                });

                btnPlus = (ImageButton) v.findViewById(R.id.btn_plus);
                btnPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        cal_quantity=Integer.parseInt(tv_jumlah_barang.getText().toString());
                        cal_quantity=cal_quantity+1;
                        tv_jumlah_barang.setText(cal_quantity+"");
                    }
                });
                return v;
            }
        };

        lvPinjam.setAdapter(adapter);

        btnPinjam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toBerhasil = new Intent(PinjamBarangActivity.this, BerhasilActivity.class);
                startActivity(toBerhasil);
            }
        });

    }

    public void backtoHome(View view) {
        Intent backtoHome = new Intent(PinjamBarangActivity.this, MainActivity.class);
        backtoHome.addCategory(Intent.CATEGORY_LAUNCHER);
        backtoHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(backtoHome);
    }
}
