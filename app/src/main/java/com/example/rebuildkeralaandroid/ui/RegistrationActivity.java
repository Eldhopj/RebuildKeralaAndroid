package com.example.rebuildkeralaandroid.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.example.rebuildkeralaandroid.R;
import com.example.rebuildkeralaandroid.data.model.RegisterModel;
import com.example.rebuildkeralaandroid.databinding.ActivityRegistrationBinding;
import com.example.rebuildkeralaandroid.utility.Utility;
import com.example.rebuildkeralaandroid.viewModel.RegisterViewModel;

import java.util.Objects;

public class RegistrationActivity extends AppCompatActivity {
    private static final String TAG = "RegistrationActivity";
    private RegisterViewModel viewModel;
    ActivityRegistrationBinding binding;
    private String userName;
    private String emailID;
    private String password1;
    private String name;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_registration);
        viewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);
//        binding.login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (!(validateEmail() & validateName() & validatePassword() & validateUserName())){
//                    return;
//                }
//                createRegistrationObject();
//            }
//        });
    }

    private void createRegistrationObject() {
        registrationApi(new RegisterModel(userName, emailID, name, password1));
    }

    private void registrationApi(RegisterModel registerModel) {
        viewModel.registerUser(registerModel).observe(this, apiResponse -> {
            progressDialog.dismiss();
        });
    }

    private boolean validateUserName() {
        userName = binding.userName.getEditText().getText().toString().trim();
        if (userName.isEmpty()) {
            binding.userName.setError("Field can't be empty");
            return false;
        } else {
            binding.userName.setError(null);
            return true;
        }
    }

    private boolean validateName() {
        name = binding.name.getEditText().getText().toString().trim();
        if (name.isEmpty()) {
            binding.name.setError("Field can't be empty");
            return false;
        } else {
            binding.name.setError(null);
            return true;
        }
    }

    private boolean validateEmail() {
        emailID = binding.emailId.getEditText().getText().toString().trim();
        if (!(Patterns.EMAIL_ADDRESS.matcher(emailID).matches())) {
            binding.emailId.setError("Enter a valid Email Address");
            return false;
        } else {
            binding.emailId.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        password1 = binding.password1.getEditText().getText().toString().trim();
        String password2 = binding.password2.getEditText().getText().toString().trim();
        if (password1.isEmpty()) {
            binding.password1.setError("Field Cant be Empty");
            return false;
        } else if (password1.length() < 6) {
            binding.password1.setError("Minimum 6 characters needed");
            return false;
        } else if (!Objects.equals(password1, password2))           // checking both passwords are similar
        {
            binding.password2.setError("Password Mismatches");
            return false;
        } else {
            binding.password1.setError(null);
            binding.password2.setError(null);
            return true;
        }

    }

    public void registerUser(View view) {
        if (!(validateEmail() & validateName() & validatePassword() & validateUserName())) {
            return;
        }
        createRegistrationObject();
        progressDialog=Utility.showLoadingDialog(this);
    }
}
