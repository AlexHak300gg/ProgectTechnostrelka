package com.example.technostrelka.Class;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.technostrelka.R;

public class CreateNewComicActivity extends AppCompatActivity {

    private EditText comicTitleEditText;
    private EditText comicDescriptionEditText;
    private Button createComicButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_comic);

        // Initialize views
        comicTitleEditText = findViewById(R.id.comicTitleEditText);
        comicDescriptionEditText = findViewById(R.id.comicDescriptionEditText);
        createComicButton = findViewById(R.id.createComicButton);

        // Create comic button click listener
        createComicButton.setOnClickListener(v -> {
            String comicTitle = comicTitleEditText.getText().toString();
            String comicDescription = comicDescriptionEditText.getText().toString();
            // Add logic to create the comic
            Toast.makeText(CreateNewComicActivity.this, "Comic created: " + comicTitle, Toast.LENGTH_SHORT).show();
            finish(); // Close the CreateNewComicActivity
        });
    }
}