package com.example.task.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.task.database.User;

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Query("SELECT password FROM user_table WHERE staffNo = :userId")
    LiveData<String> getPassword(String userId);

    @Query("SELECT *FROM user_table where staffNo = :userId")
    LiveData<User> getUser(String userId);
}
