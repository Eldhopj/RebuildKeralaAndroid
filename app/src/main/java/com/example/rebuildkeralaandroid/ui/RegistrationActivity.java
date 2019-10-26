package com.example.rebuildkeralaandroid.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.rebuildkeralaandroid.R;
import com.example.rebuildkeralaandroid.data.model.RegisterModel;
import com.example.rebuildkeralaandroid.viewModel.RegisterViewModel;

public class RegistrationActivity extends AppCompatActivity {
    private static final String TAG = "RegistrationActivity";
    private RegisterViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        viewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);

    }

    private void createNewRegistration(String userName, String email, String name, String password) {
        registrationApi(new RegisterModel(userName, email, name, password));
    }

    private void registrationApi(RegisterModel registerModel) {
        viewModel.registerUser(registerModel).observe(this, apiResponse -> {

        });
    }

}
