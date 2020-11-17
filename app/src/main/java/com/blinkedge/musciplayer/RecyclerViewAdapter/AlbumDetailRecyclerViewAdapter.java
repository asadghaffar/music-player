package com.blinkedge.musciplayer.RecyclerViewAdapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blinkedge.musciplayer.Activities.AlbumDetailsActivity;
import com.blinkedge.musciplayer.Activities.MusicPlayerActivity;
import com.blinkedge.musciplayer.MusicFilesModal.MusicFilesModal;
import com.blinkedge.musciplayer.R;
import com.bumptech.glide.Glide;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class AlbumDetailRecyclerViewAdapter extends RecyclerView.Adapter<AlbumDetail> {

    private Context context;
    private View view;
    public static ArrayList<MusicFilesModal> albumTracksModal;

    public AlbumDetailRecyclerViewAdapter(Context context1, ArrayList<MusicFilesModal> albumTracksModal1) {
        this.context = context1;
        albumTracksModal = albumTracksModal1;
    }

    @NonNull
    @Override
    public AlbumDetail onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        view = LayoutInflater.from(context).inflate(R.layout.custom_track_item_view, parent, false);

        return new AlbumDetail(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumDetail holder, int position) {
        holder.albumDetailMusicName.setText(albumTracksModal.get(position).getAlbum());
        holder.albumDetailMusicName.setText(albumTracksModal.get(position).getAlbum());
        byte[] trackImage = getAlbumImage(albumTracksModal.get(position).getPath());
        if (trackImage != null) {
            Glide.with(context).asBitmap().load(trackImage).into(holder.albumDetailMusicImage);
        } else {
            Glide.with(context).asBitmap().load(R.drawable.ic_album).into(holder.albumDetailMusicImage);
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, MusicPlayerActivity.class);
            intent.putExtra("songName", "albumDetails");
            intent.putExtra("position", position);
            context.startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        return albumTracksModal.size();
    }

    private byte[] getAlbumImage(String uri) {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(uri);
        byte[] art = mediaMetadataRetriever.getEmbeddedPicture();
        mediaMetadataRetriever.release();
        return art;
    }

}

class AlbumDetail extends RecyclerView.ViewHolder {

    TextView albumDetailMusicName;
    TextView albumDetailMusicDuration;
    ImageView albumDetailMusicImage;

    public AlbumDetail(@NonNull View itemView) {
        super(itemView);

        albumDetailMusicName = itemView.findViewById(R.id.musicItemName);
        albumDetailMusicImage = itemView.findViewById(R.id.musicItemImage);
        albumDetailMusicDuration = itemView.findViewById(R.id.musicItemDuration);

    }

}