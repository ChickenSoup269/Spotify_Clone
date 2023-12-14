package com.example.spotify;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class ParentRecyclerViewAdapter extends RecyclerView.Adapter<ParentRecyclerViewAdapter.MyViewHolder> {
    private ArrayList<ParentModel> parentModelArrayList;
    private Context cxt;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView category;
        public RecyclerView childRecyclerView;

        public MyViewHolder(View itemView) {
            super(itemView);

            category = itemView.findViewById(R.id.Movie_category);
            childRecyclerView = itemView.findViewById(R.id.Child_RV);
            // Set LayoutManager for childRecyclerView
            childRecyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext(), RecyclerView.HORIZONTAL, false));
        }
    }

    public ParentRecyclerViewAdapter(ArrayList<ParentModel> exampleList, Context context) {
        this.parentModelArrayList = exampleList;
        this.cxt = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parent_recyclerview_items, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return parentModelArrayList.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ParentModel currentItem = parentModelArrayList.get(position);
        holder.category.setText(currentItem.getAlbumTitle());

        // Lấy danh sách các bài hát từ ParentModel
        ArrayList<Songs> songs = currentItem.getSongs();

        // Chuyển đổi danh sách SongDetail sang danh sách ChildModel
        ArrayList<ChildModel> childModels = new ArrayList<>();
        for (Songs song : songs) {
            ChildModel childModel = new ChildModel(song.getThumbnail(), song.getTitle(), song.getArtist(), song.getAudio());
            childModels.add(childModel);
        }

        // Khởi tạo ChildRecyclerViewAdapter với danh sách các ChildModel
        ChildRecyclerViewAdapter childRecyclerViewAdapter = new ChildRecyclerViewAdapter(childModels, cxt);
        holder.childRecyclerView.setAdapter(childRecyclerViewAdapter);
        // Notify adapter after updating data
        childRecyclerViewAdapter.notifyDataSetChanged();
    }
}