package com.example.spotify;

public class ChildModel {
    private  String hero_image;
    private String songName;
    private String songArtist;
    private String songAudio;

    public ChildModel(String hero_image, String songName, String songArtist, String songAudio){
        this.hero_image = hero_image;
        this.songName = songName;
        this.songArtist = songArtist;
        this.songAudio = songAudio;
    }

    public String getHeroImage() {
        return hero_image;
    }
    public String getSongName() {
        return songName;
    }
    public String getSongArtist() { return songArtist; }
    public String getSongAudio() { return songAudio; }

}