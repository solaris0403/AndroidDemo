package com.tony.androiddemo;

import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private ThreadLocal<Boolean> mBooleanThreadLocal = new ThreadLocal<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBooleanThreadLocal.set(true);
        Log.e("123", Thread.currentThread().getName() + ":" + mBooleanThreadLocal.get());
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Looper.loop();
                Looper.myLooper().quit();
                Log.e("123", Thread.currentThread().getName()+":"+mBooleanThreadLocal.get());
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e("123", Thread.currentThread().getName()+":"+mBooleanThreadLocal.get());
            }
        }).start();
    }
}
