package com.chad.wallpaperapp.repository;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.chad.wallpaperapp.api.ApiClient;
import com.chad.wallpaperapp.api.ApiService;
import com.chad.wallpaperapp.model.WallpaperList;

import java.util.List;

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

    public LiveData<List<WallpaperList>> getNewPhotos(Integer page, Integer perPage, String apiKey) {

        MutableLiveData<List<WallpaperList>> data = new MutableLiveData<>();
        apiService.getNewPhotos(page, perPage, apiKey).enqueue(new Callback<List<WallpaperList>>() {
            @Override
            public void onResponse(@NonNull Call<List<WallpaperList>> call, @NonNull Response<List<WallpaperList>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<WallpaperList>> call, @NonNull Throwable t) {
                Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        return data;
    }

    public LiveData<WallpaperList> getRandomPhotos(String apiKey) {

        MutableLiveData<WallpaperList> data = new MutableLiveData<>();
        apiService.getRandomPhoto(apiKey).enqueue(new Callback<WallpaperList>() {
            @Override
            public void onResponse(@NonNull Call<WallpaperList> call,@NonNull Response<WallpaperList> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<WallpaperList> call,@NonNull Throwable t) {
                Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        return data;
    }
}
