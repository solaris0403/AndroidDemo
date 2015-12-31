package com.tony.remoteview;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.SystemClock;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

/**
 * Created by user on 12/25/15.
 */
public class MyAppWidgetProvider extends AppWidgetProvider {
    private static final String TAG = MyAppWidgetProvider.class.getSimpleName();
    private static final String CLICK_ACTION = "com.tony.remoteview.CLICK";

    @Override
    public void onReceive(final Context context, final Intent intent) {
        super.onReceive(context, intent);
        Log.e(TAG, "onReceive : action = " + intent.getAction());
        // 这里判断是自己的action，做自己的事情，比如小工具被点击了要干啥，这里是做一个动画效果
        if (intent.getAction().equals(CLICK_ACTION)) {
            Toast.makeText(context, "clicked it", Toast.LENGTH_SHORT).show();
            new Thread(new Runnable() {
                @Override
                public synchronized void run() {
                    Bitmap srcBitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.icon1);
                    AppWidgetManager awm = AppWidgetManager.getInstance(context);
                    float degree;
                    RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
                    for (int i = 0; i < 37; i++) {
                        degree = i * 10F;
                        Log.e(TAG, "degree:" + degree);
                        remoteViews.setImageViewBitmap(R.id.img_widget, rotateBitmap(context, srcBitmap, degree));
                        if(i == 36){
                            Intent intent1 = new Intent(CLICK_ACTION);
                            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent1, 0);
                            remoteViews.setOnClickPendingIntent(R.id.img_widget, pendingIntent);
                        }
                        awm.updateAppWidget(new ComponentName(context, MyAppWidgetProvider.class), remoteViews);
                        SystemClock.sleep(30);
                    }
                    srcBitmap.recycle();
                }
            }).start();
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Log.i(TAG, "onUpdate");
        for (int appWidgetId : appWidgetIds) {
            onWidgetUpdate(context, appWidgetManager, appWidgetId);
        }
    }

    private void onWidgetUpdate(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        Log.i(TAG, "appWidgetId = " + appWidgetId);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
        Intent intentClick = new Intent(CLICK_ACTION);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intentClick, 0);
        remoteViews.setOnClickPendingIntent(R.id.img_widget, pendingIntent);
        appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
    }

    private Bitmap bitmap;
    private Matrix matrix = new Matrix();
    private Bitmap rotateBitmap(Context context, Bitmap srcBitmap, float degree) {
        matrix.reset();
        matrix.setRotate(degree);
        bitmap = Bitmap.createBitmap(srcBitmap, 0, 0, srcBitmap.getWidth(), srcBitmap.getHeight(), matrix, true);
        return bitmap;
    }
}
