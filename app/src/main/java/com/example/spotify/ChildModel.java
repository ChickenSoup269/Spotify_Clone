package com.example.spotify;

public class ChildModel {
    private  String hero_image;
    private String songName;

    public ChildModel(){}

    public ChildModel(String hero_image, String songName){
        this.hero_image = hero_image;
        this.songName = songName;
    }
    public String getHeroImage() {
        return hero_image;
    }
    public String getSongName() {
        return songName;
    }
}