package com.blinkedge.musciplayer.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blinkedge.musciplayer.MusicFilesModal.MusicFilesModal;
import com.blinkedge.musciplayer.R;
import com.blinkedge.musciplayer.RecyclerViewAdapter.AllTracksRecyclerViewAdapter;

import java.io.File;
import java.util.ArrayList;

import static com.blinkedge.musciplayer.Activities.MainActivity.temporaryAudioFilesModal;


public class TracksFragment extends Fragment {

    private RecyclerView trackFragmentRecyclerView;
    private AllTracksRecyclerViewAdapter allTracksRecyclerViewAdapter;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tracks, container, false);

        id();
        recyclerView();

        return view;
    }

    private void recyclerView() {
        if (!(temporaryAudioFilesModal.size() < 1)) {
            allTracksRecyclerViewAdapter = new AllTracksRecyclerViewAdapter(getContext(), (ArrayList<MusicFilesModal>) temporaryAudioFilesModal);
            trackFragmentRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            trackFragmentRecyclerView.setAdapter(allTracksRecyclerViewAdapter);
        }
    }

    private void id() {
        trackFragmentRecyclerView = view.findViewById(R.id.trackFragmentRecyclerView);
    }



}