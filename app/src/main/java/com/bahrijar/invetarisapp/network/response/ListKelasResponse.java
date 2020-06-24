package com.bahrijar.invetarisapp.network.response;

import com.bahrijar.invetarisapp.model.Barang;
import com.bahrijar.invetarisapp.model.Kelas;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListKelasResponse {
    @SerializedName("barang")
    @Expose
    private List<Kelas> kelas = null;

    public List<Kelas> getKelas() {
        return kelas;
    }

    public void setKelas(List<Kelas> kelas) {
        this.kelas = kelas;
    }
}
