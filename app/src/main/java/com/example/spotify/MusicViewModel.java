package com.example.spotify;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MusicViewModel extends ViewModel {
    private MutableLiveData<LoveList> selectedSong = new MutableLiveData<>();

    public void selectSong(LoveList song) {
        selectedSong.setValue(song);
    }

    public MutableLiveData<LoveList> getSelectedSong() {
        return selectedSong;
    }
}
