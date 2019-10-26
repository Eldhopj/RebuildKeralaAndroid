package com.example.rebuildkeralaandroid.repo;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.rebuildkeralaandroid.data.model.ApiResponse;
import com.example.rebuildkeralaandroid.data.model.RegisterModel;
import com.example.rebuildkeralaandroid.utility.RetrofitClient;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;

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

    public MutableLiveData<ApiResponse> getRegisterUserDetail(RegisterModel registerModel) {
        final MutableLiveData<ApiResponse> mutableLiveData = new MutableLiveData<>();
        Call<RegisterModel> call;
        call = RetrofitClient.getInstance(application).registerUser().registerUser(registerModel);
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

