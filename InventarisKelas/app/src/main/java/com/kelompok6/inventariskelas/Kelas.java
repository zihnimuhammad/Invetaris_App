package com.kelompok6.inventariskelas;

import android.os.Parcel;
import android.os.Parcelable;

public class Kelas implements Parcelable {
    private String nama_kelas;
    private String lokasi;
    private String kapasitas;
    private String foto_kelas;

    public String getNama_kelas() {
        return nama_kelas;
    }

    public void setNama_kelas(String nama_kelas) {
        this.nama_kelas = nama_kelas;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getKapasitas() {
        return kapasitas;
    }

    public void setKapasitas(String kapasitas) {
        this.kapasitas = kapasitas;
    }

    public String getFoto_kelas() {
        return foto_kelas;
    }

    public void setFoto_kelas(String foto_kelas) {
        this.foto_kelas = foto_kelas;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nama_kelas);
        dest.writeString(this.lokasi);
        dest.writeString(this.kapasitas);
        dest.writeString(this.foto_kelas);
    }

    public Kelas() {
    }

    protected Kelas(Parcel in) {
        this.nama_kelas = in.readString();
        this.lokasi = in.readString();
        this.kapasitas = in.readString();
        this.foto_kelas = in.readString();
    }

    public static final Parcelable.Creator<Kelas> CREATOR = new Parcelable.Creator<Kelas>() {
        @Override
        public Kelas createFromParcel(Parcel source) {
            return new Kelas(source);
        }

        @Override
        public Kelas[] newArray(int size) {
            return new Kelas[size];
        }
    };
}
