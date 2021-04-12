package com.chad.wallpaperapp.api;

import com.chad.wallpaperapp.model.Wallpaper;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("photos")
    public Call<Wallpaper> getNewPhotos(
            @Query("client_id")
            String API_KEY
    );

    @GET("photos/random")
    public Call<Wallpaper> getRandomPhoto(
            @Query("client_id")
            String API_KEY
    );

}
