package com.bahrijar.invetarisapp.network.service;

import com.bahrijar.invetarisapp.model.Barang;
import com.bahrijar.invetarisapp.network.Endpoint;
import com.bahrijar.invetarisapp.network.response.KelasResponse;
import com.bahrijar.invetarisapp.network.response.UserResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {

    @FormUrlEncoded
    @POST(Endpoint.LOGIN)
    Call<UserResponse> loginRequest(@Field("no_induk") String no_induk, @Field("password") String password);

    @GET(Endpoint.API_KELAS)
    Call<KelasResponse> getClasses(@Header("Authorization") String token);

    @GET(Endpoint.API_KELAS + "/{id}/")
    Call<Barang> getidKelas(@Path("id") int id);
}
