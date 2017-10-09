package com.aframelib.view.wheel;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.aframelib.R;
import com.aframelib.view.wheel.view.WheelPicker;

import java.util.ArrayList;

/**
 * Created by zc on 2017/2/28.
 */

public class PeopleDialog extends Dialog implements View.OnClickListener{
    WheelCallback mWheelCallback;
    View root;
    WheelPicker mPicker;
    Button btnSure, btnCancel;
    int index = 0;

    public PeopleDialog(Context context) {
        this(context, 0);
    }

    public PeopleDialog(Context context, int themeResId) {
        super(context, themeResId);

        setCanceledOnTouchOutside(true);
        root = LayoutInflater.from(context).inflate(R.layout.diy_wheel_people, null);
        mPicker = (WheelPicker) root.findViewById(R.id.diy_wheel);
        mPicker.setOnWheelChangeListener(new WheelPicker.OnWheelChangeListener() {
            @Override
            public void onWheelScrolling() {

            }

            @Override
            public void onWheelSelected(int index, String data) {
                PeopleDialog.this.index = index;
            }

            @Override
            public void onWheelScrollStateChanged(int state) {
                if (state == WheelPicker.SCROLL_STATE_IDLE) {
                    btnSure.setEnabled(true);
                } else {
                    btnSure.setEnabled(false);
                }
            }
        });

        btnSure = (Button) root.findViewById(R.id.btn_sure);
        btnSure.setTag(WheelCallback.TAG_SURE);
        btnCancel = (Button) root.findViewById(R.id.btn_cancel);
        btnCancel.setTag(WheelCallback.TAG_CANCEL);

        btnSure.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        setContentView(root);
    }

    @Override
    public void onClick(View v) {
        int tag = (int) v.getTag();
        switch (tag){
            case WheelCallback.TAG_SURE:
//                Toast.makeText(getContext(), index+"", Toast.LENGTH_SHORT).show();
                mWheelCallback.selected(index, null);
                dismiss();
                break;
            case WheelCallback.TAG_CANCEL:
                dismiss();
                break;
        }
    }

    public void setDatas(ArrayList<String> datas){
        mPicker.setData(datas);
    }

    public void setmWheelCallback(WheelCallback mWheelCallback) {
        this.mWheelCallback = mWheelCallback;
    }
}
