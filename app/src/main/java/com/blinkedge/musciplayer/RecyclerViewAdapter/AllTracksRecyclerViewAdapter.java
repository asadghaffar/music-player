package com.blinkedge.musciplayer.RecyclerViewAdapter;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blinkedge.musciplayer.MusicFiles.MusicFilesModal;
import com.blinkedge.musciplayer.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;

public class AllTracksRecyclerViewAdapter extends RecyclerView.Adapter<AllTracks> {

    private Context context;
    private ArrayList<MusicFilesModal> musicFilesModals;
    private View view;

    public AllTracksRecyclerViewAdapter(Context context1, ArrayList<MusicFilesModal> musicFilesModals1) {
        this.context = context1;
        this.musicFilesModals = musicFilesModals1;
    }

    @NonNull
    @Override
    public AllTracks onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        view = LayoutInflater.from(context).inflate(R.layout.custom_track_item_view, parent, false);

        return new AllTracks(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllTracks holder, int position) {
        holder.trackItemName.setText(musicFilesModals.get(position).getTitle());
        holder.trackItemDuration.setText(musicFilesModals.get(position).getDuration());

        byte[] image = getAlbumImage(musicFilesModals.get(position).getPath());
        if (image != null) {
            Glide.with(context).asBitmap()
                    .load(image)
                    .centerCrop()
                    .into(holder.trackItemImage);
        } else {
            Glide.with(context)
                    .load(R.drawable.ic_headset)
                    .into(holder.trackItemImage);
        }

    }

    @Override
    public int getItemCount() {
        return musicFilesModals.size();
    }

    public byte[] getAlbumImage(String uri) {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(uri, new HashMap<>());
        byte[] albumImageData = mediaMetadataRetriever.getEmbeddedPicture();
        mediaMetadataRetriever.release();
        return albumImageData;
    }

}

class AllTracks extends RecyclerView.ViewHolder {

    ImageView trackItemImage;
    TextView trackItemName;
    TextView trackItemDuration;

    public AllTracks(@NonNull View itemView) {
        super(itemView);

        trackItemImage = itemView.findViewById(R.id.musicItemImage);
        trackItemName = itemView.findViewById(R.id.musicItemName);
        trackItemDuration = itemView.findViewById(R.id.musicItemDuration);

    }

}
