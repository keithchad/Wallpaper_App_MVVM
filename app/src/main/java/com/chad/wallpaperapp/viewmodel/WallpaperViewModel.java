package com.chad.wallpaperapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.chad.wallpaperapp.model.Wallpaper;
import com.chad.wallpaperapp.repository.WallpaperRepository;

public class WallpaperViewModel extends AndroidViewModel {

    private final WallpaperRepository wallpaperRepository;

    public WallpaperViewModel(@NonNull Application application) {
        super(application);
        wallpaperRepository = new WallpaperRepository(application);
    }

    public LiveData<Wallpaper> getNewPhotos(String apiKey) {
        return wallpaperRepository.getNewPhotos(apiKey);
    }

}
