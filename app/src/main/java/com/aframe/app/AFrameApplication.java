package com.aframe.app;

import android.app.Application;
import com.aframelib.util.L;

/**
 * Created by zc on 2017/10/9.
 */

public class AFrameApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        initLog();
    }

    private void initLog(){
        L.initDebug(com.aframe.BuildConfig.DEBUG);
    }
}
