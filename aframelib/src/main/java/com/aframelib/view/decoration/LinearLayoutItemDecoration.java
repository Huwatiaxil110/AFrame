package com.aframelib.view.decoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by zc on 2017/7/14.
 */

public class LinearLayoutItemDecoration extends RecyclerView.ItemDecoration{
    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;

    private Drawable mDivider;          //分割线
    private int mOrientation;

    private boolean isSaveLastDivider;  //是否保留最后一个分割线

    public LinearLayoutItemDecoration(Context context, int orientation, int drawableResID, boolean isSaveLastDivider) {
        this.isSaveLastDivider = isSaveLastDivider;
        mDivider = context.getResources().getDrawable(drawableResID);
        setOrientation(orientation);
    }

    public void setOrientation(int orientation) {
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
            throw new IllegalArgumentException("invalid orientation");
        }
        mOrientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent) {
        if (mOrientation == VERTICAL_LIST) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }
    }

    public void drawVertical(Canvas c, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            RecyclerView v = new RecyclerView(parent.getContext());
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    public void drawHorizontal(Canvas c, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getRight() + params.rightMargin;
            final int right = left + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        LinearLayoutManager mManager = (LinearLayoutManager) parent.getLayoutManager();
        int count = mManager.getItemCount();

        if (mOrientation == VERTICAL_LIST) {
            if(isSaveLastDivider){
                outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
            }else{
                if(itemPosition != count-1){
                    outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
                }
            }
        } else {
            if(isSaveLastDivider){
                outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
            }else{
                if(itemPosition != count-1){        //横向最后一个不划分割线
                    outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
                }
            }
        }
    }



}
