package com.example.technostrelka.Database;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.technostrelka.Setting.User;

@Dao
public interface UserDao {
    @Insert
    void insert(User user);

    @Query("SELECT * FROM users WHERE login = :login OR email = :login OR mobilePhone = :login")
    User getUserByLoginOrEmailOrPhone(String login);

    @Query("SELECT * FROM users WHERE login = :login AND password = :password")
    User authenticate(String login, String password);
}