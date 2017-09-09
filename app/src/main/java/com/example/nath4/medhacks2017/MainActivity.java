package com.example.nath4.medhacks2017;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View; //required for class View
import android.content.Intent; //required for class Intent
import android.provider.MediaStore; //Required for MediaStore symbol -- The Media provider contains meta data for all available media on both internal and external storage devices.
import android.net.Uri; //Required to use URIs
import android.widget.VideoView; //Required for VideoView

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }//end onCreate

    public void onRecordInit(View view) {
        //create intent access camera
        dispatchTakeVideoIntent();
    }//end onRecordInit

    //BEGIN CODE TO INITIALIZE INTENT TO CAMERA ON HARDWARE(PHONE)
    static final int REQUEST_VIDEO_CAPTURE = 1;

    private void dispatchTakeVideoIntent() {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
        }
    }//end dispatchTakeVideoIntent
    //END CODE TO INITIALIZE INTENT TO CAMERA ON HARDWARE(PHONE)

    //BEGIN CODE TO VIEW VIDEO
    boolean RECORD_FIRST = false;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            VideoView mVideoView = (VideoView) findViewById(R.id.videoview);
            Uri videoUri = intent.getData();
            mVideoView.setVideoURI(videoUri);
            RECORD_FIRST = true;
        }
    }
    //END CODE TO VIEW VIDEO

    //BEGIN CODE TO PLAY VIDEO
    protected void onPlayInit(View view) {

        if(RECORD_FIRST) {
            VideoView mVideoView = (VideoView) findViewById(R.id.videoview);
            mVideoView.start();
        }
    }
    //END CODE TO PLAY VIDEO

    //BEGIN CODE TO PAUSE VIDEO
    protected void onPauseInit(View view) {

        VideoView mVideoView = (VideoView) findViewById(R.id.videoview);
        if(mVideoView.isPlaying()) {
            mVideoView.pause();
        }
    }
    //END CODE TO PAUSE VIDEO


}//End Main Function
