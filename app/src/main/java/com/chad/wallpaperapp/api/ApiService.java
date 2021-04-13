package com.chad.wallpaperapp.api;

import com.chad.wallpaperapp.model.WallpaperList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("photos")
    public Call<List<WallpaperList>> getNewPhotos(
            @Query("page")
            Integer page,
            @Query("per_page")
            Integer perPage,
            @Query("client_id")
            String API_KEY
    );

    @GET("photos/random")
    public Call<List<WallpaperList>> getRandomPhoto(
            @Query("client_id")
            String API_KEY
    );

}
