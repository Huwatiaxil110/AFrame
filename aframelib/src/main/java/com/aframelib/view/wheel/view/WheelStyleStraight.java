package com.aframelib.view.wheel.view;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

/**
 * 滚轮默认直的样式实现
 * Default style implement of WheelView in Straight
 *
 * @author AigeStudio 2015-12-03
 * @author AigeStudio 2015-12-08
 * @version 1.0.0 beta
 */
class WheelStyleStraight extends AbstractWheelStyle {

    WheelStyleStraight(WheelView view) {
        super(view);
    }

    @Override
    void computeWheel() {
        width = direction.computeStraightWidth(view.itemCount, view.itemSpace, maxTextWidth,
                maxTextHeight);
        height = direction.computeStraightHeight(view.itemCount, view.itemSpace, maxTextWidth,
                maxTextHeight);
        unit = direction.computeUnitStraight(width, height, view.itemCount);
        unitDisplayMin = -direction.getUnitDisplayRule(width, height);
        unitDisplayMax = -unitDisplayMin;
    }

    @Override
    void drawItems(Canvas canvas) {
        for (int i = -view.itemIndex; i < view.data.size() - view.itemIndex; i++) {
            int curUnit = unit * i;
            curUnit += (unitTotalMove + distanceSingleMove);
            if (curUnit > unitDisplayMax || curUnit < unitDisplayMin) {
                continue;
            }

            canvas.save();
            Log.e("TAG", "curUnit = "+curUnit);
            Log.e("TAG", "unitDisplayMax = "+unitDisplayMax);
            paint.setAlpha(255 - 255 * Math.abs(curUnit) / unitDisplayMax);
//            paint.setTextSize(view.textSize * (1-(Math.abs(curUnit)/53*0.2f)));
            canvas.drawText(view.data.get(i + view.itemIndex), centerX,
                    centerTextY + curUnit, paint);
            canvas.restore();
        }
    }

    @Override
    void onTouchEventMove(MotionEvent event) {
        distanceSingleMove += (direction.getCurrentPoint(event) - lastPoint);
        if (Math.abs(distanceSingleMove) < WheelCons.TOUCH_DISTANCE_MINIMUM) return;
        if (Math.abs(distanceSingleMove) >= width) return;
        lastPoint = direction.getCurrentPoint(event);
    }

    @Override
    void onTouchEventUp(MotionEvent event) {
        if (Math.abs(distanceSingleMove) < WheelCons.TOUCH_DISTANCE_MINIMUM) return;
        isScrollingTerminal = true;
        unitTotalMove += distanceSingleMove;
        distanceSingleMove = 0;
        if (checkScrollState()) return;
        computeCurrentVelocity();
        direction.startFling(scroller, tracker, unitTotalMove, unitMoveMin, unitMoveMax);
        finalUnit = direction.getFinal(scroller);
    }
}