package com.example.spotify;

public class Songs {
    String songId, title,artist, releaseDate, thumbnail, audio;

    public Songs(String songId, String title, String artist, String releaseDate, String thumbnail, String audio) {
        this.songId = songId;
        this.title = title;
        this.artist = artist;
        this.releaseDate = releaseDate;
        this.thumbnail = thumbnail;
        this.audio = audio;
    }

    public Songs(String songId, String title, String thumbnail) {
        this.songId = songId;
        this.title = title;
        this.thumbnail = thumbnail;
    }

    public String getSongId() {return songId;}
    public void setSongId(String songId) {this.songId = songId;}
    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}
    public String getArtist() {return artist;}
    public void setArtist(String artist) {this.artist = artist;}
    public String getReleaseDate() {return releaseDate;}
    public void setReleaseDate(String releaseDate) {this.releaseDate = releaseDate;}
    public String getThumbnail() {return thumbnail;}
    public void setThumbnail(String thumbnail) {this.thumbnail = thumbnail;}
    public String getAudio() {return audio;}
    public void setAudio(String audio) {this.audio = audio;}
}
