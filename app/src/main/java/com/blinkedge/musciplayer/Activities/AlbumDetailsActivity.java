package com.blinkedge.musciplayer.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.widget.ImageView;

import com.blinkedge.musciplayer.MusicFilesModal.MusicFilesModal;
import com.blinkedge.musciplayer.R;
import com.blinkedge.musciplayer.RecyclerViewAdapter.AlbumDetailRecyclerViewAdapter;
import com.blinkedge.musciplayer.RecyclerViewAdapter.AlbumRecyclerViewAdapter;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AlbumDetailsActivity extends AppCompatActivity {

    private RecyclerView albumDetailRecyclerView;
    private ImageView albumDetailAlbumImage;
    private ArrayList<MusicFilesModal> albumSongs = new ArrayList<>();
    private String albumName = "";
    private int j = 0;
    byte[] albumImage = new byte[0];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_details);

        id();
        getData();

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
        albumName = getIntent().getExtras().getString("albumName", "");
        String albumPath = getIntent().getExtras().getString("albumPosition", "");

        albumImage = getAlbumImage(AlbumRecyclerViewAdapter.albumTracksModal.get(Integer.parseInt(albumPath)).getPath());
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
    }
}