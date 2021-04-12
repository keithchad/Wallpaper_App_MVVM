package com.chad.wallpaperapp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.wallpaperapp.constants.Constants;
import com.chad.wallpaperapp.viewmodel.WallpaperViewModel;

public class MainActivity extends AppCompatActivity {

    private WallpaperViewModel wallpaperViewModel;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {
        wallpaperViewModel = new ViewModelProvider(this).get(WallpaperViewModel.class);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        getData();
    }

    private void getData() {
        swipeRefreshLayout.setRefreshing(true);
        wallpaperViewModel.getNewPhotos(Constants.API_KEY).observe(this, wallpaper -> {
            if (wallpaper != null) {
                if (wallpaper.get(0).getUser().getProfileImage().getLarge() != null) {
                    swipeRefreshLayout.setRefreshing(false);
                    Toast.makeText(this, wallpaper.get(0).getUser().getProfileImage().getLarge(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}