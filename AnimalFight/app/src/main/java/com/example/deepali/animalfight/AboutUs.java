package com.example.deepali.animalfight;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;


public class AboutUs extends ActionBarActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        WebView text1= (WebView)findViewById(R.id.textView);
        text1.loadData("<html><body style=\"text-align:justify\">" +
                "There are three kinds of animals , mouse cat and elephant." +
                "The mouse can be eaten by cat.The cat can be crumpled by the elephant. However, mouse can beat  the  elephant. "+
                "</body></Html>", "text/html", "utf-8");

        Context context=getApplication();
        Pref_status_Check.check_pref(context);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_about_us, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        AnimalFight.sound=R.raw.menusound;
         Background_Music.play_music(this,AnimalFight.sound,2);


        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i= new Intent(this,Prefs.class);
            startActivity(i);
            return   true;
        }

        return super.onOptionsItemSelected(item);
    }



}
