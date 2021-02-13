package com.example.task.home.ui.notifications;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.task.database.User;
import com.example.task.repo.UserRepository;

public class NotificationsViewModel extends AndroidViewModel {

    private MutableLiveData<String> mText;
    private UserRepository userRepository;
    private MutableLiveData<User>mUser;

    public NotificationsViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
    }

    public LiveData<User> getUser(String userId) {
        return userRepository.getUser(userId);
    }
}