package com.bahrijar.invetarisapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Barang implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("id_kelas")
    @Expose
    private String idKelas;
    @SerializedName("nama_barang")
    @Expose
    private String namabarang;
    @SerializedName("kondisi")
    @Expose
    private String kondisi;
    @SerializedName("jumlah")
    @Expose
    private String jumlah;

    protected Barang(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        idKelas = in.readString();
        namabarang = in.readString();
        kondisi = in.readString();
        jumlah = in.readString();
        barang = in.createTypedArrayList(Barang.CREATOR);
    }

    public static final Creator<Barang> CREATOR = new Creator<Barang>() {
        @Override
        public Barang createFromParcel(Parcel in) {
            return new Barang(in);
        }

        @Override
        public Barang[] newArray(int size) {
            return new Barang[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdKelas() {
        return idKelas;
    }

    public void setIdKelas(String idKelas) {
        this.idKelas = idKelas;
    }

    public String getNamabarang() {
        return namabarang;
    }

    public void setNamabarang(String namabarang) {
        this.namabarang = namabarang;
    }

    public String getKondisi() {
        return kondisi;
    }

    public void setKondisi(String kondisi) {
        this.kondisi = kondisi;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    @SerializedName("barang")
    @Expose
    private List<Barang> barang = null;

    public List<Barang> getBarang() {
        return barang;
    }

    public void setBarang(List<Barang> barang) {
        this.barang = barang;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(id);
        }
        parcel.writeString(idKelas);
        parcel.writeString(namabarang);
        parcel.writeString(kondisi);
        parcel.writeString(jumlah);
        parcel.writeTypedList(barang);
    }
}