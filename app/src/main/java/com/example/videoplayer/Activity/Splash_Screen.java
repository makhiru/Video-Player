package com.example.videoplayer.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import com.example.videoplayer.Method;
import com.example.videoplayer.R;

import java.io.File;

import hendrawd.storageutil.library.StorageUtil;

public class Splash_Screen extends AppCompatActivity {

    private String[] allpath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        String[] per = {"android.permission.READ_EXTERNAL_STORAGE"};
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(per, 10);
        }

        getSupportActionBar().hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                allpath = StorageUtil.getStorageDirectories(Splash_Screen.this);
                for (String path : allpath) {

                    File storage = new File(path);
                    Method.load_Directory_files(storage);
                }

                Intent intent = new Intent(Splash_Screen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);

    }
}