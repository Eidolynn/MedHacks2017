package com.example.nath4.medhacks2017;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;
import android.net.Uri; //Required to use URIs

public class WatchVid extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_vid);

        VideoView video = (VideoView)findViewById(R.id.videoView);
        String path = "android.resource://com.example.nath4.medhacks2017/"+R.raw.pill;
        Uri uri = Uri.parse(path);
        video.setVideoURI(uri);
        video.requestFocus();
        video.start();
    }

}
