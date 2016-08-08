package com.example.lz.sdl.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.lz.sdl.SdlApplication;
import com.example.lz.sdl.message.InfoData;

/**
 * Created by liz on 16-8-8.
 */
public class SharedPreferenceHelper implements SharedPreferences.OnSharedPreferenceChangeListener {
    private static final SharedPreferenceHelper instance;

    static {
        instance = new SharedPreferenceHelper();
    }

    public static SharedPreferenceHelper getInstance() {
        return instance;
    }

    private SharedPreferenceHelper(){
        getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    private static SharedPreferences getSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(SdlApplication.getInstance());
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

    }

    private static String getString(String key, String def) {
        return getSharedPreferences().getString(key, def);
    }

    private static void setString(String key, String value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(key, value);
        editor.commit();

    }

    public static void saveCacheData(InfoData data){
        setString("name",data.getName());
        setString("desc",data.getDescription());
        setString("imgs",data.getImgs());
    }

    public static InfoData getCacheData(){
        InfoData infoData = new InfoData();
        infoData.setName(getString("name",""));
        infoData.setDescription(getString("desc",""));
        infoData.setImgs(getString("imgs",""));
        return infoData;
    }
}
