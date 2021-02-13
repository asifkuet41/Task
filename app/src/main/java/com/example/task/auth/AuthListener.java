package com.example.task.auth;

import androidx.lifecycle.LiveData;

public interface AuthListener {
    void onStarted();
    void onSuccess(LiveData<String> loginResponse,String userId);
    void onFailure();
    void onRegSuccess(LiveData<Boolean> regResponse);
}