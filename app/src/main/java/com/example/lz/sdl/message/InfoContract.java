package com.example.lz.sdl.message;

import android.content.Context;

/**
 * Created by liz on 16-8-8.
 */
public interface InfoContract {
    interface View{
        void showDialog();
        void hideDialog();
        void showData();

    }

    interface ActionsListener{
        void saveInfo(Context context,InfoData infoData);
        void saveCache(InfoData infoData);
        InfoData getCache();
    }
}
