package com.example.task.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.task.R;
import com.example.task.auth.AuthListener;
import com.example.task.auth.AuthViewModel;
import com.example.task.databinding.ActivitySignupBinding;
import com.example.task.utils.AuthUtils;

public class SignupActivity extends AppCompatActivity implements AuthListener {
    AuthViewModel authViewModel;
    Context context;
    ActivitySignupBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        context = this;
        authViewModel = ViewModelProviders.of(this).get(AuthViewModel.class);
        binding.setViewmodel(authViewModel);
        authViewModel.authListener = this;

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,DEPARTMENTS);
        binding.signupDepartmentEditText.setAdapter(adapter);
    }

    private static final String[] DEPARTMENTS = new String[]{
            "IT","Administration","MT","BFCC","BPC"
    };


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    public void onStarted() {

    }

    @Override
    public void onSuccess(LiveData<String> loginResponse,String userId) {
            // Nothing todo
    }

    @Override
    public void onFailure() {

    }

    @Override
    public void onRegSuccess(LiveData<Boolean> regResponse) {

        regResponse.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    AuthUtils.showDialog(context,"Success","You are successfully registered ",R.drawable.ic_check);
                }
            }
        });

    }
}