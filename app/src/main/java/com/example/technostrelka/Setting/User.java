package com.example.technostrelka.Setting;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String fullName;
    public String login;
    public String email;
    public String mobilePhone;
    public String password;
}