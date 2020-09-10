package com.example.deepali.animalfight;


import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Deepali on 25-04-2015.
 */
public class MusicService extends  Service {


    MediaPlayer mediaPlayer;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

        mediaPlayer = MediaPlayer.create(this, R.raw.background2);
        mediaPlayer.setLooping(true);

    }

    @Override
    public void onDestroy() {

        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer= null;
        }
    }

    @Override
    public void onStart(Intent intent, int startid) {
       mediaPlayer.start();
    }
}