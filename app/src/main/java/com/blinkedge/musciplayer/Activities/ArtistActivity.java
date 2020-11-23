package com.blinkedge.musciplayer.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.blinkedge.musciplayer.R;

public class ArtistActivity extends AppCompatActivity {

    private RecyclerView artistRecyclerView;
    private ImageView customActionBackImageArtist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);

        id();
        onClick();


    }

    private void onClick() {
        customActionBackImageArtist.setOnClickListener(v -> {
            Intent intent = new Intent(ArtistActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void id() {
        artistRecyclerView = findViewById(R.id.artistRecyclerView);
        customActionBackImageArtist = findViewById(R.id.customActionBackImageArtist);
    }
}