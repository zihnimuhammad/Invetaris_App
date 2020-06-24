package com.bahrijar.invetarisapp.network.response;

import com.bahrijar.invetarisapp.model.Barang;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BarangResponse {
    @SerializedName("barang")
    @Expose
    private List<Barang> barang = null;

    public List<Barang> getBarang() {
        return barang;
    }

    public void setBarang(List<Barang> barang) {
        this.barang = barang;
    }
}
