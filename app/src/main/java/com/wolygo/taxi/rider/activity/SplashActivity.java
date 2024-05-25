package com.wolygo.taxi.rider.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.wolygo.taxi.rider.R;
import com.wolygo.taxi.rider.settings.AppConst;
import com.wolygo.taxi.rider.settings.PrefManager;


public class SplashActivity extends BaseActivity {
    private PrefManager prefManager;
    private static int SPLASH_TIME_OUT = 1000;
    private Handler handler = new Handler();
    private Context context;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        setContentView(R.layout.activity_splash);
        title = (TextView) findViewById(R.id.title);
        context = SplashActivity.this;
        title.setTypeface(AppConst.font_quicksand_medium(context));

        // making notification bar transparent
        changeStatusBarColor();

        prefManager = new PrefManager(this);
        if (!prefManager.isFirstTimeLaunch()) {
            launchHomeScreen();
        }else{
            timeHandler();
        }
    }

    private void timeHandler(){
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                launchHomeScreen();
            }
        },SPLASH_TIME_OUT);
    }

    private void launchHomeScreen() {
        //prefManager.setFirstTimeLaunch(false);
        startActivity(new Intent(SplashActivity.this, LanguageChoosing.class));
        finish();
    }

    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
}
