package com.blinkedge.musciplayer.RecyclerViewAdapter;

import android.annotation.SuppressLint;
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

import com.blinkedge.musciplayer.Activities.MusicPlayerActivity;
import com.blinkedge.musciplayer.MusicFilesModal.MusicFilesModal;
import com.blinkedge.musciplayer.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

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
        String imageUrl = musicFilesModals.get(position).getPath();

        Log.d("imgaeUri_", imageUrl);

        MediaMetadataRetriever metaRetriever = new MediaMetadataRetriever();
        metaRetriever.setDataSource(imageUrl);
        try {
            byte[] art = new byte[0];
            Bitmap songImage = BitmapFactory.decodeByteArray(art, 0, art.length);
            Log.d("image", String.valueOf(songImage));
            holder.trackItemImage.setImageBitmap(songImage);
        } catch (Exception ignored) {
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, MusicPlayerActivity.class);
            intent.putExtra("positiion", position);
            context.startActivity(intent);

        });

        String duration = musicFilesModals.get(position).getDuration();
        @SuppressLint("DefaultLocale")
        String time = String.format("%02d : %02d ",
                TimeUnit.MILLISECONDS.toMinutes(Long.parseLong(duration)),
                TimeUnit.MILLISECONDS.toSeconds(Long.parseLong(duration)) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(Long.parseLong(duration)))
        );

        holder.trackItemDuration.setText(time);

        /*byte[] image = musicFilesModals.get(position).();
        if (image != null) {
            Glide.with(context).asBitmap()
                    .load(image)
                    .centerCrop()
                    .into(holder.trackItemImage);
        } else {
            Glide.with(context)
                    .load(R.drawable.ic_headset)
                    .into(holder.trackItemImage);
        }*/

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
    ;

    public AllTracks(@NonNull View itemView) {
        super(itemView);

        trackItemImage = itemView.findViewById(R.id.musicItemImage);
        trackItemName = itemView.findViewById(R.id.musicItemName);
        trackItemDuration = itemView.findViewById(R.id.musicItemDuration);

    }

}
