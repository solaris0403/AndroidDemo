package com.tony.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Scroller;
import android.widget.TextView;

/**
 * Created by user on 12/23/15.
 */
public class ScrollByView extends TextView {
    private Paint mPaint;
    private Scroller mScroller;
    public ScrollByView(Context context) {
        super(context);
        init();
    }

    public ScrollByView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ScrollByView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mScroller = new Scroller(getContext());
        setBackgroundColor(Color.RED);
        setText("222222222");
//        mPaint = new Paint();
//        mPaint.setColor(Color.RED);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    int mLastX;
    int mLastY;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!mScroller.isFinished()){
                    mScroller.abortAnimation();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                scrollBy(-deltaX, -deltaY);
                break;
            case MotionEvent.ACTION_UP:
                int scrollX = getScrollX();
                int scrollY = getScrollY();
                int dx = 200 - scrollX;
                int dy = 200 - scrollY;
                smoothScrollBy(dx, dy);
                break;
        }
        mLastX = x;
        mLastY = y;
        return true;
    }

    private void smoothScrollBy(int dx, int dy){
        mScroller.startScroll(getScrollX(), 0, dx, dy, 500);
        invalidate();
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()){
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }
}
