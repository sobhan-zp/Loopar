package com.loopar.zp.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.loopar.zp.Classes.AppConfig;
import com.loopar.zp.Classes.SharedPrefSave;
import com.loopar.zp.Fragment.FragmentHome;
import com.loopar.zp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LoadingActivity extends AppCompatActivity {



    GifDrawable gifImageView;
    TextView tv_timer_loading;

    // start time in milliseconds to count down
    long totalMilliseconds = 10000;
    //count down interval in milliseconds
    long interval = 1000;

    private SharedPrefSave save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        save=new SharedPrefSave(this);
        tv_timer_loading = findViewById(R.id.tv_timer_loading);

        new CountDownTimer(totalMilliseconds, interval) {

            @Override
            public void onTick(long millisUntilFinished ) {

                tv_timer_loading.setText(""+ millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {

                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("username" , save.load("username",""));
                    jsonObject.put("money" , 28);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, AppConfig.URL_SENDSCORE, jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getString("message").equals("yes"))
                            {
                                tv_timer_loading.setText("مبلغ به کیف پول شما اضافه شد.");
                            }else if (response.getString("message").equals("no")){
                                tv_timer_loading.setText("خطایی پیش آمده لطفا بعدا سعی کنید.");
                            }else if (response.getString("message").equals("null")){
                                Toast.makeText(LoadingActivity.this, "خطایی پیش آمده!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoadingActivity.this, "اتصال خود به اینترنت را برسی کنید!", Toast.LENGTH_SHORT).show();
                    }
                });

                request.setRetryPolicy(new DefaultRetryPolicy(18000 , DefaultRetryPolicy.DEFAULT_MAX_RETRIES , DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                Volley.newRequestQueue(LoadingActivity.this).add(request);


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

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
