package com.loopar.zp.Activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.loopar.zp.Fragment.FragmentHome;
import com.loopar.zp.R;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class LoadingActivity extends AppCompatActivity {



    GifDrawable gifImageView;
    TextView tv_timer_loading;

    // start time in milliseconds to count down
    long totalMilliseconds = 10000;
    //count down interval in milliseconds
    long interval = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        tv_timer_loading = (TextView)findViewById(R.id.tv_timer_loading);

        new CountDownTimer(totalMilliseconds, interval) {

            @Override
            public void onTick(long millisUntilFinished ) {

                tv_timer_loading.setText(""+ millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {

                tv_timer_loading.setText("مبلغ به کیف پول شما اضافه شد.");
            }
        }.start();




        //resource (drawable or raw)
        try {
            gifImageView = new GifDrawable( getAssets(), "seo.gif" );
        } catch (IOException e) {
            e.printStackTrace();
        }

        Thread welcomeThread = new Thread() {

            @Override
            public void run() {
                try {
                    super.run();
                    sleep(12000);  //Delay of 10 seconds
                } catch (Exception e) {

                } finally {

                    Intent i = new Intent(LoadingActivity.this, MainActivity.class);
                    startActivity(i);


                }
            }
        };
        welcomeThread.start();

    }
}
