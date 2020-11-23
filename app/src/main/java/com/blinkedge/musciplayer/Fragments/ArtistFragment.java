package com.blinkedge.musciplayer.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blinkedge.musciplayer.MusicFilesModal.MusicFilesModal;
import com.blinkedge.musciplayer.R;
import com.blinkedge.musciplayer.RecyclerViewAdapter.AllTracksRecyclerViewAdapter;
import com.blinkedge.musciplayer.RecyclerViewAdapter.ArtistRecyclerViewAdapter;

import java.util.ArrayList;

import static com.blinkedge.musciplayer.Activities.MainActivity.temporaryAudioFilesModal;

public class ArtistFragment extends Fragment {

    private RecyclerView artistRecyclerView;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_artist, container, false);

        id();
        recyclerView();

        return view;
    }

    private void recyclerView() {
        ArtistRecyclerViewAdapter artistRecyclerViewAdapter = new ArtistRecyclerViewAdapter(getContext(),
                                (ArrayList<MusicFilesModal>) temporaryAudioFilesModal);
        artistRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        artistRecyclerView.setAdapter(artistRecyclerViewAdapter);

    }

    private void id() {
        artistRecyclerView = view.findViewById(R.id.artistRecyclerView);
    }
}