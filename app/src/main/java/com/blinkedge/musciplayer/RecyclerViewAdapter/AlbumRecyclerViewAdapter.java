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
import com.blinkedge.musciplayer.MusicFilesModal.MusicFilesModal;
import com.blinkedge.musciplayer.R;
import com.bumptech.glide.Glide;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class AlbumRecyclerViewAdapter extends RecyclerView.Adapter<Album> {

    private Context context;
    private View view;
    private final ArrayList<MusicFilesModal> albumTracksModal;

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
        byte[] trackImage = getAlbumImage(albumTracksModal.get(position).getPath());
        if (trackImage != null){
            Glide.with(context).asBitmap().load(trackImage).into(holder.albumImage);
        }else {
            Glide.with(context).asBitmap().load(R.drawable.ic_album).into(holder.albumImage);
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, AlbumDetailsActivity.class);
            intent.putExtra("albumName", albumTracksModal.get(position).getAlbum());
            intent.putExtra("albumPosition", albumTracksModal.get(position).getPath());
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

class Album extends RecyclerView.ViewHolder {

    TextView albumName;
    ImageView albumImage;

    public Album(@NonNull View itemView) {
        super(itemView);

        albumImage = itemView.findViewById(R.id.albumImage);
        albumName = itemView.findViewById(R.id.albumName);

    }

}