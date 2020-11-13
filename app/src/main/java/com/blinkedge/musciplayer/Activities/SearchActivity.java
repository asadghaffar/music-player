package com.blinkedge.musciplayer.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blinkedge.musciplayer.MusicFilesModal.MusicFilesModal;
import com.blinkedge.musciplayer.R;
import com.blinkedge.musciplayer.RecyclerViewAdapter.AllTracksRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Locale;

import static com.blinkedge.musciplayer.Activities.MainActivity.temporaryAudioFilesModal;

public class SearchActivity extends AppCompatActivity {

    private TextView searchAppName;
    private ImageView searchIconView;
    private LinearLayout searchLinearLayout;
    private RecyclerView searchRecyclerView;
    private ImageView searchBackImageView;
    private EditText searchSongEditTextView;
    private AllTracksRecyclerViewAdapter allTracksRecyclerViewAdapter;
    private ArrayList<MusicFilesModal> trackList;
    private  ArrayList<MusicFilesModal> emptyTrackList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        id();
        onClick();
        recyclerView();

    }

    private void onClick() {
        searchIconView.setOnClickListener(v -> {
            searchAppName.setVisibility(View.GONE);
            searchLinearLayout.setVisibility(View.VISIBLE);
        });

        searchBackImageView.setOnClickListener(v -> onBackPressed());

        searchSongEditTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                emptyTrackList = new ArrayList<>();

                for (int i = 0; i < trackList.size(); i++) {
                    if (trackList.get(i).getTitle().trim().toLowerCase(Locale.getDefault()).contains(searchSongEditTextView.
                            getText().toString().trim().toLowerCase())) {
                        emptyTrackList.add(trackList.get(i));


                    }
                }
                setAdapter(emptyTrackList);

            }
        });


    }

    private void recyclerView() {
        if (!(temporaryAudioFilesModal.size() < 1)) {
            trackList = (ArrayList<MusicFilesModal>) MainActivity.temporaryAudioFilesModal;
            setAdapter(trackList);
        }
    }

    private void setAdapter(ArrayList<MusicFilesModal> list) {
        allTracksRecyclerViewAdapter = new AllTracksRecyclerViewAdapter(this, list);
        searchRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchRecyclerView.setAdapter(allTracksRecyclerViewAdapter);
    }

    private void id() {
        searchLinearLayout = findViewById(R.id.searchLinearLayout);
        searchAppName = findViewById(R.id.searchAppName);
        searchIconView = findViewById(R.id.searchIconView);
        searchRecyclerView = findViewById(R.id.searchRecyclerView);
        searchSongEditTextView = findViewById(R.id.searchSongEditTextView);
        searchBackImageView = findViewById(R.id.searchBackImageView);

    }
}