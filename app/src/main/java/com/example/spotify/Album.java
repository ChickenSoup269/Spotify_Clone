package com.example.spotify;

import java.util.List;

public class Album {
    private String albumId, albumTitle, albumThumbnail, sortDescription;
    private List<Songs> songs;

    public Album(){}

    public Album(String albumId, String albumTitle, String albumThumbnail, String sortDescription, List<Songs> songs) {
        this.albumId = albumId;
        this.albumTitle = albumTitle;
        this.albumThumbnail = albumThumbnail;
        this.sortDescription = sortDescription;
        this.songs = songs;
    }

    public String getAlbumId() {return albumId;}

    public void setAlbumId(String albumId) {this.albumId = albumId;}

    public String getAlbumTitle() {return albumTitle;}

    public void setAlbumTitle(String albumTitle) {this.albumTitle = albumTitle;}

    public String getAlbumThumbnail() {return albumThumbnail;}

    public void setAlbumThumbnail(String albumThumbnail) {this.albumThumbnail = albumThumbnail;}

    public String getSortDescription() {return sortDescription;}

    public void setSortDescription(String sortDescription) {this.sortDescription = sortDescription;}

    public List<Songs> getSongs() {return songs;}

    public void setSongs(List<Songs> songs) {this.songs = songs;}

    public void addSong(Songs song) {
        songs.add(song);
    }

    public void removeSong(Songs song) {
        songs.remove(song);
    }

}
