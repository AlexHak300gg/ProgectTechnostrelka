package com.example.technostrelka.Class;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.technostrelka.Database.AppDatabase;
import com.example.technostrelka.Database.UserDao;
import com.example.technostrelka.R;
import com.example.technostrelka.Setting.User;

public class RegistrationActivity extends AppCompatActivity {
    private EditText fullNameEditText, loginEditText, emailEditText, mobilePhoneEditText, passwordEditText, repeatPasswordEditText;
    private CheckBox consentCheckBox;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        fullNameEditText = findViewById(R.id.fullName);
        loginEditText = findViewById(R.id.login);
        emailEditText = findViewById(R.id.email);
        mobilePhoneEditText = findViewById(R.id.mobilePhone);
        passwordEditText = findViewById(R.id.password);
        repeatPasswordEditText = findViewById(R.id.repeatPassword);
        consentCheckBox = findViewById(R.id.consentCheckBox);
        registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String fullName = fullNameEditText.getText().toString();
        String login = loginEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String mobilePhone = mobilePhoneEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String repeatPassword = repeatPasswordEditText.getText().toString();
        boolean consent = consentCheckBox.isChecked();

        if (password.equals(repeatPassword) && consent) {
            User user = new User();
            user.fullName = fullName;
            user.login = login;
            user.email = email;
            user.mobilePhone = mobilePhone;
            user.password = password;

            new InsertUserAsyncTask(AppDatabase.getDatabase(this)).execute(user);
        } else {
            Toast.makeText(this, "Passwords do not match or consent not given", Toast.LENGTH_SHORT).show();
        }
    }

    private static class InsertUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        InsertUserAsyncTask(AppDatabase db) {
            userDao = db.userDao();
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.insert(users[0]);
            return null;
        }
    }
}