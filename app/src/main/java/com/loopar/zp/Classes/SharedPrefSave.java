package com.loopar.zp.Classes;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefSave {

    private SharedPreferences sp;

    public SharedPrefSave(Context context) {
        sp=context.getSharedPreferences("sp",Context.MODE_PRIVATE);
    }

    public void save(String key , String value){
        sp.edit().putString(key , value).apply();
    }

    public void save(String key , int value){
        sp.edit().putInt(key , value).apply();
    }

    public void save(String key , boolean value){
        sp.edit().putBoolean(key , value).apply();
    }

    public void save(String key , long value){
        sp.edit().putLong(key , value).apply();
    }

    public String  load(String key , String def){
        return sp.getString(key,def);
    }

    public int load(String key , int def){
        return sp.getInt(key,def);
    }

    public boolean load(String key , boolean def){
        return sp.getBoolean(key , def);
    }

    public long load(String key , long def){
        return sp.getLong(key,def);
    }
}
