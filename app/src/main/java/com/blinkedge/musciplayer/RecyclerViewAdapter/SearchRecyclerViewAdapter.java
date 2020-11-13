package com.blinkedge.musciplayer.RecyclerViewAdapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blinkedge.musciplayer.MusicFilesModal.MusicFilesModal;
import com.blinkedge.musciplayer.R;

import java.util.List;

public class SearchRecyclerViewAdapter extends RecyclerView.Adapter<searchTrack> {

    private Context context;
    private View view;
    private List<MusicFilesModal> searchMusicFilesModals;

    public SearchRecyclerViewAdapter(Context context, List<MusicFilesModal> searchMusicFilesModals){
        this.context = context;
        this.searchMusicFilesModals = searchMusicFilesModals;
    }

    @NonNull
    @Override
    public searchTrack onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.custom_search_view, parent, false);

        return new searchTrack(view);
    }

    @Override
    public void onBindViewHolder(@NonNull searchTrack holder, int position) {

    }

    @Override
    public int getItemCount() {
        return searchMusicFilesModals.size();
    }
}

class searchTrack extends RecyclerView.ViewHolder{

    ImageView searchMusicItemImage;
    TextView musicItemName;
    TextView musicItemDuration;

    public searchTrack(@NonNull View itemView) {
        super(itemView);

        searchMusicItemImage = itemView.findViewById(R.id.searchMusicItemImage);
        musicItemName = itemView.findViewById(R.id.musicItemName);
        musicItemDuration = itemView.findViewById(R.id.musicItemDuration);

    }
}
