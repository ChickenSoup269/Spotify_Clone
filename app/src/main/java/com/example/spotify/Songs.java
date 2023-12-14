package com.example.spotify;

import android.os.Parcel;
import android.os.Parcelable;

public class Songs implements Parcelable {
    String songId, title,artist, releaseDate, cover, thumbnail, audio;

    public Songs(String songId, String title, String artist, String releaseDate, String cover, String thumbnail, String audio) {
        this.songId = songId;
        this.title = title;
        this.artist = artist;
        this.releaseDate = releaseDate;
        this.cover = cover;
        this.thumbnail = thumbnail;
        this.audio = audio;
    }

    public Songs(String songId, String title, String cover, String thumbnail) {
        this.songId = songId;
        this.title = title;
        this.cover = cover;
        this.thumbnail = thumbnail;
    }
    protected Songs(Parcel in) {
        songId = in.readString();
        title = in.readString();
        artist = in.readString();
        releaseDate = in.readString();
        cover = in.readString();
        thumbnail = in.readString();
        audio = in.readString();
    }

    public static final Creator<Songs> CREATOR = new Creator<Songs>() {
        @Override
        public Songs createFromParcel(Parcel in) {
            return new Songs(in);
        }

        @Override
        public Songs[] newArray(int size) {
            return new Songs[size];
        }
    };

    // Override writeToParcel method to write to Parcel
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(songId);
        dest.writeString(title);
        dest.writeString(artist);
        dest.writeString(releaseDate);
        dest.writeString(cover);
        dest.writeString(thumbnail);
        dest.writeString(audio);
    }

    // Other methods

    @Override
    public int describeContents() {
        return 0;
    }

    public String getCover() {return cover;}
    public void setCover(String cover) {this.cover = cover;}
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
