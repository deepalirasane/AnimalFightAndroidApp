package com.example.deepali.animalfight;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


/**
 * Created by Deepali on 25-04-2015.
 */
public class Pref_status_Check {


    public  static void check_pref(Context context) {
        boolean checkBoxValue;
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());

        checkBoxValue = sharedPreferences.getBoolean("musickey", false);
        if (checkBoxValue) {

            context.startService(new Intent(context, MusicService.class));
        }

        else
        {
            context.stopService(new Intent(context, MusicService.class));
        }
    }

}
