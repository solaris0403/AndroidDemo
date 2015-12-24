package com.tony.binderpool;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class BinderPoolService extends Service {
    private Binder mBinder = new BinderPool.BinderPoolImpl();
    public BinderPoolService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("BinderPoolService", "onBind");
        return mBinder;
    }
}
