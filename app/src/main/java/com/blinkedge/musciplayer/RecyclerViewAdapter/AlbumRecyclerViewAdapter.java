package com.blinkedge.musciplayer.RecyclerViewAdapter;

import android.content.Context;
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

        String albumImageUrl = albumTracksModal.get(position).getPath();

        Log.d("imgaeUri_", albumImageUrl);

        MediaMetadataRetriever metaRetriever = new MediaMetadataRetriever();
        metaRetriever.setDataSource(albumImageUrl);
        try {
            byte[] art = new byte[0];
            Bitmap songImage = BitmapFactory.decodeByteArray(art, 0, art.length);
            Log.d("image", String.valueOf(songImage));
            holder.musicItemImage.setImageBitmap(songImage);
        } catch (Exception ignored) {
        }


    }

    @Override
    public int getItemCount() {
        return albumTracksModal.size();
    }
}

class Album extends RecyclerView.ViewHolder {

    TextView albumName;
    ImageView musicItemImage;

    public Album(@NonNull View itemView) {
        super(itemView);

        musicItemImage = itemView.findViewById(R.id.musicItemImage);
        albumName = itemView.findViewById(R.id.albumName);

    }
}