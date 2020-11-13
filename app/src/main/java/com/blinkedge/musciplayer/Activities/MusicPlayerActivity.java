package com.blinkedge.musciplayer.Activities;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.blinkedge.musciplayer.Fragments.TracksFragment;
import com.blinkedge.musciplayer.MusicFilesModal.MusicFilesModal;
import com.blinkedge.musciplayer.R;
import com.blinkedge.musciplayer.RecyclerViewAdapter.AllTracksRecyclerViewAdapter;
import com.example.jean.jcplayer.model.JcAudio;
import com.example.jean.jcplayer.view.JcPlayerView;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.simplemobiletools.commons.views.MySeekBar;
import com.simplemobiletools.commons.views.MyTextView;

import java.util.ArrayList;
import java.util.List;

import static com.blinkedge.musciplayer.Activities.MainActivity.temporaryAudioFilesModal;

public class MusicPlayerActivity extends AppCompatActivity {

    private MaterialToolbar materialToolbar;

    // Shared Preference
    private Gson gson;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;



    private ImageView unFavoriteImageView;
    private ImageView favoriteImageView;

    // JC Player
    private JcPlayerView jcPlayerView;
    List<JcAudio> audioList;

    private int uriSong;
    static List<MusicFilesModal> musicPlayerListSongs;

    private int positionFavoriteSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        musicPlayerListSongs = new ArrayList<>();

        gson = new Gson();
        sharedPreferences = this.getSharedPreferences("SONGS", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        id();
        musicPLayerJcAudioPlayer();
        onCLick();

    }

    private void onCLick() {
        materialToolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });

        unFavoriteImageView.setOnClickListener(v -> {
            unFavoriteImageView.setVisibility(View.INVISIBLE);
            favoriteImageView.setVisibility(View.VISIBLE);

            save();


        });

        favoriteImageView.setOnClickListener(v -> {
            favoriteImageView.setVisibility(View.INVISIBLE);
            unFavoriteImageView.setVisibility(View.VISIBLE);


        });

    }


    private void save() {
        String save = gson.toJson(positionFavoriteSong);
        editor.putString("fav", save).apply();
        Toast.makeText(this, "item favourite successfully!", Toast.LENGTH_SHORT).show();
    }

    private void musicPLayerJcAudioPlayer() {

        uriSong = getIntent().getIntExtra("position", 999999999);

        musicPlayerListSongs = AllTracksRecyclerViewAdapter.musicFilesModals;

        jcPlayerView = findViewById(R.id.jcplayer);

        audioList = new ArrayList<>();
        for (int i = 0; i < musicPlayerListSongs.size(); i++) {
            audioList.add(JcAudio.createFromFilePath(musicPlayerListSongs.get(i).getTitle(),
                    String.valueOf(Uri.parse(musicPlayerListSongs.get(i).getPath()))));

            positionFavoriteSong = i;

        }
        jcPlayerView.initPlaylist(audioList, null);
        jcPlayerView.playAudio(audioList.get(uriSong));


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        jcPlayerView.kill();
    }

    @Override
    protected void onPause() {
        super.onPause();
        jcPlayerView.createNotification();
        jcPlayerView.createNotification(R.drawable.ic_headset);
    }

    private void id() {
        materialToolbar = findViewById(R.id.musicPlayerToolbar);
        favoriteImageView = findViewById(R.id.favoriteImageView);
        unFavoriteImageView = findViewById(R.id.unFavoriteImageView);

    }
}