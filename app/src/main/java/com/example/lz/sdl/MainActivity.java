package com.example.lz.sdl;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.lz.sdl.find.FindFragment;
import com.example.lz.sdl.message.InfoFragment;

import java.util.List;

import me.nereo.multi_image_selector.MultiImageSelectorActivity;

public class MainActivity extends AppCompatActivity {

    private final int REQUEST_IMAGE = 10;
    private ViewPager mPager;

    private final int MESSAGE_STATUS =0;

    private final int FIND_STATUS =1;

    private TextView tv_message;

    private TextView tv_find;

    //当前选择的页面
    private int currIndex;

    public static boolean newInfo  = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitTextView();
        InitViewPager();
    }

    public void InitTextView(){
        tv_message = (TextView)findViewById(R.id.tv_message);
        tv_message.setTextColor(Color.parseColor("#00a0c9"));
        tv_find = (TextView)findViewById(R.id.tv_find);
        tv_message.setOnClickListener(new txListener(MESSAGE_STATUS));
        tv_find.setOnClickListener(new txListener(FIND_STATUS));
    }


    public void InitViewPager(){
        mPager = (ViewPager)super.findViewById(R.id.pager);
        mPager.setAdapter(new LoginPagerFragmentAdapter(getSupportFragmentManager()));
        mPager.setCurrentItem(0);
        mPager.addOnPageChangeListener(new MyOnPageChangeListener());
    }


    public class txListener implements View.OnClickListener{
        private int index=0;

        public txListener(int i) {
            index =i;
        }
        @Override
        public void onClick(View v) {
            mPager.setCurrentItem(index);
        }
    }

    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPageSelected(int arg0) {
            // TODO Auto-generated method stub
            currIndex = arg0;
            int i = currIndex + 1;
            changeTitleColor(currIndex);
        }
    }
    public class LoginPagerFragmentAdapter extends FragmentPagerAdapter {
        public LoginPagerFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case MESSAGE_STATUS:
                    return InfoFragment.newInstance();
                case FIND_STATUS:
                    return FindFragment.newInstance();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
    public void changeTitleColor(int isChecked){
        if (isChecked ==0){
            tv_message.setTextColor(Color.parseColor("#00a0c9"));
            tv_find.setTextColor(Color.parseColor("#5f5f5f"));
        }else {
            tv_find.setTextColor(Color.parseColor("#00a0c9"));
            tv_message.setTextColor(Color.parseColor("#5f5f5f"));
        }
    }


}
