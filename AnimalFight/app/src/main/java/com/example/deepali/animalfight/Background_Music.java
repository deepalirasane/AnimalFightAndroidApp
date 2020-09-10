package com.example.deepali.animalfight;

/**
 * Created by Deepali on 09-04-2015.
 */
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
public class Background_Music {

    private  static MediaPlayer mp=null,mp1=null;

    public static void play_music(Context context,int musicid,int sound_type){

        stop_music(context);
        mp=MediaPlayer.create(context,musicid);
        mp.start();
    }

    public  static  void stop_music(Context context) {
        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }



}
