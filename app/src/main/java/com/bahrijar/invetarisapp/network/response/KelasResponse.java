package com.bahrijar.invetarisapp.network.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.bahrijar.invetarisapp.model.Barang;
import com.bahrijar.invetarisapp.model.Kelas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

//public class BaseResponse<T> {
//    @SerializedName("data")
//    private T data;
//
//    public List<Barang> barang;
//
//
//    public List<Kelas> getData() {
//        return (List<Kelas>) data;
//    }
//
//
//
//    public void setData(T data) {
//        this.data = data;
//    }
//}
public class KelasResponse{


    @SerializedName("classes")
    @Expose
    private List<Kelas> kelas = null;

    public List<Kelas> getKelas() {
        return kelas;
    }

    public void setKelas(List<Kelas> kelas) {
        this.kelas = kelas;
    }


}