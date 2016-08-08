package com.example.lz.sdl;

import android.app.Application;

/**
 * Created by liz on 16-8-8.
 */
public class SdlApplication extends Application {
    private static SdlApplication instance;
    public static SdlApplication getInstance() {
        if (instance == null) {
            throw new IllegalStateException();
        }
        return instance;
    }

    public SdlApplication(){
        instance  = this;
    }
}
