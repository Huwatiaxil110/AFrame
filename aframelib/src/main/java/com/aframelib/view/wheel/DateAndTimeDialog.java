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

public class DateAndTimeDialog extends Dialog implements View.OnClickListener{
    WheelCallback mWheelCallback;
    View root;
    WheelPicker mPickerYear, mPickerMonth, mPickerDay, mPickerHour, mPickerMinute;
    Button btnSure, btnCancel;
    int year, month, day, hour, minute;

    public DateAndTimeDialog(Context context) {
        this(context, 0);
    }

    public DateAndTimeDialog(Context context, int themeResId) {
        super(context, themeResId);

        setCanceledOnTouchOutside(true);
        root = LayoutInflater.from(context).inflate(R.layout.diy_wheel_date_time, null);
        mPickerYear = (WheelPicker) root.findViewById(R.id.diy_wheel_year);
        mPickerMonth = (WheelPicker) root.findViewById(R.id.diy_wheel_month);
        mPickerDay = (WheelPicker) root.findViewById(R.id.diy_wheel_day);
        mPickerHour = (WheelPicker) root.findViewById(R.id.diy_wheel_hour);
        mPickerMinute = (WheelPicker) root.findViewById(R.id.diy_wheel_minute);

        mPickerYear.setOnWheelChangeListener(new DateAndTimeOnWheelChangeListener(1));
        mPickerMonth.setOnWheelChangeListener(new DateAndTimeOnWheelChangeListener(2));
        mPickerDay.setOnWheelChangeListener(new DateAndTimeOnWheelChangeListener(3));
        mPickerHour.setOnWheelChangeListener(new DateAndTimeOnWheelChangeListener(4));
        mPickerMinute.setOnWheelChangeListener(new DateAndTimeOnWheelChangeListener(5));

        btnSure = (Button) root.findViewById(R.id.btn_sure);
        btnSure.setTag(WheelCallback.TAG_SURE);
        btnCancel = (Button) root.findViewById(R.id.btn_cancel);
        btnCancel.setTag(WheelCallback.TAG_CANCEL);

        btnSure.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        setContentView(root);
    }

    class DateAndTimeOnWheelChangeListener implements WheelPicker.OnWheelChangeListener {
        int type;

        public DateAndTimeOnWheelChangeListener(int type) {
            this.type = type;
        }

        @Override
        public void onWheelScrolling() {

        }

        @Override
        public void onWheelSelected(int index, String data) {
            switch (type){
                case 1:     //年
                    year = index;
                    break;
                case 2:     //月
                    month = index;
                    break;
                case 3:     //日
                    day = index;
                    break;
                case 4:     //时
                    hour = index;
                    break;
                case 5:     //分
                    minute = index;
                    break;
            }
        }

        @Override
        public void onWheelScrollStateChanged(int state) {
            if (state == WheelPicker.SCROLL_STATE_IDLE) {
                btnSure.setEnabled(true);
            } else {
                btnSure.setEnabled(false);
            }
        }
    }

    @Override
    public void onClick(View v) {
        int tag = (int) v.getTag();
        switch (tag){
            case WheelCallback.TAG_SURE:
//                Toast.makeText(getContext(), index+"", Toast.LENGTH_SHORT).show();
//                mWheelCallback.selected(index);
                int[] times = new int[5];
                times[0] = year;
                times[1] = month;
                times[2] = day;
                times[3] = hour;
                times[4] = minute;

                mWheelCallback.selected(0, times);
                dismiss();
                break;
            case WheelCallback.TAG_CANCEL:
                dismiss();
                break;
        }
    }

    public void setIndex(int yearIndex, int monthIndex, int dayIndex, int hourIndex, int minuteIndex){
        mPickerYear.setItemIndex(yearIndex);
        mPickerMonth.setItemIndex(monthIndex);
        mPickerDay.setItemIndex(dayIndex);
        mPickerHour.setItemIndex(hourIndex);
        mPickerMinute.setItemIndex(minuteIndex);
    }

    public void setDatas(ArrayList<String> years, ArrayList<String> months, ArrayList<String> days,
                         ArrayList<String> hours, ArrayList<String> minutes){
        mPickerYear.setData(years);
        mPickerMonth.setData(months);
        mPickerDay.setData(days);
        mPickerHour.setData(hours);
        mPickerMinute.setData(minutes);
    }

    public void setmWheelCallback(WheelCallback mWheelCallback) {
        this.mWheelCallback = mWheelCallback;
    }

}
