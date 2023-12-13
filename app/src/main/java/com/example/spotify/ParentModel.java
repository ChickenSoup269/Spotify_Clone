package com.example.spotify;

import java.util.ArrayList;

public class ParentModel {
    private String albumTitle;
    private ArrayList<Songs> songs;
    public ParentModel(){}
    public ParentModel(String albumTitle, ArrayList<Songs> songs) {
        this.albumTitle = albumTitle;
        this.songs = songs;
    }

    public void setAlbumTitle(String albumTitle) {this.albumTitle = albumTitle;}
    public String getAlbumTitle() {return albumTitle;}
    public ArrayList<Songs> getSongs() {return songs;}
    public void setSongs(ArrayList<Songs> songs) {this.songs = songs;}
}
