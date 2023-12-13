package com.example.spotify;

public class SliderItem {
    private int imageResId;
    private String imageUrl;
    private String title;
    private String description;
    private Album album;


    public SliderItem() {

    }

    // Constructor for local resource image
    public SliderItem(int imageResId, String title, String description, Album album) {
        this.imageResId = imageResId;
        this.title = title;
        this.description = description;
        this.album = album;
    }

    public int getImageResId() {return imageResId;}

    public void setImageResId(int imageResId) {this.imageResId = imageResId;}

    public String getImageUrl() {return imageUrl;}

    public void setImageUrl(String imageUrl) {this.imageUrl = imageUrl;}

    public String getTitle() {return title;}

    public void setTitle(String title) {this.title = title;}

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}

    public void setAlbum(Album album) {this.album = album;}

    public Album getAlbum() {return album;}

}


