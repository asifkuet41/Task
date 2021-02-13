package com.example.task.auth;

import android.app.Application;
import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.task.database.User;
import com.example.task.repo.UserRepository;

public class AuthViewModel extends AndroidViewModel {

    public String userId = null;
    public String password = null;
    public String name =  null;
    public String shop = null;
    public String department = null;

    public AuthListener authListener;

    private UserRepository userRepository;
   /* private LiveData<String>password;
    private LiveData<User>user;*/

    public AuthViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
    }


    public void onLoginButtonClick(){
        authListener.onStarted();
        if((userId == null || userId.isEmpty()) && (password == null || password.isEmpty())){
            authListener.onFailure();
            return;
        }
        LiveData<String> loginResponse = userRepository.getPassword(userId);
        if(loginResponse.toString() == null){
            authListener.onSuccess(loginResponse,userId);
        }else{
            authListener.onSuccess(loginResponse,userId);
        }

    }

    public void onSignUp(View view){
        Intent signUpIntent = new Intent(view.getContext(), SignupActivity.class);
        view.getContext().startActivity(signUpIntent);
    }

    public void onSignUpButtonClick(){
        authListener.onStarted();
        if((userId == null|| userId.isEmpty())||(password == null || password.isEmpty())||
                (name == null || name.isEmpty())||(shop == null || shop.isEmpty())|| (department == null)|| department.isEmpty()){
            authListener.onFailure();
            return;
        }
        int _shop = Integer.parseInt(shop.toString().trim());
        User newUser = new User(userId,password,name,"12-10-1994","01688725527","Programmer",_shop,department);
         userRepository.insert(newUser);
         LiveData<Boolean> isTrue = new MutableLiveData<>(true);
         authListener.onRegSuccess(isTrue);
    }

    public void insertUser(User user){
        userRepository.insert(user);
    }
}
