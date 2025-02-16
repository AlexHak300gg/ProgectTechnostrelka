package com.example.technostrelka.Class;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.technostrelka.R;

public class ProfileActivity extends AppCompatActivity {

    private TextView fullNameTextView, loginTextView, emailTextView, mobilePhoneTextView;
    private Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Initialize views
        fullNameTextView = findViewById(R.id.fullNameTextView);
        loginTextView = findViewById(R.id.loginTextView);
        emailTextView = findViewById(R.id.emailTextView);
        mobilePhoneTextView = findViewById(R.id.mobilePhoneTextView);
        logoutButton = findViewById(R.id.logoutButton);

        Intent intent = getIntent();
        String fullName = intent.getStringExtra("FULL_NAME");
        String login = intent.getStringExtra("LOGIN");
        String email = intent.getStringExtra("EMAIL");
        String mobilePhone = intent.getStringExtra("MOBILE_PHONE");

        fullNameTextView.setText(fullName);
        loginTextView.setText("Login: " + login);
        emailTextView.setText("Email: " + email);
        mobilePhoneTextView.setText("Mobile Phone: " + mobilePhone);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(loginIntent);
                finish();
            }
        });
    }
}