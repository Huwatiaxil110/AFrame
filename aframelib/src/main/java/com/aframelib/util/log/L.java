package com.aframelib.util.log;

import android.util.Log;

/**
 * 打印工具
 * Created by zc on 2017/7/4.
 */
public class L{
    private static LAdapter mLAdapter;

    public static void initLAdapter(LAdapter mLAdapter) {
        L.mLAdapter = mLAdapter;
    }

    public static void e(String tag, String content){
        if(mLAdapter == null){
            return;
        }
        if(mLAdapter.isDebug()){
            if(mLAdapter.isNeedOtherInfo()){
                Log.e(tag, getDescription() + content);
            }else{
                Log.e(tag, content);
            }
        }
    }

    public static void i(String tag, String content){
        if(mLAdapter == null){
            return;
        }
        if(mLAdapter.isDebug()){
            if(mLAdapter.isNeedOtherInfo()){
                Log.i(tag, getDescription() + content);
            }else{
                Log.i(tag, content);
            }
        }
    }

    public static void d(String tag, String content){
        if(mLAdapter == null){
            return;
        }
        if(mLAdapter.isDebug()){
            if(mLAdapter.isNeedOtherInfo()){
                Log.d(tag, getDescription() + content);
            }else{
                Log.d(tag, content);
            }
        }
    }

    public static void v(String tag, String content){
        if(mLAdapter.isDebug()){
            if(mLAdapter.isNeedOtherInfo()){
                Log.v(tag, getDescription() + content);
            }else{
                Log.v(tag, content);
            }
        }
    }

    public static void w(String tag, String content){
        if(mLAdapter == null){
            return;
        }
        if(mLAdapter.isDebug()){
            if(mLAdapter.isNeedOtherInfo()){
                Log.w(tag, getDescription() + content);
            }else{
                Log.w(tag, content);
            }
        }
    }

    /**
     * 获取打印信息所在方法名，行号等信息
     */
    private static String getDescription() {
        String[] infos = new String[]{ "", "", "" };
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        if (elements.length >= 5) {
//            infos[0] = elements[4].getClassName();      //com.aframe.activity.LaucherActivity$1
            infos[0] = elements[4].getClassName().substring(elements[4].getClassName().lastIndexOf(".") + 1, elements[4].getClassName().length()-2);
            infos[1] = elements[4].getMethodName();
            infos[2] = "line = " + elements[4].getLineNumber();
        } else {
            Log.e("L.java", "Stack is too shallow!!!");
        }

        return infos[0] + " -> " + infos[1] + "() -> " + infos[2] + "\n";
    }

}
