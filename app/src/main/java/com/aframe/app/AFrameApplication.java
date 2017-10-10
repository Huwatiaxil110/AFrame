package com.aframe.app;

import android.app.Application;

import com.aframe.BuildConfig;
import com.aframelib.util.log.L;
import com.aframelib.util.log.LAdapter;

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
        L.initLAdapter(new LAdapter("TAG", BuildConfig.DEBUG, true));
    }
}
