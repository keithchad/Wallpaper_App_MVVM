package com.chad.wallpaperapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.chad.wallpaperapp.model.WallpaperList;
import com.chad.wallpaperapp.repository.WallpaperRepository;

import java.util.List;

public class WallpaperViewModel extends AndroidViewModel {

    private final WallpaperRepository wallpaperRepository;

    public WallpaperViewModel(@NonNull Application application) {
        super(application);
        wallpaperRepository = new WallpaperRepository(application);
    }

    public LiveData<List<WallpaperList>> getNewPhotos(Integer page, Integer perPage, String apiKey) {
        return wallpaperRepository.getNewPhotos(page, perPage, apiKey);
    }

    public LiveData<WallpaperList> getRandomPhotos(String apiKey) {
        return wallpaperRepository.getRandomPhotos(apiKey);
    }

}
