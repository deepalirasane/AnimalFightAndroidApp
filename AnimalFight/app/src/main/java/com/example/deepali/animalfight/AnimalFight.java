package com.example.deepali.animalfight;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;

import org.w3c.dom.Text;


public class AnimalFight extends ActionBarActivity implements OnClickListener {


     public static int round=0,win_game=0, cpu_win_game=0,sound=-99 ,win=1,loose=2,tie=3,Miss=4 ,height=0 ,width=0;
     public static TextView winner,round_number, winresultdisplay;
     public static ImageView btn_comptuer1;
     private static MediaPlayer mp;
     public static RelativeLayout toplayout;
     public static  String Player_name="";

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_fight);

        Context context=getApplication();
        Pref_status_Check.check_pref(context);



        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        Bundle bundle=getIntent().getExtras();



        //   Player_name=bundle.getString("playername");
        //TextView txt_display1= (TextView)findViewById(R.id.txt_displayname);
        //txt_display1.setText("Welcome "+bundle.getString("playername"));

        ImageButton btn_cat1=(ImageButton)findViewById(R.id.btn_cat);
        btn_cat1.setOnClickListener(this);

        ImageButton btn_mouse1=(ImageButton)findViewById(R.id.btn_mouse);
        btn_mouse1.setOnClickListener(this);

        ImageButton btn_elephant1= (ImageButton)findViewById(R.id.btn_elephant);
        btn_elephant1.setOnClickListener(this);


        winner= (TextView)findViewById(R.id.txt_winner_result);
        winresultdisplay=(TextView)findViewById((R.id.txt_winner_resultdisplay));
        round_number=(TextView)findViewById(R.id.txt_roundcount);
        btn_comptuer1=(ImageView)findViewById(R.id.btn_computer);
        toplayout = (RelativeLayout) findViewById(R.id.toprelativelayout);



         Animation_Image.Downslide();


           }
    public static void Round_Miss()  {
        winner.setText("You Miss");
        if(win_game<10  && cpu_win_game < 10) {
            win_game--;


            winresultdisplay.setText(win_game + "");
            Animation_Image.Downslide();
        }


    }
    public  void Game_Final_Result(int state)  {

        switch(state)
        {
            case 1: //win
                win_game++;
                sound=R.raw.winning;
                winner.setText("You Win");
                break;
            case 2://loose
                cpu_win_game++;
                win_game--;
                sound=R.raw.fail;
                winner.setText("You Loose");
                break;
            case 3://tie
                sound=R.raw.tie;
                winner.setText("Game Tie");
                break;


        }

        Background_Music.play_music(this,sound,2);
        winresultdisplay.setText(win_game+"");
        round_number.setText(cpu_win_game+"");
         if(win_game==10 )
         {
             Alert("you");
         }
        else if( cpu_win_game==10)
         {
             Alert("cpu");
         }
        else if(cpu_win_game <10 || win_game < 10)
               Animation_Image.Downslide();
        else
             finish();
    }
    public void onClick(View v) {
                round++;


        /*
         1=mouse
         2=cat
         3=elephant
         */

        switch (Animation_Image.computer_choice) {
            case 1:
                switch (v.getId()) {

                    case R.id.btn_mouse:
                            Game_Final_Result(tie);


                        break;
                    case R.id.btn_cat:
                             Game_Final_Result(win);

                        break;
                    case R.id.btn_elephant:

                            Game_Final_Result(loose);

                        break;
                }

                break;
            case 2:
                switch (v.getId()) {

                    case R.id.btn_mouse:
                         Game_Final_Result(loose);

                         break;
                    case R.id.btn_cat:

                            Game_Final_Result(tie);

                        break;
                    case R.id.btn_elephant:
                         Game_Final_Result(win);

                        break;
                }
                break;
            case 3:

                switch (v.getId()) {

                    case R.id.btn_mouse:
                         Game_Final_Result(win);

                        break;
                    case R.id.btn_cat:
                          Game_Final_Result(loose);

                        break;
                    case R.id.btn_elephant:
                         Game_Final_Result(tie);
                        break;
                }
                break;
        }


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_animal_fight, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
         sound=R.raw.menusound;
        Background_Music.play_music(this,sound,2);

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i= new Intent(this,Prefs.class);
            startActivity(i);
            return   true;        }

        return super.onOptionsItemSelected(item);
    }

    private void Alert(String msg) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AnimalFight.this);

        alertDialogBuilder.setTitle(this.getTitle()+ " Winner");
        if(msg=="you")
        alertDialogBuilder.setMessage("Congratulation you win !!!!!!!!!! Do you want continue.....?");
        else
            alertDialogBuilder.setMessage("Sorry you loose. Do you want continue.....?");
        // set positive button: Yes message
        alertDialogBuilder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                // go to a new activity of the app
             finish();
                Intent restart_game = new Intent(getApplicationContext(),
                        AnimalFight.class);
                restart_game.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(restart_game);
            }
        });
        // set negative button: No message
        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                // cancel the alert box and put a Toast to the user
                finish();
                dialog.cancel();
                Intent close = new Intent(getApplicationContext(),
                        Game_Start_PageActivity.class);
                close.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(close);


            }
        });
        // set neutral button: Exit the app message


        AlertDialog alertDialog = alertDialogBuilder.create();
        // show alert
        alertDialog.show();
    }
    protected void onResume(){
        super.onResume();
        win_game=0;
        cpu_win_game=0;


    }

}
