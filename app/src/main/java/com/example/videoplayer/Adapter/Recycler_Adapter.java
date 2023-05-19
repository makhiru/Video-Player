package com.example.videoplayer.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.videoplayer.R;
import com.example.videoplayer.Activity.Video_Activity;

import java.io.File;
import java.util.ArrayList;

public class Recycler_Adapter extends RecyclerView.Adapter<Recycler_Adapter.ViewHolder> {

    Context context;
    ArrayList<File> mediaList;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public Recycler_Adapter(Context context, ArrayList<File> mediaList) {
        this.context = context;
        this.mediaList = mediaList;
        sharedPreferences = context.getSharedPreferences("myPref", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.folder_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.txt.setText(mediaList.get(position).getName());
        Uri uri = Uri.fromFile(mediaList.get(position));

        Glide.with(context)
                .load(uri).thumbnail(0.1f).into((holder.img));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editor.putString("recentvideo", mediaList.get(holder.getAdapterPosition()).getAbsolutePath());
                editor.commit();

                Intent intent = new Intent(context, Video_Activity.class);
                intent.putExtra("videopath", mediaList.get(holder.getAdapterPosition()).getAbsolutePath());
                context.startActivity(intent);
                System.out.println(mediaList.get(holder.getAdapterPosition()).getAbsolutePath());
            }
        });

    }

    @Override
    public int getItemCount() {
        return mediaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView txt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.imgvideo);
            txt = itemView.findViewById(R.id.text);
        }
    }
}
