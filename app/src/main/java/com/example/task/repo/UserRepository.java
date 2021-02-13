package com.example.task.repo;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.task.database.User;
import com.example.task.database.UserDao;
import com.example.task.database.UserDatabase;

public class UserRepository {

    private UserDao userDao;
    private LiveData<String>password;
    private LiveData<User>user;
    public UserRepository(Application application){
        UserDatabase database = UserDatabase.getInstance(application);
        userDao = database.userDao();
    }

    public LiveData<String> getPassword(String userId){
        password = userDao.getPassword(userId);
        return password;
    }

    public LiveData<User> getUser(String userId){
        user = userDao.getUser(userId);
        return user;
    }

    public void insert(User user){
        new InsertUserAsyncTask(userDao).execute(user);
    }

    private static class InsertUserAsyncTask extends AsyncTask<User,Void,Void>{
        private UserDao userDao;

        private InsertUserAsyncTask(UserDao userDao){
            this.userDao = userDao;
        }
        @Override
        protected Void doInBackground(User... users) {
            userDao.insert(users[0]);
            return null;
        }
    }
}
