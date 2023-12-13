package com.example.spotify;

public class SliderItem {
    private int imageResId;
    private String imageUrl;
    private String title;
    private String description;

    public SliderItem() {

    }

    // Constructor for local resource image
    public SliderItem(int imageResId, String title, String description) {
        this.imageResId = imageResId;
        this.title = title;
        this.description = description;
    }

    public int getImageResId() {return imageResId;}

    public void setImageResId(int imageResId) {this.imageResId = imageResId;}

    public String getImageUrl() {return imageUrl;}

    public void setImageUrl(String imageUrl) {this.imageUrl = imageUrl;}

    public String getTitle() {return title;}

    public void setTitle(String title) {this.title = title;}

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}

}


