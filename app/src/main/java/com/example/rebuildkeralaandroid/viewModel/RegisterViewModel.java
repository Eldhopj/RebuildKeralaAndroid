package com.example.rebuildkeralaandroid.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.rebuildkeralaandroid.data.model.ApiResponse;
import com.example.rebuildkeralaandroid.data.model.RegisterModel;
import com.example.rebuildkeralaandroid.repo.RegisterRepo;

public class RegisterViewModel extends AndroidViewModel {
    private final RegisterRepo usersRepo;

    public RegisterViewModel(@NonNull Application application) {
        super(application);
        usersRepo = RegisterRepo.getInstance(application);
    }

    public LiveData<ApiResponse> registerUser(RegisterModel registerModel) {
        return usersRepo.getRegisterUserDetail(registerModel);
    }
}