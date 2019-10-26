package com.example.rebuildkeralaandroid.networkInterface;

import com.example.rebuildkeralaandroid.pojoModel.RegisterModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RegesterApiInterface {
    @FormUrlEncoded
    @POST("api/accounts/register")
    Call<RegisterModel> registerUser(
            @Field("username") String username,
            @Field("password") String password,
            @Field("email") String email,
            @Field("name") String name);
}
