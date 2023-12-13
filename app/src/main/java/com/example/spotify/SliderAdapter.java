package com.example.spotify;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder> {

    private List<SliderItem> sliderItems = new ArrayList<>();
    private ViewPagerItemClick itemClick;
    private ViewPager2 viewPager2;
    private OnItemClickListener itemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public SliderAdapter(ViewPager2 viewPager2) {
        this.viewPager2 = viewPager2;
    }

    public interface ViewPagerItemClick {
        void onItemClicked(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }

    public SliderAdapter(ViewPagerItemClick clickListener) {
        this.itemClick = clickListener;
    }

    public void setSliderItems(List<SliderItem> items) {
        this.sliderItems = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_item_container, parent, false);
        return new SliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        if (sliderItems.size() == 0) {
            return;
        }

        SliderItem sliderItem = sliderItems.get(position % sliderItems.size());
        if (sliderItem.getImageResId() != 0) {
            holder.bindLocalImage(sliderItem.getImageResId());
        } else if (sliderItem.getImageUrl() != null) {
            holder.bindRemoteImage(sliderItem.getImageUrl());
        }
    }

    @Override
    public int getItemCount() {
            return Integer.MAX_VALUE;
    }

    class SliderViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;

        SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageSlide);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && itemClickListener != null) {
                        itemClickListener.onItemClick(position);
                    }
                }
            });
        }

        void bindLocalImage(int imageResId) {
            imageView.setImageResource(imageResId);
        }

        void bindRemoteImage(String imageUrl) {
            RequestOptions requestOptions = new RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC) // Sử dụng cache tự động
                    .override(400, Target.SIZE_ORIGINAL) // Kích thước chiều rộng 400, chiều cao tính tự động
                    .centerCrop(); // Cắt ảnh để vừa với kích thước hiển thịs

            Glide.with(itemView.getContext())
                    .load(imageUrl)
                    .apply(requestOptions)
                    .into(imageView);
        }
    }
}