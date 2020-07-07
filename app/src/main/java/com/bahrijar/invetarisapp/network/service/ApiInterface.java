package com.bahrijar.invetarisapp.network.service;

import com.bahrijar.invetarisapp.model.Barang;
import com.bahrijar.invetarisapp.model.Laporan;
import com.bahrijar.invetarisapp.network.Endpoint;
import com.bahrijar.invetarisapp.network.response.KelasResponse;
import com.bahrijar.invetarisapp.network.response.LaporanResponse;
import com.bahrijar.invetarisapp.network.response.UserResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiInterface {

    // Login
    @FormUrlEncoded
    @POST(Endpoint.LOGIN)
    Call<UserResponse> loginRequest(@Field("no_induk") String no_induk, @Field("password") String password);

    // List Kelas
    @GET(Endpoint.API_KELAS)
    Call<KelasResponse> getClasses(@Header("Authorization") String token);

    @GET(Endpoint.API_KELAS + "/{id}/")
    Call<Barang> getidKelas(@Path("id") int id);

    // Laporan
    @Multipart
    @POST(Endpoint.API_POST_LAPORAN)
    Call<LaporanResponse> postLaporan(
            @Header("Authorization") String token,
            @Part("id_pelapor") RequestBody id_pelapor,
            @Part("id_ruang") RequestBody id_ruang,
            @Part("deskripsi") RequestBody deskripsi
//            @Part MultipartBody.Part foto_kerusakan
    );

}
//
//    @Header("Authorization") String token,
//    @Part("id_pelapor") RequestBody id_pelapor,
//    @Part("id_ruang") RequestBody  id_ruang,
//    @Part("deskripsi") RequestBody  deskripsi);

//    RequestBody reqIdPelapor = RequestBody.create(MediaType.parse("multipart/form-data"),
//            sharedPrefManager.getSpNoinduk());
//    RequestBody reqIdruangan = RequestBody.create(MediaType.parse("multipart/form-data"),
//            id_ruangan.toLowerCase());
//    RequestBody deskripsi = RequestBody.create(MediaType.parse("multipart/form-data"),
//            vDeskripsi);