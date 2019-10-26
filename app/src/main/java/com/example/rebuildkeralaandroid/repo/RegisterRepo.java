package com.example.rebuildkeralaandroid.repo;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.rebuildkeralaandroid.data.model.ApiResponse;
import com.example.rebuildkeralaandroid.data.model.ApiTokenModel;
import com.example.rebuildkeralaandroid.data.model.LoginModel;
import com.example.rebuildkeralaandroid.data.model.RegisterModel;
import com.example.rebuildkeralaandroid.utility.RetrofitClient;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterRepo {
    private static final String TAG = "UsersRepo";
    private static RegisterRepo repoInstance;
    private final Application application;

    private RegisterRepo(Application application) {
        this.application = application;
    }

    public static RegisterRepo getInstance(Application application) {
        if (repoInstance == null) {
            repoInstance = new RegisterRepo(application);
        }
        return repoInstance;
    }

    public MutableLiveData<ApiResponse> userLogin(LoginModel loginModel) {
        MutableLiveData<ApiResponse> mutableLiveData = new MutableLiveData<>();
        Call<ApiTokenModel> loginCall = RetrofitClient.getInstance(application).apiInterface().loginUser(loginModel);
        loginCall.enqueue(new Callback<ApiTokenModel>() {
            @Override
            public void onResponse(@NotNull Call<ApiTokenModel> call, @NotNull Response<ApiTokenModel> response) {
                if (!response.isSuccessful()) {
                    mutableLiveData.setValue(new ApiResponse(response.message()));
                    return;
                }
                mutableLiveData.setValue(new ApiResponse(response.body()));
            }

            @Override
            public void onFailure(@NotNull Call<ApiTokenModel> call, @NotNull Throwable t) {
                mutableLiveData.setValue(new ApiResponse(t.getMessage()));
            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<ApiResponse> getRegisterUserDetail(RegisterModel registerModel) {
        final MutableLiveData<ApiResponse> mutableLiveData = new MutableLiveData<>();
        Call<RegisterModel> call;
        call = RetrofitClient.getInstance(application).apiInterface().registerUser(registerModel);
        call.enqueue(new Callback<RegisterModel>() {
            @Override
            public void onResponse(@NotNull Call<RegisterModel> call, @NotNull retrofit2.Response<RegisterModel> response) {
                if (!response.isSuccessful()) {
                    Log.e(TAG, "onResponse: " + "ErrorCode : " + response.code());
                    mutableLiveData.setValue(new ApiResponse(response.message()));
                    return;
                }
                mutableLiveData.setValue(new ApiResponse(response.body()));
            }

            @Override
            public void onFailure(@NotNull Call<RegisterModel> call, @NotNull Throwable t) {
                mutableLiveData.setValue(new ApiResponse(t.getLocalizedMessage()));
            }
        });
        return mutableLiveData;
    }
}

