package org.rebuildearth.rebuildkeralaandroid.networkInterface;

import org.rebuildearth.rebuildkeralaandroid.data.model.ApiTokenModel;
import org.rebuildearth.rebuildkeralaandroid.data.model.LoginModel;
import org.rebuildearth.rebuildkeralaandroid.data.model.RegisterModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegesterApiInterface {
    @POST("api/accounts/register")
    Call<RegisterModel> registerUser(@Body RegisterModel registerModel);

    @POST("api/token/")
    Call<ApiTokenModel> loginUser(@Body LoginModel loginModel);
}
