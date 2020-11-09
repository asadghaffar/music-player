package com.blinkedge.musciplayer.RecyclerViewAdapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AlbumRecyclerViewAdapter extends RecyclerView.Adapter<Album> {

    @NonNull
    @Override
    public Album onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull Album holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
class Album extends RecyclerView.ViewHolder {
    public Album(@NonNull View itemView) {
        super(itemView);
    }
}