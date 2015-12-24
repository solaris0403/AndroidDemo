package com.tony.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

/**
 * Created by user on 12/23/15.
 */
public class MyView extends View implements GestureDetector.OnGestureListener{
    private GestureDetector gestureDetector;
    private Scroller mScroller;
    // 分别记录上次滑动的坐标
    private int mLastX = 0;
    private int mLastY = 0;
    public MyView(Context context) {
        super(context);
        init();
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        gestureDetector = new GestureDetector(getContext(), this);
//        gestureDetector.setIsLongpressEnabled(false);
        init();
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mScroller = new Scroller(getContext());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
//        VelocityTracker tracker = VelocityTracker.obtain();
//        tracker.addMovement(event);
//        tracker.computeCurrentVelocity(1000);
//        int x = (int) tracker.getXVelocity();
//        int y = (int) tracker.getYVelocity();
//        Log.e("VelocityTracker", "x:"+x+"--y:"+y);
//        tracker.clear();
//        tracker.recycle();

//        return gestureDetector.onTouchEvent(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (!mScroller.isFinished()){
                    mScroller.abortAnimation();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                scrollBy(-deltaX, 0);
                break;
            case MotionEvent.ACTION_UP:
                int scrollX = getScrollX();

                break;
        }
        mLastX = x;
        mLastY = y;
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        Log.e("GestureDetector", "onDown");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.e("GestureDetector", "onShowPress");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.e("GestureDetector", "onSingleTapUp");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.e("GestureDetector", "onScroll");
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.e("GestureDetector", "onLongPress");
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.e("GestureDetector", "onFling");
        return false;
    }

//    @Override
//    public boolean onSingleTapConfirmed(MotionEvent e) {
//        Log.e("GestureDetector", "GestureDetector");
//        return false;
//    }
//
//    @Override
//    public boolean onDoubleTap(MotionEvent e) {
//        Log.e("GestureDetector", "onDoubleTap");
//        return false;
//    }
//
//    @Override
//    public boolean onDoubleTapEvent(MotionEvent e) {
//        Log.e("GestureDetector", "onDoubleTapEvent");
//        return false;
//    }
}
