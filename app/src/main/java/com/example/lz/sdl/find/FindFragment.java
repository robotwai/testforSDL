package com.example.lz.sdl.find;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lz.sdl.R;
import com.example.lz.sdl.message.InfoData;
import com.example.lz.sdl.utils.DatabaseHelper;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by liz on 16-8-8.
 */
public class FindFragment extends Fragment {

    private RecyclerView lv_find;
    private FindAdapter findAdapter;


    LinearLayoutManager linearLayoutManager;

    public static FindFragment newInstance() {
        FindFragment fragment = new FindFragment();

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.find_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lv_find = (RecyclerView) view.findViewById(R.id.lv_find);
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        lv_find.setLayoutManager(linearLayoutManager);
        lv_find.addItemDecoration(new DividerItemDecoration(
                getActivity(), DividerItemDecoration.VERTICAL_LIST));
        initData();

    }

    public void initAdapter(List<InfoData> list) {
        findAdapter = new FindAdapter(list, getContext());
        lv_find.setAdapter(findAdapter);
    }

    public void initData() {
        DatabaseHelper helper = DatabaseHelper.getHelper(getContext());
        try {

            List<InfoData> ls = helper.getUserDao().queryForAll();
            initAdapter(ls);
            Log.i("lz", ls.toString());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
