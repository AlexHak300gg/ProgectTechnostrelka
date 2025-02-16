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

public class LoginActivity extends AppCompatActivity {
    private EditText loginEditText, passwordEditText;
    private CheckBox rememberMeCheckBox;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEditText = findViewById(R.id.login);
        passwordEditText = findViewById(R.id.password);
        rememberMeCheckBox = findViewById(R.id.rememberMeCheckBox);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authenticateUser();
            }
        });
    }

    private void authenticateUser() {
        String login = loginEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        boolean rememberMe = rememberMeCheckBox.isChecked();

        new AuthenticateUserAsyncTask(AppDatabase.getDatabase(this), new AuthenticateUserAsyncTask.AuthenticateListener() {
            @Override
            public void onAuthenticate(User user) {
                if (user != null) {
                    Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
            }
        }).execute(login, password);
    }

    private static class AuthenticateUserAsyncTask extends AsyncTask<String, Void, User> {
        private UserDao userDao;
        private AuthenticateListener listener;

        AuthenticateUserAsyncTask(AppDatabase db, AuthenticateListener listener) {
            userDao = db.userDao();
            this.listener = listener;
        }

        @Override
        protected User doInBackground(String... credentials) {
            return userDao.authenticate(credentials[0], credentials[1]);
        }

        @Override
        protected void onPostExecute(User user) {
            listener.onAuthenticate(user);
        }

        interface AuthenticateListener {
            void onAuthenticate(User user);
        }
    }
}