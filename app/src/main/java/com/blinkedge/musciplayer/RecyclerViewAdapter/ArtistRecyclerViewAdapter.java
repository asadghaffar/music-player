package com.blinkedge.musciplayer.RecyclerViewAdapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blinkedge.musciplayer.MusicFilesModal.MusicFilesModal;
import com.blinkedge.musciplayer.R;

import java.util.ArrayList;

public class ArtistRecyclerViewAdapter extends RecyclerView.Adapter<artist> {

    private View view;
    private Context context;
    private ArrayList<MusicFilesModal> artistNameModal;

    public ArtistRecyclerViewAdapter(Context context1, ArrayList<MusicFilesModal> musicFilesModals1) {
        this.context = context1;
        this.artistNameModal = musicFilesModals1;
    }

    @NonNull
    @Override
    public artist onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_artist_item_view, parent, false);

        return new artist(view);
    }

    @Override
    public void onBindViewHolder(@NonNull artist holder, int position) {
        holder.artistName.setText(artistNameModal.get(position).getArtist());

    }

    @Override
    public int getItemCount() {
        Log.d("artistListSize", String.valueOf(artistNameModal.size()));
        return artistNameModal.size();

    }
}

class artist extends RecyclerView.ViewHolder {

    TextView artistName;
    TextView totalArtistAlbum;
    TextView totalArtistTrack;

    public artist(@NonNull View itemView) {
        super(itemView);

        artistName = itemView.findViewById(R.id.artistName);

    }
}
