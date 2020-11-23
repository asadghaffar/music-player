package com.blinkedge.musciplayer.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inspector.StaticInspectionCompanionProvider;
import android.widget.ImageView;

import com.blinkedge.musciplayer.Fragments.AlbumsFragment;
import com.blinkedge.musciplayer.MusicFilesModal.MusicFilesModal;
import com.blinkedge.musciplayer.R;
import com.blinkedge.musciplayer.RecyclerViewAdapter.AlbumDetailRecyclerViewAdapter;
import com.blinkedge.musciplayer.RecyclerViewAdapter.AlbumRecyclerViewAdapter;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static com.blinkedge.musciplayer.Activities.MainActivity.temporaryAudioFilesModal;

public class AlbumDetailsActivity extends AppCompatActivity {

    private RecyclerView albumDetailRecyclerView;
    private ImageView albumDetailAlbumImage;
    private ImageView customActionBackImageDetailActivity;
    static ArrayList<MusicFilesModal> albumSongs = new ArrayList<>();
    private String albumName = "";
    private int j = 0;
    byte[] albumImage = new byte[0];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_details);

        id();
        getData();
        onClick();

    }

    private void onClick() {
        customActionBackImageDetailActivity.setOnClickListener(v -> {
            Intent intent = new Intent(AlbumDetailsActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {

        if (!(albumSongs.size() < 1)) {
            AlbumDetailRecyclerViewAdapter albumDetailRecyclerViewAdapter = new
                    AlbumDetailRecyclerViewAdapter(this, albumSongs);
            albumDetailRecyclerView.setAdapter(albumDetailRecyclerViewAdapter);
            albumDetailRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        }

        super.onResume();
    }

    private void getData() {
        albumName = getIntent().getStringExtra("albumName");

        for (int i = 0; i < temporaryAudioFilesModal.size(); i++) {
            if (albumName.equals(temporaryAudioFilesModal.get(i).getAlbum())) {
                Log.d("wsefw__","intent data="+albumName+"listdata="+temporaryAudioFilesModal.get(i).getAlbum());
                albumSongs.clear();
                albumSongs.add( temporaryAudioFilesModal.get(i));
            }
        }

        byte[] albumImage = getAlbumImage(albumSongs.get(0).getPath());
        if (albumImage != null) {
            Glide.with(this).asBitmap().load(albumImage).into(albumDetailAlbumImage);
        } else {
            Glide.with(this).asBitmap().load(R.drawable.ic_album).into(albumDetailAlbumImage);
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
        albumDetailRecyclerView = findViewById(R.id.albumDetailRecyclerView);
        albumDetailAlbumImage = findViewById(R.id.albumDetailAlbumImage);
        customActionBackImageDetailActivity = findViewById(R.id.customActionBackImageDetailActivity);
    }
}