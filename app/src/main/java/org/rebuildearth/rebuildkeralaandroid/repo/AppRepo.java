package org.rebuildearth.rebuildkeralaandroid.repo;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;
import org.rebuildearth.rebuildkeralaandroid.data.model.ApiResponse;
import org.rebuildearth.rebuildkeralaandroid.data.model.ApiTokenModel;
import org.rebuildearth.rebuildkeralaandroid.data.model.LoginModel;
import org.rebuildearth.rebuildkeralaandroid.data.model.RegisterModel;
import org.rebuildearth.rebuildkeralaandroid.utility.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppRepo {
    private static final String TAG = "UsersRepo";
    private static AppRepo repoInstance;
    private final Application application;

    private AppRepo(Application application) {
        this.application = application;
    }

    public static AppRepo getInstance(Application application) {
        if (repoInstance == null) {
            repoInstance = new AppRepo(application);
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

