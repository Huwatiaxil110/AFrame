package com.aframe.activity.rxjava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aframe.R;
import com.aframe.adapter.RxJavaAdapter;
import com.aframelib.util.log.L;
import com.aframelib.view.decoration.LinearLayoutItemDecoration;

import java.util.ArrayList;

/**
 * Created by zc on 2017/10/11.
 */

public class RxJavaActivity extends AppCompatActivity{
    RecyclerView rvShow;
    ArrayList<String> mItems;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);

        initDatas();
        initViews();
    }

    private void initDatas(){
        mItems = new ArrayList<>();
        mItems.add("0");
        mItems.add("1");
        mItems.add("2");
        mItems.add("3");
    }

    private void initViews(){
        rvShow = (RecyclerView) findViewById(R.id.rv_items);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvShow.setLayoutManager(mLayoutManager);

        RxJavaAdapter rvAdapter = new RxJavaAdapter(this, mItems);
        rvAdapter.setmItemCallback(new RxJavaAdapter.ItemCallback() {
            @Override
            public void onItemClick(int position) {
                L.e("position = " + position);
            }
        });
        rvShow.setAdapter(rvAdapter);

        LinearLayoutItemDecoration mItemDecoration2 = new LinearLayoutItemDecoration(this, LinearLayoutItemDecoration.VERTICAL_LIST, R.drawable.shape_rv, false);
        rvShow.addItemDecoration(mItemDecoration2);
        rvShow.setItemAnimator(new DefaultItemAnimator());
    }

}




























