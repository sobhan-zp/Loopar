package com.loopar.zp.Classes;

import android.app.Application;
import android.content.Context;

import com.loopar.zp.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class AppConfig extends Application {

    public static final String URL_LOGIN="http://loopaal.com/loop/api/login.php";
    public static final String URL_SIGNUP="http://loopaal.com/loop/api/register.php";
    public static final String URL_GETSITE="http://loopaal.com/loop/api/getSite.php";
    public static final String URL_GETUSER="http://loopaal.com/loop/api/getUser.php";
    public static final String URL_SENDSCORE="http://loopaal.com/loop/api/addmoney.php";

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
