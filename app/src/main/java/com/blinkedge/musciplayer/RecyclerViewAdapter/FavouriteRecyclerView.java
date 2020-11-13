package com.blinkedge.musciplayer.RecyclerViewAdapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blinkedge.musciplayer.MusicFilesModal.MusicFilesModal;
import com.google.gson.Gson;

import java.util.List;

public class FavouriteRecyclerView extends RecyclerView.Adapter<favouriteSongs>{

    private Context context;
    private List<MusicFilesModal> modals;
    private View view;
    private Gson gson;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public FavouriteRecyclerView(Context context, List<MusicFilesModal> modal) {
        this.context = context;
        this.modals = modal;
        gson = new Gson();
        sharedPreferences = context.getSharedPreferences("SONGS", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    @NonNull
    @Override
    public favouriteSongs onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull favouriteSongs holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

class favouriteSongs extends RecyclerView.ViewHolder {
    public favouriteSongs(@NonNull View itemView) {
        super(itemView);
    }
}
