package com.blinkedge.musciplayer.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.blinkedge.musciplayer.MusicFilesModal.MusicFilesModal;
import com.blinkedge.musciplayer.R;
import com.blinkedge.musciplayer.RecyclerViewAdapter.AlbumRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.blinkedge.musciplayer.Activities.MainActivity.temporaryAudioFilesModal;

public class AlbumsFragment extends Fragment {

    private View view;
    private RecyclerView albumRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_albums, container, false);

        id();
        recyclerView();

        return view;

    }

    private void id() {
        albumRecyclerView = view.findViewById(R.id.albumRecyclerView);
    }

    private void recyclerView() {
        AlbumRecyclerViewAdapter albumRecyclerViewAdapter = new AlbumRecyclerViewAdapter(getContext(),
                                                    (ArrayList<MusicFilesModal>) temporaryAudioFilesModal);
        albumRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        albumRecyclerView.setAdapter(albumRecyclerViewAdapter);
    }
}