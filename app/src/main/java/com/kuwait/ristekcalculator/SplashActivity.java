package com.kuwait.ristekcalculator;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
    private final int SPLASH_TIME_OUT=2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_splash );
        ImageView splashImage = findViewById( R.id.splashImage );
        splashImage.setImageResource( R.mipmap.calculator ); //https://www.flaticon.com/free-icon

        new Handler().postDelayed( new Runnable() {

            /* Referensi: https://www.androidhive.info/2013/07/how-to-implement-android-splash-screen-2/
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                //Reference: https://www.androidhive.info/2013/07/how-to-implement-android-splash-screen-2/
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
