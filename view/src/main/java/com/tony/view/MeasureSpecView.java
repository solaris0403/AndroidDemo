package com.tony.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Created by user on 12/24/15.
 */
public class MeasureSpecView extends ViewGroup{
    public MeasureSpecView(Context context) {
        super(context);
    }

    public MeasureSpecView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MeasureSpecView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
