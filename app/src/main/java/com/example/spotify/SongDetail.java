package com.example.spotify;

public class SongDetail {
    String id, title, img, cover, artist, audio;

    public SongDetail(){};

    public SongDetail(String id, String title, String img, String cover, String artist, String audio) {
        this.id = id;
        this.title = title;
        this.img = img;
        this.cover = cover;
        this.artist = artist;
        this.audio = audio;
    }

    public String getId() {return id;}

    public void setId(String id) {this.id = id;}

    public String getTitle() {return title;}

    public void setTitle(String title) {this.title = title;}

    public String getImg() {return img;}

    public void setImg(String img) {this.img = img;}

    public String getCover() {return cover;}

    public void setCover(String cover) {this.cover = cover;}

    public String getArtist() {return artist;}

    public void setArtist(String artist) {this.artist = artist;}

    public String getAudio() {return audio;}

    public void setAudio(String audio) {this.audio = audio;}
}
