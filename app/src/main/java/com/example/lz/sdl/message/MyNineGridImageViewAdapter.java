package com.example.lz.sdl.message;

import android.content.Context;
import android.widget.ImageView;

import com.jaeger.ninegridimageview.NineGridImageViewAdapter;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

/**
 * Created by liz on 16-8-8.
 */
public class MyNineGridImageViewAdapter extends NineGridImageViewAdapter<String> {
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
}
