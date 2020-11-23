package com.blinkedge.musciplayer.RecyclerViewAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blinkedge.musciplayer.Activities.MusicPlayerActivity;
import com.blinkedge.musciplayer.MusicFilesModal.MusicFilesModal;
import com.blinkedge.musciplayer.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class ArtistDetailRecyclerViewAdapter extends RecyclerView.Adapter<ArtistDetail> {

    private Context context;
    private View view;
    public static ArrayList<MusicFilesModal> detailArtistTracksModal;

    public ArtistDetailRecyclerViewAdapter(Context context1, ArrayList<MusicFilesModal> detailAlbumTracksModal1) {
        this.context = context1;
        detailArtistTracksModal = detailAlbumTracksModal1;
    }

    @NonNull
    @Override
    public ArtistDetail onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        view = LayoutInflater.from(context).inflate(R.layout.custom_track_item_view, parent, false);

        return new ArtistDetail(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistDetail holder, int position) {
        holder.albumDetailMusicName.setText(detailArtistTracksModal.get(position).getTitle());
        holder.albumDetailMusicDuration.setText(detailArtistTracksModal.get(position).getDuration());
        byte[] trackImage = getAlbumImage(detailArtistTracksModal.get(position).getPath());
        if (trackImage != null) {
            Glide.with(context).asBitmap().load(trackImage).into(holder.albumDetailMusicImage);
        } else {
            Glide.with(context).asBitmap().load(R.drawable.ic_artist).into(holder.albumDetailMusicImage);
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, MusicPlayerActivity.class);
            intent.putExtra("detailArtistSyArhaH", "artistDetails");
            intent.putExtra("position", position);
            context.startActivity(intent);

        });

        // Duration
        try {
            String duration = detailArtistTracksModal.get(position).getDuration();
            @SuppressLint("DefaultLocale")
            String time = String.format("%02d : %02d ",
                    TimeUnit.MILLISECONDS.toMinutes(Long.parseLong(duration)),
                    TimeUnit.MILLISECONDS.toSeconds(Long.parseLong(duration)) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(Long.parseLong(duration))));
            holder.albumDetailMusicDuration.setText(time);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return detailArtistTracksModal.size();
    }

    private byte[] getAlbumImage(String uri) {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(uri);
        byte[] art = mediaMetadataRetriever.getEmbeddedPicture();
        mediaMetadataRetriever.release();
        return art;
    }

}

class ArtistDetail extends RecyclerView.ViewHolder {

    TextView albumDetailMusicName;
    TextView albumDetailMusicDuration;
    ImageView albumDetailMusicImage;

    public ArtistDetail(@NonNull View itemView) {
        super(itemView);

        albumDetailMusicName = itemView.findViewById(R.id.musicItemName);
        albumDetailMusicImage = itemView.findViewById(R.id.musicItemImage);
        albumDetailMusicDuration = itemView.findViewById(R.id.musicItemDuration);

    }

}