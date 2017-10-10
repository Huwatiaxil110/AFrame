package com.aframelib.util.toast;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.aframelib.R;

/**
 * 弹长时间Toast，时长为2个3500ms
 * 如果设置无限长时间，会每两次就出现一个淡入淡出
 * 重复次数过多，就不再弹出Toast
 * Created by zc on 2017/7/5.
 */

public class LongToast{
    private static Handler handler;
    private Toast toast;
    boolean isFirst;

    public LongToast(final Context context){
        handler = new Handler(context.getMainLooper()){
            @Override
            public void handleMessage(Message msg){
                switch(msg.what){
                    case 0x11:
                        toast.show();
                        if(!isFirst){
                            isFirst = true;
                            handler.sendEmptyMessageDelayed(0x11, 3000);
                        }
                        break;
                    case 0x12:

                        break;
                }
            }
        };
    }

    private void setToast(Toast toast){
        this.toast = toast;
    }

    public static LongToast makeText(Context context, String text, boolean isCenter){
        Toast tempToast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        if(isCenter){
            tempToast.setGravity(Gravity.CENTER, 0, 0);     //正中心
            tempToast.setMargin(0, -0.1f);                  //正中心往上
        }

        LongToast st = new LongToast(context);
        st.setToast(tempToast);

        return st;
    }

    public static LongToast makeTextWhite(Context context, String text, boolean isCenter, boolean isWhite){
        Toast tempToast = null;

        if(!isWhite){
            tempToast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        }else{
            tempToast = new Toast(context);
            tempToast.setDuration(Toast.LENGTH_LONG);

            View view = LayoutInflater.from(context).inflate(R.layout.toast_item, null);
            TextView tvContent = (TextView) view.findViewById(R.id.toast_message);
            tvContent.setText(text);

            tempToast.setView(view);
        }

        if(isCenter){
            tempToast.setGravity(Gravity.CENTER, 0, 0);     //正中心
            tempToast.setMargin(0, -0.1f);                  //正中心往上
        }

        LongToast st = new LongToast(context);
        st.setToast(tempToast);

        return st;
    }

    public void show(){
        handler.sendEmptyMessage(0x11);
    }
}
