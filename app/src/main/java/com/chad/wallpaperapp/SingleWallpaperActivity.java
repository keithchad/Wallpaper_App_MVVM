package com.chad.wallpaperapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.wallpaperapp.constants.Constants;
import com.chad.wallpaperapp.model.WallpaperList;
import com.chad.wallpaperapp.viewmodel.WallpaperViewModel;
import com.makeramen.roundedimageview.RoundedImageView;

import de.hdodenhof.circleimageview.CircleImageView;

public class SingleWallpaperActivity extends AppCompatActivity {

    private WallpaperViewModel wallpaperViewModel;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RoundedImageView wallpaperImage;
    private TextView textLike;
    private CircleImageView profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_wallpaper);
        initialize();
    }

    private void initialize() {
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayoutSingle);
        wallpaperImage = findViewById(R.id.wallpaperImageSingle);
        profileImage = findViewById(R.id.profileImageSingle);
        textLike = findViewById(R.id.textLikesSingle);
        wallpaperViewModel = new ViewModelProvider(this).get(WallpaperViewModel.class);
        swipeRefreshLayout.setOnRefreshListener(this::getRandomPhotos);
        getRandomPhotos();
    }

    @SuppressLint("SetTextI18n")
    private void getRandomPhotos() {
        swipeRefreshLayout.setRefreshing(true);
        wallpaperViewModel.getRandomPhotos(Constants.API_KEY).observe(this, wallpaperList -> {
            if (wallpaperList != null) {
                swipeRefreshLayout.setRefreshing(false);
                String wallpaper = wallpaperList.getUrls().getRegular();
                String profile = wallpaperList.getUser().getProfileImage().getLarge();
                Integer likes = wallpaperList.getLikes();

                textLike.setText(likes.toString());
                Glide.with(getApplicationContext()).load(wallpaper).into(wallpaperImage);
                Glide.with(getApplicationContext()).load(profile).into(profileImage);
            }
        });

    }
}