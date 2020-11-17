package com.blinkedge.musciplayer.RecyclerViewAdapter;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blinkedge.musciplayer.MusicFilesModal.MusicFilesModal;
import com.blinkedge.musciplayer.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ArtistRecyclerViewAdapter extends RecyclerView.Adapter<artist> {

    private View view;
    private final Context context;
    private final ArrayList<MusicFilesModal> artistNameModal;

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
        byte[] trackImage = getArtistImage(artistNameModal.get(position).getPath());
        if (trackImage != null) {
            Glide.with(context).asBitmap().load(trackImage).into(holder.artistImage);
        } else {
            Glide.with(context).asBitmap().load(R.drawable.ic_artist).into(holder.artistImage);
        }

    }

    private byte[] getArtistImage(String uri) {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(uri);
        byte[] art = mediaMetadataRetriever.getEmbeddedPicture();
        mediaMetadataRetriever.release();
        return art;
    }

    @Override
    public int getItemCount() {
        Log.d("artistListSize", String.valueOf(artistNameModal.size()));
        return artistNameModal.size();

    }
}

class artist extends RecyclerView.ViewHolder {

    TextView artistName;
    ImageView artistImage;

    public artist(@NonNull View itemView) {
        super(itemView);

        artistName = itemView.findViewById(R.id.artistName);
        artistImage = itemView.findViewById(R.id.artistImage);

    }
}
