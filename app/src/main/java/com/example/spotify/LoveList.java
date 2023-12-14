package com.example.spotify;

public class LoveList {
    private String hero_image;
    private String songName;
    private String songArtist;

    public LoveList(String hero_image, String songName){
        this.hero_image = hero_image;
        this.songName = songName;
    }

    public String getHeroImage() {
        return hero_image;
    }
    public String getSongName() {
        return songName;
    }
    public String getSongArtist() { return songArtist; }

    @Override
    public String toString() {
        return "LoveList{" +
                "songName='" + songName + '\'' +
                ", heroImage='" + hero_image + '\'' +
                // Other fields you want to include
                '}';
    }
}