package com.bahrijar.invetarisapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Laporan {
    @SerializedName("id_ruang")
    @Expose
    private String idRuang;
    @SerializedName("deskripsi")
    @Expose
    private String deskripsi;
    @SerializedName("foto_kerusakan")
    @Expose
    private String fotoKerusakan;
    @SerializedName("id_pelapor")
    @Expose
    private String idPelapor;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("id_laporan")
    @Expose
    private String idLaporan;
    @SerializedName("id")
    @Expose
    private Integer id;

    public String getIdRuang() {
        return idRuang;
    }

    public void setIdRuang(String idRuang) {
        this.idRuang = idRuang;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getFotoKerusakan() {
        return fotoKerusakan;
    }

    public void setFotoKerusakan(String fotoKerusakan) {
        this.fotoKerusakan = fotoKerusakan;
    }

    public String getIdPelapor() {
        return idPelapor;
    }

    public void setIdPelapor(String idPelapor) {
        this.idPelapor = idPelapor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIdLaporan() {
        return idLaporan;
    }

    public void setIdLaporan(String idLaporan) {
        this.idLaporan = idLaporan;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
