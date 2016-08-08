package com.example.lz.sdl.message;

import android.content.Context;

import com.example.lz.sdl.utils.DatabaseHelper;
import com.example.lz.sdl.utils.SharedPreferenceHelper;

import java.sql.SQLException;

/**
 * Created by liz on 16-8-8.
 */
public class InfoPresenter implements InfoContract.ActionsListener {

    private InfoContract.View mView;

    public InfoPresenter(InfoContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void saveInfo(Context context, InfoData infoData) {
        InfoData u1 = new InfoData(infoData.getName(), infoData.getDescription(),infoData.getImgs());
        DatabaseHelper helper = DatabaseHelper.getHelper(context);
        try
        {
            helper.getUserDao().create(u1);

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public InfoData getCache() {
        return SharedPreferenceHelper.getCacheData();
    }

    @Override
    public void saveCache(InfoData infoData) {
        try {
            if (infoData.getName().equals("")&&infoData.getImgs().equals("")&&infoData.getDescription().equals("")){

            }else {
                SharedPreferenceHelper.saveCacheData(infoData);
            }
        }catch (Exception e){
            e.printStackTrace();
        }



    }


}
