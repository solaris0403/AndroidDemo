package com.tony.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by user on 12/24/15.
 */
public class HeartView extends View {
    private Paint mPaintRect;
    private Paint mPaintCircle;
    private int mWidth, mHeight;

    public HeartView(Context context) {
        super(context);
        init();
    }

    public HeartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HeartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaintRect = new Paint();
        mPaintRect.setColor(Color.RED);
        mPaintCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintCircle.setColor(Color.RED);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mCircelX = mHeight / 4;
        mCircelY = mHeight / 4;
    }

    private int mCircelX;
    private int mCircelY;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mWidth / 2);
        canvas.rotate(45);
        canvas.drawRect(-mWidth / 4, -mHeight / 4, mWidth / 4, mHeight / 4, mPaintRect);

        canvas.drawCircle(0, mCircelY, mHeight / 4, mPaintCircle);
        canvas.drawCircle(mCircelX, 0, mHeight / 4, mPaintCircle);

        if (mCircelX == mWidth / 4 && mCircelY != -mHeight / 4) {
            //同下 保持X不变
            mCircelY--;
            postInvalidate();
        }else if (mCircelX == mWidth / 4 && mCircelY == -mHeight / 4){
            postInvalidateDelayed(2000);
            return;
        }

        if (mCircelY == -mHeight / 4 && mCircelX != -mWidth/4) {
            mCircelX--;
            postInvalidate();
        }else if (mCircelY == -mHeight / 4 && mCircelX == -mWidth/4){
            postInvalidateDelayed(2000);
            return;
        }

        if (mCircelX == -mWidth / 4 && mCircelY != mHeight / 4){
            mCircelY++;
            postInvalidate();
        }else if(mCircelX == -mWidth / 4 && mCircelY == mHeight / 4){
            postInvalidateDelayed(2000);
            return;
        }

        if ( mCircelY == mHeight / 4 && mCircelX != mHeight / 4){
            mCircelX++;
            postInvalidate();
        }else if (mCircelY == mHeight / 4 && mCircelX == mHeight / 4){
            postInvalidateDelayed(2000);
            return;
        }
    }
}
