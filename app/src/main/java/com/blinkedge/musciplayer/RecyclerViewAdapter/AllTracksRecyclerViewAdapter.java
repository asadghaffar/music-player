package com.blinkedge.musciplayer.RecyclerViewAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.util.Log;
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

public class AllTracksRecyclerViewAdapter extends RecyclerView.Adapter<AllTracks> {

    private final Context context;
    public static ArrayList<MusicFilesModal> musicFilesModals;
    private View view;

    public AllTracksRecyclerViewAdapter(Context context1, ArrayList<MusicFilesModal> musicFilesModals1) {
        this.context = context1;
        musicFilesModals = musicFilesModals1;
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
        byte[] albumImage = getTrackImage(Uri.parse(musicFilesModals.get(position).getPath()));
        if (albumImage != null) {
            Glide.with(context).asBitmap().load(albumImage).into(holder.trackItemImage);
        } else {
            Glide.with(context).asBitmap().load(R.drawable.ic_album).into(holder.trackItemImage);
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, MusicPlayerActivity.class);
            intent.putExtra("position", position);
            intent.putExtra("track", "allTracksRecycler");
            context.startActivity(intent);

        });

        // Duration
        try {
            String duration = musicFilesModals.get(position).getDuration();
            @SuppressLint("DefaultLocale")
            String time = String.format("%02d : %02d ",
                    TimeUnit.MILLISECONDS.toMinutes(Long.parseLong(duration)),
                    TimeUnit.MILLISECONDS.toSeconds(Long.parseLong(duration)) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(Long.parseLong(duration))));
            holder.trackItemDuration.setText(time);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private byte[] getTrackImage(Uri uri) {
        byte [] art = new byte[0];
        try {
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(String.valueOf(uri));
            art = mediaMetadataRetriever.getEmbeddedPicture();
            mediaMetadataRetriever.release();
        } catch (Exception e) {
            Log.d("error_", String.valueOf(e));
            e.printStackTrace();
        }
        return art;
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(musicFilesModals.get(position).getTitle());
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return musicFilesModals.size();
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
