package com.example.technostrelka.Class;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.technostrelka.R;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

public class ComicsMenuActivity extends AppCompatActivity {

    private ListView comicsListView;
    private Button backToProfileButton;
    private Button satisfyComicButton;
    private Button createNewComicButton;
    private ImageView comicImageView;
    private int selectedComicIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comics_menu);

        // Initialize views
        comicsListView = findViewById(R.id.comicsListView);
        backToProfileButton = findViewById(R.id.backToProfileButton);
        satisfyComicButton = findViewById(R.id.satisfyComicButton);
        createNewComicButton = findViewById(R.id.createNewComicButton);
        comicImageView = findViewById(R.id.comicImageView);

        // Dummy data for saved comics
        List<String> savedComics = new ArrayList<>();
        savedComics.add("The Amazing Spider-Man #1");
        savedComics.add("Batman: The Dark Knight Returns");
        savedComics.add("Watchmen");
        savedComics.add("Saga Vol. 1");
        savedComics.add("Maus");

        // Load comic image
        String comicImageUrl = "https://example.com/comic-image.jpg"; // Replace with actual image URL
        Picasso.get().load(comicImageUrl).into(comicImageView);

        // Adapter for the ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, savedComics);
        comicsListView.setAdapter(adapter);comicsListView.setOnItemClickListener((parent, view, position, id) -> {
            selectedComicIndex = position;
            Toast.makeText(ComicsMenuActivity.this, "Selected comic: " + savedComics.get(position), Toast.LENGTH_SHORT).show();
        });

        // Satisfy comic button click listener
        satisfyComicButton.setOnClickListener(v -> {
            if (selectedComicIndex!= -1) {
                Toast.makeText(ComicsMenuActivity.this, "Satisfying comic: " + savedComics.get(selectedComicIndex), Toast.LENGTH_SHORT).show();
                // Add logic to satisfy the comic
            } else {
                Toast.makeText(ComicsMenuActivity.this, "Please select a comic", Toast.LENGTH_SHORT).show();
            }
        });

        // Create new comic button click listener
        createNewComicButton.setOnClickListener(v -> {
            Intent createNewComicIntent = new Intent(ComicsMenuActivity.this, CreateNewComicActivity.class);
            startActivity(createNewComicIntent);
        });

        // Back to Profile button click listener
        backToProfileButton.setOnClickListener(v -> {
            // Redirect to ProfileActivity
            Intent profileIntent = new Intent(ComicsMenuActivity.this, ProfileActivity.class);
            startActivity(profileIntent);
            finish(); // Close the ComicsMenuActivity
        });
    }
}