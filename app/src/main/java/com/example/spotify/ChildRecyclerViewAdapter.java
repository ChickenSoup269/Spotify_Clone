package com.example.spotify;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

public class ChildRecyclerViewAdapter extends RecyclerView.Adapter<ChildRecyclerViewAdapter.MyViewHolder> {
    public ArrayList<ChildModel> childModelArrayList;
    Context cxt;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView heroImage;
        public TextView songName;

        public MyViewHolder(View itemView) {
            super(itemView);
            heroImage = itemView.findViewById(R.id.hero_image);
            songName = itemView.findViewById(R.id.song_name);
        }
    }

    public ChildRecyclerViewAdapter(ArrayList<ChildModel> arrayList, Context mContext) {
        this.cxt = mContext;
        this.childModelArrayList = arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_recyclerview_items, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ChildModel currentItem = childModelArrayList.get(position);
        // Hiển thị tên bài hát
        String songName = currentItem.getSongName();
        if (songName.length() > 9) {
            songName = songName.substring(0, 9) + "...";
        }

        holder.songName.setText(songName);

        // Load hình ảnh sử dụng Glide vào ImageView
        Glide.with(cxt)
                .load(currentItem.getHeroImage())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        Log.e("GlideError", "Error loading image: " + e.getMessage());
                        // Hiển thị thông báo lỗi bằng Toast
                        Toast.makeText(cxt, "Error loading image", Toast.LENGTH_SHORT).show();
                        return false; // Trả về false để Glide xử lý tiếp sau khi ghi log lỗi
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(holder.heroImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lấy dữ liệu từ mục được nhấp vào
                ChildModel currentItem = childModelArrayList.get(position);
                String songImage = currentItem.getHeroImage();
                String songName = currentItem.getSongName();
                String artistName = currentItem.getSongArtist();
                String songAudioUrl = currentItem.getSongAudio();

                // Tạo FragmentListenMusic mới và truyền dữ liệu qua Bundle
                FragmentListenMusic fragment = new FragmentListenMusic();
                Bundle bundle = new Bundle();
                bundle.putString("thumbnail", songImage);
                bundle.putString("title", songName);
                bundle.putString("artist", artistName);
                bundle.putString("audio", songAudioUrl);
                fragment.setArguments(bundle);

                // Mở FragmentListenMusic bằng FragmentManager
                FragmentManager fragmentManager = ((FragmentActivity) view.getContext()).getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragmentContainer, fragment);
                transaction.addToBackStack(null);  // Để có thể quay lại Fragment trước đó nếu cần
                transaction.commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return childModelArrayList.size();
    }
}