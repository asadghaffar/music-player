package com.blinkedge.musciplayer.RecyclerViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blinkedge.musciplayer.MusicFilesModal.MusicFilesModal;
import com.blinkedge.musciplayer.R;

import java.util.ArrayList;
import java.util.List;

public class AlbumRecyclerViewAdapter extends RecyclerView.Adapter<Album> {

    private Context context;
    private View view;
    private ArrayList<MusicFilesModal> albumTracksModal;

    public AlbumRecyclerViewAdapter(Context context1, ArrayList<MusicFilesModal> albumTracksModal1) {
        this.context = context1;
        this.albumTracksModal = albumTracksModal1;
    }

    @NonNull
    @Override
    public Album onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        view = LayoutInflater.from(context).inflate(R.layout.custom_album_item_view, parent, false);

        return new Album(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Album holder, int position) {

        holder.albumName.setText(albumTracksModal.get(position).getAlbum());

    }

    @Override
    public int getItemCount() {
        return albumTracksModal.size();
    }
}

class Album extends RecyclerView.ViewHolder {

    TextView albumName;

    public Album(@NonNull View itemView) {
        super(itemView);

        albumName = itemView.findViewById(R.id.albumName);

    }
}