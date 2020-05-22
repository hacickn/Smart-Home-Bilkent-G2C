package com.example.smarthome_v2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * a Open Screen class
 *
 * @author Tarık Buğra Karali
 * @version 11.05.2020
 */
public class OpenScreen extends Activity {

    //properties
    private Animation anim, anim_two;

    // @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opening_screen);
        TextView shome = (TextView) findViewById(R.id.shometext);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/BlackHanSans-Regular.ttf");
        shome.setTypeface(typeface);
        ImageView roof = findViewById(R.id.roof);

        anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim);
        //anim.reset();
        shome.clearAnimation();

        shome.startAnimation(anim);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }
            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intocan = new Intent(OpenScreen.this, MainActivity.class);
                startActivity(intocan);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        roof.startAnimation(anim);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }
            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intocan = new Intent(OpenScreen.this, MainActivity.class);
                startActivity(intocan);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
