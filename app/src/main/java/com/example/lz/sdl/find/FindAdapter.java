package com.example.lz.sdl.find;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lz.sdl.R;
import com.example.lz.sdl.message.InfoData;
import com.example.lz.sdl.message.MyNineGridImageViewAdapter;
import com.jaeger.ninegridimageview.NineGridImageView;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;

import java.util.Arrays;
import java.util.List;

/**
 * Created by liz on 16-8-8.
 */
public class FindAdapter extends RecyclerView.Adapter<FindAdapter.ItemViewHolder> {

    List<InfoData> list;
    private Context context;

    private InfoData infoData;

    public FindAdapter(List<InfoData> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemViewHolder holder = new ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.ad_find, parent, false));

        return holder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        infoData = list.get(position);

        if (infoData != null) {
            holder.tv_name.setText(infoData.getName());
            holder.tv_description.setText(infoData.getDescription());
            try {
                String[] sss = infoData.getImgs().split(",");
                holder.nineGridImageView.setVisibility(View.VISIBLE);
                holder.nineGridImageView.setImagesData(Arrays.asList(sss));
            } catch (Exception e) {
                e.printStackTrace();
                holder.nineGridImageView.setVisibility(View.GONE);
            }

        }


    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        TextView tv_description;
        NineGridImageView nineGridImageView;
        private NineGridImageViewAdapter<String> mAdapter = new MyNineGridImageViewAdapter();

        public ItemViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_description = (TextView) itemView.findViewById(R.id.tv_description);
            nineGridImageView = (NineGridImageView<String>) itemView.findViewById(R.id.ngl_images);
            nineGridImageView.setAdapter(mAdapter);

        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position, int lastPosition);

    }

}
