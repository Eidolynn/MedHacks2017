package com.example.nath4.medhacks2017;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View; //required for class View
import android.content.Intent; //required for class Intent
import android.provider.MediaStore; //Required for MediaStore symbol -- The Media provider contains meta data for all available media on both internal and external storage devices.
import android.net.Uri; //Required to use URIs
import android.widget.VideoView; //Required for VideoView

public class MainActivity extends AppCompatActivity {
     Uri videoUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }//end onCreate

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
            videoUri = intent.getData();
            mVideoView.setVideoURI(videoUri);
            WatchVid vid = new WatchVid();
            vid.setVideoUri(videoUri);
            RECORD_FIRST = true;
        }
        if(RECORD_FIRST) {
            // Create a new intent to open the {@link FamilyActivity}
            Intent contactsIntent = new Intent(MainActivity.this, Contacts.class);

            // Start the new activity
            startActivity(contactsIntent);
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

    //BEGIN onContactClick Go Left
    protected void onContactClick(View view){

        // Create a new intent to open the {@link FamilyActivity}
        Intent recieveIntent = new Intent(MainActivity.this, Recieve.class);

        // Start the new activity
        startActivity(recieveIntent);

    }//END onContactClick

    //BEGIN onCamClick
    protected void onCamClick(View view) {

        //Start taking photo and get the URI
        dispatchTakeVideoIntent();

    }//END onCamClick

    protected void onStartVid(View view){
        Intent watchVidIntent = new Intent(MainActivity.this, WatchVid.class);

        startActivity(watchVidIntent);
    }

}//End Main Function
