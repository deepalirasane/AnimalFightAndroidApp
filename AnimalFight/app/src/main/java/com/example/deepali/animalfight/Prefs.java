package com.example.deepali.animalfight;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class Prefs extends PreferenceActivity {

    public static CheckBoxPreference check_box1,check_box2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_prefs);
        addPreferencesFromResource(R.xml.settings);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        check_box1 = (CheckBoxPreference)findPreference("musickey");



        check_box1.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

            @Override
            public boolean onPreferenceClick(Preference preference) {
               AnimalFight.sound=R.raw.checkboxclick;
                  Context context= getApplicationContext();
                Background_Music.play_music(context,AnimalFight.sound,2);
                loadSavedPreferences(context);

                return false;
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_prefs, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    public static void loadSavedPreferences(Context context){
        boolean checkBoxValue;
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());

        checkBoxValue = sharedPreferences.getBoolean("musickey", false);
        if (checkBoxValue) {
            Prefs.check_box1.setChecked(true);
            savePreferences("musickey", checkBoxValue,context);
            //Background_Music.play_music(this, R.raw.backgoundmusic, 1);
            context.startService(new Intent(context, MusicService.class));

        } else {
            Prefs.check_box1.setChecked(false);
            savePreferences("musickey", checkBoxValue,context);
            //Background_Music.stop_music(this);
            context.stopService(new Intent(context, MusicService.class));
        }




    }

    public  static void savePreferences(String key, boolean value,Context context){
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);

        editor.commit();
    }


}
