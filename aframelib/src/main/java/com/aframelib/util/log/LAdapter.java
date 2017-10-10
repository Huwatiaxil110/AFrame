package com.aframelib.util.log;

/**
 * Created by zc on 2017/10/10.
 */

public class LAdapter {
    private boolean isDebug;
    private boolean isNeedOtherInfo;

    public LAdapter(boolean isDebug, boolean isNeedOtherInfo) {
        this.isDebug = isDebug;
        this.isNeedOtherInfo = isNeedOtherInfo;
    }

    public boolean isDebug() {
        return isDebug;
    }

    public boolean isNeedOtherInfo() {
        return isNeedOtherInfo;
    }
}
