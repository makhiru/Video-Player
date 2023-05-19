package com.example.videoplayer.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.videoplayer.Adapter.Recycler_Adapter;
import com.example.videoplayer.Constant;
import com.example.videoplayer.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    String[] per = {"android.permission.READ_EXTERNAL_STORAGE"};

    RecyclerView recyclerView;
    Recycler_Adapter adapter;
    FloatingActionButton recentvideo;

    SwipeRefreshLayout swipeRefreshLayout;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(per, 404);
        }

        recyclerView = findViewById(R.id.recyclerview);
        recentvideo = findViewById(R.id.recentVideo);

        preferences = getSharedPreferences("myPref", MODE_PRIVATE);

        recentvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String recentpath = preferences.getString("recentvideo", "no");

                if (!recentpath.equals("no")) {
                    Intent intent = new Intent(MainActivity.this, Video_Activity.class);
                    intent.putExtra("videopath", recentpath);
                    startActivity(intent);

                } else {
                    Toast.makeText(MainActivity.this, "Play any video First!!!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        swipeRefreshLayout = findViewById(R.id.refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                show();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (requestCode == 404) {
                show();
            }
        }
    }

    public void show() {
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        adapter = new Recycler_Adapter(MainActivity.this, Constant.allMediaList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_items, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.rate:
                Uri uri = Uri.parse("http://play.google.com/store/apps/details?id="
                        + getApplicationContext().getPackageName());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

                break;

            case R.id.refresh:

                finish();
                startActivity(getIntent());
                break;
            case R.id.share:

                Intent share_intent = new Intent();
                share_intent.setAction(Intent.ACTION_SEND);
                share_intent.putExtra(Intent.EXTRA_TEXT,
                        "http://play.google.com/store/apps/details?id="
                                + getApplicationContext().getPackageName());
                share_intent.setType("text/plain");
                startActivity(Intent.createChooser(share_intent, "Share APP via"));

                break;
        }
        return true;
    }
}