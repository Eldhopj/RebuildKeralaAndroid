package org.rebuildearth.rebuildkeralaandroid.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.rebuildearth.rebuildkeralaandroid.data.model.ApiResponse;
import org.rebuildearth.rebuildkeralaandroid.data.model.RegisterModel;
import org.rebuildearth.rebuildkeralaandroid.repo.AppRepo;

public class RegisterViewModel extends AndroidViewModel {
    private final AppRepo appRepo;

    public RegisterViewModel(@NonNull Application application) {
        super(application);
        appRepo = AppRepo.getInstance(application);
    }

    public LiveData<ApiResponse> registerUser(RegisterModel registerModel) {
        return appRepo.getRegisterUserDetail(registerModel);
    }
}