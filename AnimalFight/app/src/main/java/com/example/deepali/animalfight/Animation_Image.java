package com.example.deepali.animalfight;

import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by Deepali on 16-04-2015.
 */
public class Animation_Image {

     public static int computer_choice=-1;


    public  static void Downslide() {

        computer_choice  =(int)(Math.random()*3+1);
        switch (computer_choice)
        {
            case 1: AnimalFight.btn_comptuer1.setImageResource(R.drawable.rat);
                break;
            case 2:  AnimalFight.btn_comptuer1.setImageResource(R.drawable.cat1);
                break;
            case 3:
                AnimalFight.btn_comptuer1.setImageResource(R.drawable.ele);
                break;

        }

        AnimalFight.btn_comptuer1.setPadding(0, 0, 0, 50);
        Animation down = null;
        down = new TranslateAnimation(0,0,0,200);
        down.setDuration(1500);
        down.setFillAfter(true);
        down.setFillEnabled(true);
        down.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {



            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

               if(AnimalFight.cpu_win_game< 10 || AnimalFight.win_game < 10)
               AnimalFight.Round_Miss();

            }

        });
        AnimalFight.toplayout.startAnimation(down);
    }
}
