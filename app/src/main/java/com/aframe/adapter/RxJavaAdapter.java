package com.aframe.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aframe.R;
import com.aframelib.util.log.L;

import java.util.ArrayList;

/**
 * Created by zc on 2017/10/11.
 */

public class RxJavaAdapter extends RecyclerView.Adapter<RxJavaAdapter.RxJavaHolderView>{
    private Context mContext;
    private ArrayList<String> mList;
    private ItemCallback mItemCallback;

    public RxJavaAdapter(Context mContext, ArrayList<String> mList) {
        this.mContext = mContext;
        if(mList == null) mList = new ArrayList<>();
        this.mList = mList;
    }

    @Override
    public RxJavaHolderView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_rxjava, parent, false);
        return new RxJavaHolderView(view);
    }

    @Override
    public void onBindViewHolder(final RxJavaHolderView holder, int position) {
        holder.tvRxJava.setText(mList.get(position));

        holder.tvRxJava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemCallback.onItemClick(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class RxJavaHolderView extends RecyclerView.ViewHolder{
        TextView tvRxJava;

        public RxJavaHolderView(View itemView) {
            super(itemView);
            initViews();
        }

        private void initViews(){
            tvRxJava = (TextView) itemView.findViewById(R.id.item_tv_rxjava);
        }
    }

    public void setmItemCallback(ItemCallback mItemCallback) {
        this.mItemCallback = mItemCallback;
    }

    public interface ItemCallback{
        void onItemClick(int position);
    }

    public void updateRxJavaAdapter(ArrayList<String> mList){
        this.mList = mList;
        notifyDataSetChanged();
    }
}
