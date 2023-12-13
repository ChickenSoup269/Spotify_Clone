package com.example.spotify;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.SongViewHolder> {

    private List<Songs> danhSachBaiHat;

    public SongsAdapter(List<Songs> danhSachBaiHat) {
        this.danhSachBaiHat = danhSachBaiHat;
    }
    public  SongsAdapter(){

    }

    public void setDanhSachBaiHat(List<Songs> danhSachBaiHat) {
        if (danhSachBaiHat != null) {
            this.danhSachBaiHat.clear();
            this.danhSachBaiHat.addAll(danhSachBaiHat);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_favorite_song, parent, false);
        return new SongViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return danhSachBaiHat != null ? danhSachBaiHat.size() : 0;
    }

    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, int position) {
        if (danhSachBaiHat == null || danhSachBaiHat.isEmpty() || position >= danhSachBaiHat.size()) {
            return;
        }
        Songs baiHat = danhSachBaiHat.get(position);
        if (baiHat != null) {
            holder.bind(baiHat);
        }
    }
    static class SongViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name_song, tv_author;
        private ImageView image_thumbnail;

        public SongViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name_song = itemView.findViewById(R.id.tv_name_song);
            tv_author = itemView.findViewById(R.id.tv_author);
            image_thumbnail = itemView.findViewById(R.id.image_thumbnail);
        }

        public void bind(Songs baiHat) {
            String songTitle = baiHat.getTitle();
            String limitedTitle = songTitle.length() > 20 ? songTitle.substring(0, 20) + "..." : songTitle;
            tv_name_song.setText(limitedTitle);
            tv_author.setText(baiHat.getArtist());
            Glide.with(itemView.getContext())
                    .load(baiHat.getThumbnail())
                    .into(image_thumbnail);
        }
    }
}
