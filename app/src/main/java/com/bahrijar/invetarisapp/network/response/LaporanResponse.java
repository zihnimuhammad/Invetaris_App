package com.bahrijar.invetarisapp.network.response;

import com.bahrijar.invetarisapp.model.Laporan;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LaporanResponse {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Laporan laporan;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Laporan getData() {
        return laporan;
    }

    public void setData(Laporan data) {
        this.laporan = data;
    }

}
