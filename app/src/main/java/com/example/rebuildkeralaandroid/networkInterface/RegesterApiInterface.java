package com.example.rebuildkeralaandroid.networkInterface;

import com.example.rebuildkeralaandroid.data.model.ApiTokenModel;
import com.example.rebuildkeralaandroid.data.model.LoginModel;
import com.example.rebuildkeralaandroid.data.model.RegisterModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegesterApiInterface {
    @POST("api/accounts/register")
    Call<RegisterModel> registerUser(@Body RegisterModel registerModel);

    @POST("api/token/")
    Call<ApiTokenModel> loginUser(@Body LoginModel loginModel);
}
