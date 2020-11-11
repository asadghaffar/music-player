package com.blinkedge.musciplayer.Activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.blinkedge.musciplayer.Fragments.TracksFragment;
import com.blinkedge.musciplayer.MusicFilesModal.MusicFilesModal;
import com.blinkedge.musciplayer.R;
import com.example.jean.jcplayer.model.JcAudio;
import com.example.jean.jcplayer.view.JcPlayerView;
import com.google.android.material.appbar.MaterialToolbar;
import com.simplemobiletools.commons.views.MySeekBar;
import com.simplemobiletools.commons.views.MyTextView;

import java.util.ArrayList;
import java.util.List;

import static com.blinkedge.musciplayer.Activities.MainActivity.temporaryAudioFilesModal;

public class MusicPlayerActivity extends AppCompatActivity {

    private MaterialToolbar materialToolbar;

    // JC Player
    private JcPlayerView jcPlayerView;
    List<JcAudio> audioList;

    private int uriSong;
    static List<MusicFilesModal> musicPlayerListSongs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        musicPlayerListSongs = new ArrayList<>();
        getSupportActionBar().hide();


        id();
        musicPLayerJcAudioPlayer();
        onCLick();

    }

    private void onCLick() {
        materialToolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });
    }

    private void musicPLayerJcAudioPlayer() {
        uriSong = getIntent().getIntExtra("positiion", 999999999);
        musicPlayerListSongs = temporaryAudioFilesModal;

        jcPlayerView = findViewById(R.id.jcplayer);

        audioList = new ArrayList<>();
        for (int i = 0; i < temporaryAudioFilesModal.size(); i++)
            audioList.add(JcAudio.createFromFilePath(temporaryAudioFilesModal.get(i).getTitle(), String.valueOf(Uri.parse(temporaryAudioFilesModal.get(i).getPath()))));
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

    }
}