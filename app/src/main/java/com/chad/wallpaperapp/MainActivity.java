package com.chad.wallpaperapp;

import android.os.Bundle;
import android.widget.Toast;

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
        list = new ArrayList<>();
        wallpaperAdapter = new WallpaperAdapter(this, list);

        recyclerView.setAdapter(wallpaperAdapter);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        swipeRefreshLayout.setOnRefreshListener(this::getData);

        getData();
    }

    private void getData() {
        swipeRefreshLayout.setRefreshing(true);
        wallpaperViewModel.getNewPhotos(Constants.API_KEY).observe(this, wallpaper -> {
            if (wallpaper != null) {
                swipeRefreshLayout.setRefreshing(false);
                list.clear();
                list.addAll(wallpaper);
                wallpaperAdapter.notifyDataSetChanged();
            }
        });

    }
}