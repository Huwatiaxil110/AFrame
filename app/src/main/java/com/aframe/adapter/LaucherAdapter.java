package com.aframe.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aframe.R;

import java.util.ArrayList;

/**
 * Created by zc on 2017/10/9.
 */

public class LaucherAdapter extends BaseAdapter{
    private ArrayList<String> contents;
    private Context mContext;

    public LaucherAdapter(ArrayList<String> contents, Context mContext) {
        if(contents == null){
            contents = new ArrayList<>();
        }
        this.contents = contents;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return contents.size();
    }

    @Override
    public String getItem(int i) {
        return contents.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LaucherViewHolder mViewHolder;
        if(view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.item_laucher, null);

            mViewHolder = new LaucherViewHolder();
            mViewHolder.tvContent = (TextView) view.findViewById(R.id.tv_laucher_item);
            view.setTag(mViewHolder);
        }else{
            mViewHolder = (LaucherViewHolder) view.getTag();
        }
        mViewHolder.tvContent.setText(contents.get(i));

        return view;
    }

    private static class LaucherViewHolder{
        TextView tvContent;
    }
}
