package com.example.spotify;

public class searchmodel {
    private String albumTitle;
    private String artistName;
    private String albumThumbnail;

    public searchmodel(String albumTitle, String artistName, String albumThumbnail) {
        this.albumTitle = albumTitle;
        this.artistName = artistName;
        this.albumThumbnail = albumThumbnail;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getAlbumThumbnail() {
        return albumThumbnail;
    }
}
