package com.example.rebuildkeralaandroid.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.rebuildkeralaandroid.R;
import com.example.rebuildkeralaandroid.pojoModel.ApiResponse;
import com.example.rebuildkeralaandroid.viewModel.RegisterViewModel;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private RegisterViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);

        viewModel.registerUser().observe(this, new Observer<ApiResponse>() {
            @Override
            public void onChanged(ApiResponse apiResponse) {

            }
        });
    }


}
