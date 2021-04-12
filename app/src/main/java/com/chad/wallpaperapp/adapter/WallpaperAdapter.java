package com.chad.wallpaperapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.wallpaperapp.R;
import com.chad.wallpaperapp.model.WallpaperList;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class WallpaperAdapter extends RecyclerView.Adapter<WallpaperAdapter.ViewHolder> {

    private final Context context;
    private final List<WallpaperList> list;

    public WallpaperAdapter(Context context, List<WallpaperList> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.wallpaper_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WallpaperList wallpaper = list.get(position);

        String profileImage = wallpaper.getUser().getProfileImage().getLarge();
        String wallpaperImage = wallpaper.getUrls().getFull();
        Integer likes = wallpaper.getLikes();

        holder.textLikes.setText(likes.toString());
        Glide.with(context).load(profileImage).into(holder.profileImage);
        Glide.with(context).load(wallpaperImage).into(holder.wallpaperImage);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final CircleImageView profileImage;
        private final RoundedImageView wallpaperImage;
        private final TextView textLikes;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profileImage = itemView.findViewById(R.id.profileImage);
            wallpaperImage = itemView.findViewById(R.id.wallpaperImage);
            textLikes = itemView.findViewById(R.id.textLikes);
        }
    }
}
