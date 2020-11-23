package com.blinkedge.musciplayer.Activities;

import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blinkedge.musciplayer.MusicFilesModal.MusicFilesModal;
import com.blinkedge.musciplayer.R;
import com.blinkedge.musciplayer.RecyclerViewAdapter.ArtistDetailRecyclerViewAdapter;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static com.blinkedge.musciplayer.Activities.MainActivity.temporaryAudioFilesModal;

public class ArtistDetailActivity extends AppCompatActivity {

    private ImageView artistDetailArtistImage;
    private ImageView customActionBackImageArtist;
    private RecyclerView artistDetailRecyclerView;
    private final ArrayList<MusicFilesModal> artist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_detail);

        id();
        onClick();
        getData();

    }

    private void onClick() {
        customActionBackImageArtist.setOnClickListener(v -> {
            onBackPressed();
        });
    }

    @Override
    protected void onResume() {
        if (!(artist.size() < 1)) {
            ArtistDetailRecyclerViewAdapter artistDetailRecyclerViewAdapter =
                    new ArtistDetailRecyclerViewAdapter(this, artist);
            artistDetailRecyclerView.setAdapter(artistDetailRecyclerViewAdapter);
            artistDetailRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
        super.onResume();
    }

    private void getData() {
        String artistName = getIntent().getStringExtra("artistName");

        for (int i = 0; i < temporaryAudioFilesModal.size(); i++) {
            if (artistName.equals(temporaryAudioFilesModal.get(i).getArtist())) {
                Log.d("artistName" , temporaryAudioFilesModal.get(i).getArtist());
                artist.clear();
                artist.add(temporaryAudioFilesModal.get(i));
            }
        }

        byte[] albumImage = getAlbumImage(artist.get(0).getPath());
        if (albumImage != null) {
            Glide.with(this).asBitmap().load(albumImage).into(artistDetailArtistImage);
        } else {
            Glide.with(this).asBitmap().load(R.drawable.ic_artist).into(artistDetailArtistImage);
        }
    }

    private byte[] getAlbumImage(String uri) {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(uri);
        byte[] art = mediaMetadataRetriever.getEmbeddedPicture();
        mediaMetadataRetriever.release();
        return art;
    }

    private void id() {
        artistDetailArtistImage = findViewById(R.id.artistDetailArtistImage);
        customActionBackImageArtist = findViewById(R.id.customActionBackImageArtist);
        artistDetailRecyclerView = findViewById(R.id.artistDetailRecyclerView);
    }
}