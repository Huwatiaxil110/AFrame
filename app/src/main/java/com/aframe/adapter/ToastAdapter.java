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
 * Created by zc on 2017/7/5.
 */

public class ToastAdapter extends BaseAdapter{
    private Context mContext;
    private ArrayList<String> mList;

    public ToastAdapter(Context mContext, ArrayList<String> mList){
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount(){
        return mList.size();
    }

    @Override
    public String getItem(int position){
        return mList.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ToastHolder mHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_main, null);

            mHolder = new ToastHolder();
            mHolder.tvItem = (TextView) convertView.findViewById(R.id.main_tv_item);

            convertView.setTag(mHolder);
        }else{
            mHolder = (ToastHolder) convertView.getTag();
        }

        if(position%2 == 0){
            mHolder.tvItem.setBackgroundColor(0xffaaaaaa);
            mHolder.tvItem.setTextColor(0xff000000);
        }else{
            mHolder.tvItem.setBackgroundColor(0xffaaaaaa);
            mHolder.tvItem.setTextColor(0xff000000);
        }
        mHolder.tvItem.setText(mList.get(position));

        return convertView;
    }

    private class ToastHolder{
        TextView tvItem;
    }
}
