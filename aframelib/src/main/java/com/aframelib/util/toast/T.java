package com.aframelib.util.toast;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.aframelib.R;

/**
 * Toast工具
 * Created by zc on 2017/7/4.
 */

public class T {
    private static final boolean ISDEBUG = true;

    public static void showToast(Context mContext, String content){
        if(ISDEBUG){
            Toast.makeText(mContext, content, Toast.LENGTH_SHORT).show();
        }
    }

    public static void showToastLong(Context mContext, String content){
        if(ISDEBUG){
            Toast.makeText(mContext, content, Toast.LENGTH_LONG).show();
        }
    }

    public static void showToastShort(Context mContext, String content){
        if(ISDEBUG){
            Toast.makeText(mContext, content, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 白色Toast 中上位置显示
     * @param content   内容
     * @param duration  Toast.LENGTH_SHORT 与 Toast.LENGTH_LONG
     */
    public static void showWhiteToastCenter(Context mContext, String content, int duration){
        Toast tempToast = new Toast(mContext);
        tempToast.setDuration(duration);

        View view = LayoutInflater.from(mContext).inflate(R.layout.toast_item, null);
        TextView tvContent = (TextView) view.findViewById(R.id.toast_message);
        tvContent.setText(content);

        tempToast.setView(view);

        tempToast.setGravity(Gravity.CENTER, 0, 0);     //正中心
        tempToast.setMargin(0, -0.1f);                  //正中心往上

        tempToast.show();
    }

    /**
     * 白色Toast
     * @param content   内容
     * @param duration  Toast.LENGTH_SHORT 与 Toast.LENGTH_LONG
     */
    public static void showWhiteToast(Context mContext, String content, int duration){
        Toast tempToast = new Toast(mContext);
        tempToast.setDuration(duration);

        View view = LayoutInflater.from(mContext).inflate(R.layout.toast_item, null);
        TextView tvContent = (TextView) view.findViewById(R.id.toast_message);
        tvContent.setText(content);

        tempToast.setView(view);

        tempToast.show();
    }

    /** 白色Toast 中上位置显示(时间长3.5*2s)*/
    public static void showWhiteToastServen(Context mContext, String content, boolean isCenter){
        LongToast.makeTextWhite(mContext, content, isCenter, true).show();
    }

    /**
     * 默认Toast 中上位置显示
     * @param content   内容
     * @param duration  Toast.LENGTH_SHORT 与 Toast.LENGTH_LONG
     */
    public static void showBlackToastCenter(Context mContext, String content, int duration){
        Toast tempToast = Toast.makeText(mContext, content, duration);

        tempToast.setGravity(Gravity.CENTER, 0, 0);     //正中心
        tempToast.setMargin(0, -0.1f);                  //正中心往上

        tempToast.show();
    }

    /**
     * 默认Toast
     * @param content   内容
     * @param duration  Toast.LENGTH_SHORT 与 Toast.LENGTH_LONG
     */
    public static void showBlackToast(Context mContext, String content, int duration){
        Toast tempToast = Toast.makeText(mContext, content, duration);

        tempToast.show();
    }

    /** 默认Toast 中上位置显示(时间长3.5s*2) */
    public static void showBlackToastServen(Context mContext, String content, boolean isCenter){
        LongToast.makeTextWhite(mContext, content, isCenter, false).show();
    }


}
