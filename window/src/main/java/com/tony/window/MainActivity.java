package com.tony.window;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    @Bind(R.id.btn_window)
    Button mBtnWindow;

    private Button mFloatingButton;
    private WindowManager.LayoutParams mLayoutParams;
    private WindowManager mWindowManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        mBtnWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFloatingButton = new Button(MainActivity.this);
                mFloatingButton.setText("click me");
                mLayoutParams = new WindowManager.LayoutParams(
                        LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0, 0,
                        PixelFormat.TRANSPARENT);
                mLayoutParams.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL
                        | LayoutParams.FLAG_NOT_FOCUSABLE
                        | LayoutParams.FLAG_SHOW_WHEN_LOCKED;
                mLayoutParams.type = LayoutParams.TYPE_SYSTEM_ERROR;
                mLayoutParams.gravity = Gravity.LEFT | Gravity.TOP;
                mLayoutParams.x = 0;
                mLayoutParams.y = 0;
                mFloatingButton.setOnTouchListener(MainActivity.this);
                mWindowManager.addView(mFloatingButton, mLayoutParams);
            }
        });
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE: {
                int x = (int) event.getX();
                int y = (int) event.getY();
                mLayoutParams.x = rawX;
                mLayoutParams.y = rawY;
                mWindowManager.updateViewLayout(mFloatingButton, mLayoutParams);
                break;
            }
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        mWindowManager.removeView(mFloatingButton);
        super.onDestroy();
    }
}
