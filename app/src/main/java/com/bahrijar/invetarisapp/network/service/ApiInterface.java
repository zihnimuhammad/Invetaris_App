package com.bahrijar.invetarisapp.network.service;

import com.bahrijar.invetarisapp.model.Barang;
import com.bahrijar.invetarisapp.model.Kelas;
import com.bahrijar.invetarisapp.network.Endpoint;
import com.bahrijar.invetarisapp.network.response.KelasResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET(Endpoint.API_KELAS)
    Call<KelasResponse> getClasses();

    @GET(Endpoint.API_KELAS + "/{id}/")
    Call<Barang> getidKelas(@Path("id") int id);
}
