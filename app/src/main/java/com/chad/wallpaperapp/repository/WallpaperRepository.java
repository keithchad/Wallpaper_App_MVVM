package com.chad.wallpaperapp.repository;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.chad.wallpaperapp.api.ApiClient;
import com.chad.wallpaperapp.api.ApiService;
import com.chad.wallpaperapp.model.Wallpaper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WallpaperRepository {

    private final ApiService apiService;
    private final Context context;

    public WallpaperRepository(Context context) {
        apiService = new ApiClient().getRetrofit().create(ApiService.class);
        this.context = context;
    }

    public LiveData<Wallpaper> getNewPhotos(String apiKey) {

        MutableLiveData<Wallpaper> data = new MutableLiveData<>();
        apiService.getNewPhotos(apiKey).enqueue(new Callback<Wallpaper>() {
            @Override
            public void onResponse(@NonNull Call<Wallpaper> call, @NonNull Response<Wallpaper> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<Wallpaper> call, @NonNull Throwable t) {
                Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        return data;
    }
}
