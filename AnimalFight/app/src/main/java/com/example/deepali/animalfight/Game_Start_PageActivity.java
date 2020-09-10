package com.example.deepali.animalfight;


import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.media.AudioManager;


public class Game_Start_PageActivity extends ActionBarActivity implements  OnClickListener{



     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game__start__page);


         setVolumeControlStream(AudioManager.STREAM_MUSIC);

       //  startService(new Intent(this, MusicService.class));

         View startgame_btn=findViewById(R.id.btn_start_game);
        startgame_btn.setOnClickListener( this);


        View aboutus_btn=findViewById(R.id.btn_About_us);
        aboutus_btn.setOnClickListener( this);

         Context context=getApplication();
         Pref_status_Check.check_pref(context);







    }



    @Override
     public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game__start__page, menu);
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
      @Override
      public void onClick(View v) {
          AnimalFight.sound=R.raw.btnclick2;
          Background_Music.play_music(this,AnimalFight.sound,2);
          switch (v.getId())
          {
              case R.id.btn_start_game:
                                        Intent i= new Intent(this,AnimalFight.class);
                                         startActivity(i);
                  break;
              case R.id.btn_About_us:
                                      Intent i1= new Intent(this,AboutUs.class);
                                      startActivity(i1);
              break;

          }
      }




    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this, MusicService.class));
    }



  }
