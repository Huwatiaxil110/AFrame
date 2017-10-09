package com.aframelib.view.wheel;

/**
 * Created by zc/2/28.
 */

public interface WheelCallback {
    public static final int TAG_SURE = 101;
    public static final int TAG_CANCEL = 102;
    public void selected(int index, int[] datas);
}
