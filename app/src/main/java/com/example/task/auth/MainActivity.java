package com.example.task.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.task.R;
import com.example.task.database.UserSessionManager;
import com.example.task.databinding.ActivityMainBinding;
import com.example.task.home.HomeActivity;

public class MainActivity extends AppCompatActivity implements AuthListener {

    private AuthViewModel authViewModel;
    ActivityMainBinding binding;
    Context context;
    UserSessionManager userSessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        userSessionManager = new UserSessionManager(this);

        if(userSessionManager.isUserLoggedIn()){
            intentToHome();
        }

        context = this;
        authViewModel = ViewModelProviders.of(this).get(AuthViewModel.class);
        binding.setViewmodel(authViewModel);
        authViewModel.authListener = this;

    }

    public void intentToHome(){
        Intent homeIntent = new Intent(this,HomeActivity.class);
        startActivity(homeIntent);
        finish();
    }

    @Override
    public void onStarted() {

    }

    @Override
    public void onSuccess(LiveData<String> loginResponse,String userId) {

        loginResponse.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(s == null){
                    Toast.makeText(context,"Failed",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(context,"Success",Toast.LENGTH_LONG).show();
                    userSessionManager.createUserLoginSession(userId);
                    Intent homeIntent = new Intent(context, HomeActivity.class);
                    startActivity(homeIntent);
                    finish();
                }
            }
        });
    }

    @Override
    public void onFailure() {

    }

    @Override
    public void onRegSuccess(LiveData<Boolean> regResponse) {

    }
}