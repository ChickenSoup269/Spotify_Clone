package com.example.spotify;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LoveListAdapter extends RecyclerView.Adapter<LoveListAdapter.LoveListViewHolder> {

    private ArrayList<LoveList> favoriteSongsList;
    private Context context;

    public LoveListAdapter(ArrayList<LoveList> favoriteSongsList, Context context) {
        this.favoriteSongsList = favoriteSongsList;
        this.context = context;
    }

    @NonNull
    @Override
    public LoveListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_favorite_song, parent, false);
        return new LoveListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoveListViewHolder holder, int position) {
        LoveList song = favoriteSongsList.get(position);

        String songName = song.getSongName();
        holder.songName.setText(songName);

//        holder.songImage.setImageResource(song.getHeroImage());
    }

    @Override
    public int getItemCount() {
        return favoriteSongsList.size();
    }

    public static class LoveListViewHolder extends RecyclerView.ViewHolder {
        TextView songName;
        ImageView songImage;

        public LoveListViewHolder(View itemView) {
            super(itemView);
            songName = itemView.findViewById(R.id.tv_name_song);
            songImage = itemView.findViewById(R.id.image_thumbnail);
        }
    }
}