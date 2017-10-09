package com.aframelib.util;

import android.util.Log;

/**
 * 打印工具
 * Created by zc on 2017/7/4.
 */
public class L{
    private static boolean DEBUG = false;

    public static void initDebug(boolean isDebug){
        DEBUG = isDebug;
    }

    public static void e(String tag, String content){
        if(DEBUG){
            Log.e(tag, getDescription() + content);
        }else{
            Log.e(tag, "----");
        }
    }

    public static void i(String tag, String content){
        if(DEBUG){
            Log.i(tag, getDescription() + content);
        }
    }

    public static void d(String tag, String content){
        if(DEBUG){
            Log.d(tag, getDescription() + content);
        }
    }

    public static void v(String tag, String content){
        if(DEBUG){
            Log.v(tag, getDescription() + content);
        }
    }

    public static void w(String tag, String content){
        if(DEBUG){
            Log.w(tag, getDescription() + content);
        }
    }

    /**
     * 获取打印信息所在方法名，行号等信息
     */
    private static String[] getCNMNLN() {
        String[] infos = new String[]{ "", "", "" };
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        if (elements.length >= 5) {
            infos[0] = elements[4].getClassName();
            infos[1] = elements[4].getMethodName();
            infos[2] = "line = " + elements[4].getLineNumber();
        } else {
            Log.e("MyLogger", "Stack is too shallow!!!");
        }

        return infos;
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
            Log.e("MyLogger", "Stack is too shallow!!!");
        }

        return infos[0] + " -> " + infos[1] + "() -> " + infos[2] + "\n";
    }

}
