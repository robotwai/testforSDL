package com.example.lz.sdl.message;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lz.sdl.MainActivity;
import com.example.lz.sdl.R;
import com.example.lz.sdl.utils.DatabaseHelper;
import com.jaeger.ninegridimageview.NineGridImageView;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.nereo.multi_image_selector.MultiImageSelectorActivity;

/**
 * Created by liz on 16-8-8.
 */
public class InfoFragment extends Fragment implements InfoContract.View{
    private final int REQUEST_IMAGE = 10;


    private NineGridImageView<String> nineGridImageView;
    private Button btn_select_pic;
    private Button btn_save;
    private EditText et_name;
    private EditText et_description;


    private InfoPresenter presenter;

    private MainActivity activity;

    private InfoData mData = new InfoData();
    public static InfoFragment newInstance() {
        InfoFragment fragment = new InfoFragment();

        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        presenter = new InfoPresenter(this);
        this.activity = (MainActivity)activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.info_main,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nineGridImageView  = (NineGridImageView<String>) view.findViewById(R.id.ngl_images);
        btn_select_pic = (Button)view.findViewById(R.id.btn_select_pic);
        btn_save = (Button)view.findViewById(R.id.btn_save);
        et_name = (EditText)view.findViewById(R.id.et_name);
        et_description = (EditText)view.findViewById(R.id.et_description);
        nineGridImageView.setAdapter(mAdapter);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mData.setName(et_name.getText().toString());
                mData.setDescription(et_description.getText().toString());
                presenter.saveInfo(getContext(),mData);
                activity.newInfo = true;

            }
        });

        btn_select_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivityForResult(new Intent(getActivity(),MultiImageSelectorActivity.class),REQUEST_IMAGE);
//                DatabaseHelper helper = DatabaseHelper.getHelper(getContext());
//                try
//                {
//
//                    List<InfoData> ls = helper.getUserDao().queryForAll();
//                    Log.i("lz",ls.toString());
//
//                } catch (SQLException e)
//                {
//                    e.printStackTrace();
//                }
            }
        });
        showData();
    }



    @Override
    public void onPause() {
        super.onPause();
        mData.setName(et_name.getText().toString());
        mData.setDescription(et_description.getText().toString());
        presenter.saveCache(mData);
    }

    @Override
    public void showData() {
        try {
            InfoData infoData = presenter.getCache();
            et_name.setText(infoData.getName());
            et_description.setText(infoData.getDescription());
            String[] sss= infoData.getImgs().split(",");
            nineGridImageView.setImagesData(Arrays.asList(sss));
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void showDialog() {

    }

    @Override
    public void hideDialog() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_IMAGE){
            if(resultCode == Activity.RESULT_OK){
                // 获取返回的图片列表
                List<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                // 处理你自己的逻辑 ....
                StringBuffer result = new StringBuffer();
                for (int i=0;i<path.size();i++){
                    result.append(path.get(i));
                    if (i!=path.size()-1){
                        result.append(",");
                    }

                }
                mData.setImgs(result.toString());
                nineGridImageView.setImagesData(path);

            }
        }
    }

    private NineGridImageViewAdapter<String> mAdapter = new NineGridImageViewAdapter<String>() {
        @Override
        protected void onDisplayImage(Context context, ImageView imageView, String photo) {
            Picasso.with(context)
                    .load(new File(photo))
                    .fit()
                    .centerCrop()
                    .into(imageView);
        }

        @Override
        protected ImageView generateImageView(Context context) {
            return super.generateImageView(context);
        }

        @Override
        protected void onItemImageClick(Context context, int index, List<String> photoList) {

        }
    };


}
