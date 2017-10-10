package com.aframe.activity.toast;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.aframe.R;
import com.aframe.adapter.ToastAdapter;
import com.aframelib.util.toast.CustomToast;
import com.aframelib.util.toast.T;

import java.util.ArrayList;

/**
 * Created by zc on 2017/7/5.
 */

public class ToastActivity extends AppCompatActivity{
    ListView lvItems;
    ArrayList<String> mItems;
    ToastAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);

        initDatas();
        initViews();
    }

    class ListClickListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
            Intent intent = null;
            switch(position){
                case 0:
                    CustomToast.makeText(ToastActivity.this, mItems.get(position), 7).show();
                    break;
                case 1:
                    T.showWhiteToastServen(ToastActivity.this, mItems.get(position), false);
                    break;
                case 2:
                    T.showBlackToastServen(ToastActivity.this, mItems.get(position), false);
                    break;
                case 3:
                    T.showWhiteToastServen(ToastActivity.this, mItems.get(position), true);
                    break;
                case 4:
                    T.showBlackToastServen(ToastActivity.this, mItems.get(position), true);
                    break;
                case 5:
                    T.showWhiteToast(ToastActivity.this, mItems.get(position), Toast.LENGTH_LONG);
                    break;
                case 6:
                    T.showBlackToast(ToastActivity.this, mItems.get(position), Toast.LENGTH_SHORT);
                    break;
                case 7:
                    T.showWhiteToastCenter(ToastActivity.this, mItems.get(position), Toast.LENGTH_LONG);
                    break;
                case 8:
                    T.showBlackToastCenter(ToastActivity.this, mItems.get(position), Toast.LENGTH_LONG);
                    break;

                case 9:
                    T.showWhiteToast(ToastActivity.this, mItems.get(position), Toast.LENGTH_SHORT);
                    break;
                case 10:
                    T.showToast(ToastActivity.this, mItems.get(position));
                    break;
                case 11:
                    T.showWhiteToastCenter(ToastActivity.this, mItems.get(position), Toast.LENGTH_SHORT);
                    break;
                case 12:
                    T.showBlackToastCenter(ToastActivity.this, mItems.get(position), Toast.LENGTH_SHORT);
                    break;
            }
        }
    }

    private void initViews(){
        lvItems = (ListView) findViewById(R.id.lv_items);
        mAdapter = new ToastAdapter(ToastActivity.this, mItems);
        lvItems.setAdapter(mAdapter);

        lvItems.setOnItemClickListener(new ListClickListener());
    }

    private void initDatas(){
        mItems = new ArrayList<>();
        mItems.add("窗口Toast(7s)");

        mItems.add("长Toast（白色/7s）");
        mItems.add("长Toast（默认色/7s）");
        mItems.add("长Toast（白色/7s/中间）");
        mItems.add("长Toast（默认色/7s/中间）");

        mItems.add("长Toast（白色/3.5s）");
        mItems.add("长Toast（默认色/3.5s）");
        mItems.add("长Toast（白色/3.5s/中间）");
        mItems.add("长Toast（默认色/3.5s/中间）");

        mItems.add("短Toast（白色/2s）");
        mItems.add("短Toast（默认色/2s）");
        mItems.add("短Toast（白色/2s/中间）");
        mItems.add("短Toast（默认色/2s/中间）");
    }
}

































