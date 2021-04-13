package com.chad.wallpaperapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.wallpaperapp.adapter.WallpaperAdapter;
import com.chad.wallpaperapp.constants.Constants;
import com.chad.wallpaperapp.model.WallpaperList;
import com.chad.wallpaperapp.viewmodel.WallpaperViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private WallpaperViewModel wallpaperViewModel;
    private SwipeRefreshLayout swipeRefreshLayout;
    private WallpaperAdapter wallpaperAdapter;
    private List<WallpaperList> list;
    private Integer currentPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {

        wallpaperViewModel = new ViewModelProvider(this).get(WallpaperViewModel.class);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        ImageView imageLaunch = findViewById(R.id.imageLaunch);
        list = new ArrayList<>();
        wallpaperAdapter = new WallpaperAdapter(this, list);

        recyclerView.setAdapter(wallpaperAdapter);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        imageLaunch.setOnClickListener(v -> {
            Intent intent = new Intent(this, SingleWallpaperActivity.class);
            startActivity(intent);
        });

        swipeRefreshLayout.setOnRefreshListener(this::getData);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if(!recyclerView.canScrollVertically(1)) {
                    currentPage += 1;
                    getData();
                }
            }
        });

        getData();
    }

    private void getData() {

        swipeRefreshLayout.setRefreshing(true);
        wallpaperViewModel.getNewPhotos(currentPage, 20, Constants.API_KEY).observe(this, wallpaper -> {
            if (wallpaper != null) {
                int oldCount = wallpaper.size();
                swipeRefreshLayout.setRefreshing(false);
                list.addAll(wallpaper);
                wallpaperAdapter.notifyItemRangeInserted(oldCount, wallpaper.size());
            }
        });

    }
}