package com.zeneo.abraj;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        boolean isPicked = false;
        try {
            isPicked = preferences.getBoolean("isPicked",false);
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        final boolean finalIsPicked = isPicked;

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (!finalIsPicked){
                    Intent intent = new Intent(getApplicationContext(), PickActivity.class);
                    startActivity(intent);
                } else {
                    int zodiakNum;
                    Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                    zodiakNum = preferences.getInt("index",0);
                    mainIntent.putExtra("zodiakNum",zodiakNum);
                    startActivity(mainIntent);
                }
                finish();


            }
        },2000);

    }

    @Override
    protected void onPause() {
        super.onPause();

    }
}
