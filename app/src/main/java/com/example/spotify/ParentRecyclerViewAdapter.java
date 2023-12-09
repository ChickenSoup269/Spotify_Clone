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
    public Context cxt;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView category;
        public RecyclerView childRecyclerView;

        public MyViewHolder(View itemView) {
            super(itemView);

            category = itemView.findViewById(R.id.Movie_category);
            childRecyclerView = itemView.findViewById(R.id.Child_RV);
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
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(cxt, LinearLayoutManager.HORIZONTAL, false);
        holder.childRecyclerView.setLayoutManager(layoutManager);
        holder.childRecyclerView.setHasFixedSize(true);

        holder.category.setText(currentItem.movieCategory());
        ArrayList<ChildModel> arrayList = new ArrayList<>();

        // added the first child row
        if (parentModelArrayList.get(position).movieCategory().equals("Album Mùa Xuân")) {
            arrayList.add(new ChildModel(R.drawable.twitter,"Movie Name"));
            arrayList.add(new ChildModel(R.drawable.twitter,"Movie Name"));
            arrayList.add(new ChildModel( R.drawable.twitter,"Movie Name"));
            arrayList.add(new ChildModel( R.drawable.twitter,"Movie Name"));
            arrayList.add(new ChildModel( R.drawable.twitter,"Movie Name"));
            arrayList.add(new ChildModel( R.drawable.twitter,"Movie Name"));
        }

        // added in second child row
        if (parentModelArrayList.get(position).movieCategory().equals("Album Mùa Hè")) {
            arrayList.add(new ChildModel(R.drawable.facebook,"Movie Name"));
            arrayList.add(new ChildModel(R.drawable.facebook,"Movie Name"));
            arrayList.add(new ChildModel( R.drawable.facebook,"Movie Name"));
            arrayList.add(new ChildModel( R.drawable.facebook,"Movie Name"));
            arrayList.add(new ChildModel( R.drawable.facebook,"Movie Name"));
            arrayList.add(new ChildModel( R.drawable.facebook,"Movie Name"));
        }

        // added in third child row
        if (parentModelArrayList.get(position).movieCategory().equals("Album Tết")) {
            arrayList.add(new ChildModel(R.drawable.test,"Movie Name"));
            arrayList.add(new ChildModel(R.drawable.test,"Movie Name"));
            arrayList.add(new ChildModel( R.drawable.test,"Movie Name"));
            arrayList.add(new ChildModel( R.drawable.test,"Movie Name"));
            arrayList.add(new ChildModel( R.drawable.test,"Movie Name"));
            arrayList.add(new ChildModel( R.drawable.test,"Movie Name"));
        }

        // added in fourth child row
        if (parentModelArrayList.get(position).movieCategory().equals("Album Gái Anime")) {
            arrayList.add(new ChildModel(R.drawable.test,"Movie Name"));
            arrayList.add(new ChildModel(R.drawable.test,"Movie Name"));
            arrayList.add(new ChildModel( R.drawable.test,"Movie Name"));
            arrayList.add(new ChildModel( R.drawable.test,"Movie Name"));
            arrayList.add(new ChildModel( R.drawable.test,"Movie Name"));
            arrayList.add(new ChildModel( R.drawable.test,"Movie Name"));
        }

        // added in fourth child row
        if (parentModelArrayList.get(position).movieCategory().equals("Album Gái Anime")) {
            arrayList.add(new ChildModel(R.drawable.test,"Movie Name"));
            arrayList.add(new ChildModel(R.drawable.test,"Movie Name"));
            arrayList.add(new ChildModel( R.drawable.test,"Movie Name"));
            arrayList.add(new ChildModel( R.drawable.test,"Movie Name"));
            arrayList.add(new ChildModel( R.drawable.test,"Movie Name"));
            arrayList.add(new ChildModel( R.drawable.test,"Movie Name"));
        }

        ChildRecyclerViewAdapter childRecyclerViewAdapter = new ChildRecyclerViewAdapter(arrayList,holder.childRecyclerView.getContext());
        holder.childRecyclerView.setAdapter(childRecyclerViewAdapter);
    }
}
