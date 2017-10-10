package com.aframelib.util.toast;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 实现任意时间显示Toast的View
 *
 * 用WindowManager来添加
 * 但是需要注意，当前页面消失时，需要先移除
 */
public class CustomToast{
    private WindowManager wdm;
    private double time;
    private View mView;
    private WindowManager.LayoutParams params;
    private Timer timer;

    private CustomToast(Context context, String text, double time){
        wdm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        timer = new Timer();

        Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        mView = toast.getView();

        params = new WindowManager.LayoutParams();
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.format = PixelFormat.TRANSLUCENT;
        params.windowAnimations = Animation.INFINITE;
        params.type = WindowManager.LayoutParams.TYPE_TOAST;
        params.setTitle("Toast");
        params.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
        params.gravity = Gravity.CENTER;
//        params.gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
        params.y = -30;

        this.time = time;
    }

    public static CustomToast makeText(Context context, String text, double time){
        CustomToast toastCustom = new CustomToast(context, text, time);
        return toastCustom;
    }

    public void show(){
        wdm.addView(mView, params);
        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                try{
                    wdm.removeView(mView);
                }catch(Exception e){
                    e.printStackTrace();
                }finally{
                    timer.cancel();
                }
            }
        }, (long)(time * 1000L));
    }

    public void cancel(){
        wdm.removeView(mView);
        timer.cancel();
    }


}