package com.loopar.zp.Classes;

import android.app.Application;
import android.content.Context;

import com.loopar.zp.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class AppConfig extends Application {

    public static final String URL_LOGIN="";
    public static final String URL_SIGNUP="";
    public static final String URL_GETSITE="";
    public static final String URL_GETUSER="";
    public static final String URL_SENDSCORE="";

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();

        CalligraphyConfig
                .initDefault(new CalligraphyConfig.Builder()
                .setFontAttrId(R.attr.fontPath)
                .setDefaultFontPath("fonts/irsans.ttf").build());
    }

    public static Context getContext(){
        return context;
    }
}
