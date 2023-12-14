package com.example.spotify;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class searchAdapter extends BaseAdapter {
    private Context context;
    private List<searchmodel> albumList;

    public searchAdapter(Context context, List<searchmodel> albumList) {
        this.context = context;
        this.albumList = albumList;
    }

    @Override
    public int getCount() {
        return albumList.size();
    }

    @Override
    public Object getItem(int position) {
        return albumList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_list_search, null);
        }

        ImageView imageView = convertView.findViewById(R.id.imageView);
        TextView nameTextView = convertView.findViewById(R.id.SongName);
        TextView detailsTextView = convertView.findViewById(R.id.ArtistName);

        searchmodel album = albumList.get(position);

        // Load ảnh sử dụng Picasso
        ImageView imgAblum = (ImageView) convertView.findViewById(R.id.imageView);
        Picasso.with(context).load(album.getAlbumThumbnail()).resize(100,100).into(imgAblum);


        nameTextView.setText(album.getAlbumTitle());
        detailsTextView.setText( album.getArtistName());

        return convertView;
    }
}