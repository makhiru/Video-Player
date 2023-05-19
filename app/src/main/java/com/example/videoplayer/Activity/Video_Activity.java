package com.example.videoplayer.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.videoplayer.R;

public class Video_Activity extends AppCompatActivity {

    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        videoView = findViewById(R.id.videoview);

        String videopath = getIntent().getStringExtra("videopath");
        videoView.setVideoPath(videopath);
        videoView.setMediaController(new MediaController(Video_Activity.this));
        videoView.requestFocus();
        videoView.start();

    }
}