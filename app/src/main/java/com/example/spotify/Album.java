package com.example.spotify;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Album implements Parcelable {
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

    // Parcelable methods
    protected Album(Parcel in) {
        albumId = in.readString();
        albumTitle = in.readString();
        albumThumbnail = in.readString();
        sortDescription = in.readString();
        songs = new ArrayList<>();
        in.readList(songs, Songs.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(albumId);
        dest.writeString(albumTitle);
        dest.writeString(albumThumbnail);
        dest.writeString(sortDescription);
        dest.writeList(songs);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Album> CREATOR = new Creator<Album>() {
        @Override
        public Album createFromParcel(Parcel in) {
            return new Album(in);
        }

        @Override
        public Album[] newArray(int size) {
            return new Album[size];
        }
    };

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
