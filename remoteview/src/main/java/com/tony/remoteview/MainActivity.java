package com.tony.remoteview;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RemoteViews;

import com.tony.remoteview.custom.MyConstant;
import com.tony.remoteview.custom.TestActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.btn_notify_default)
    Button btnNotifyDefault;
    @Bind(R.id.btn_test)
    Button mBtnTest;
    @Bind(R.id.lyt_view)
    LinearLayout mLytRemote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        btnNotifyDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                notifyDefault();
            }
        });
        mBtnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                startActivity(intent);
            }
        });
        IntentFilter intentFilter = new IntentFilter(MyConstant.ACTION);
        registerReceiver(receiver, intentFilter);
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(MyConstant.ACTION)) {
                RemoteViews remoteViews = intent.getParcelableExtra(MyConstant.EXTRA_REMOTE_VIEWS);
                updateUI(remoteViews);
            }
        }
    };

    private void updateUI(RemoteViews remoteViews) {
        int redId = getResources().getIdentifier("view_remote", "layout", getPackageName());
        View view = getLayoutInflater().inflate(redId, mLytRemote, false);
        remoteViews.reapply(this, view);
        mLytRemote.addView(view);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();
    }
//    private void notifyDefault() {
//        Intent intent = new Intent("com.tony.view.launch");
//        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
//        Notification.Builder notification = new Notification.Builder(MainActivity.this)//
//                .setSmallIcon(R.mipmap.ic_launcher)//
//                .setContentIntent(pendingIntent)//
//                .setAutoCancel(true)//
//                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))//
//                .setContentTitle("title")//
//                .setContentText("text")//
//                .setSubText("subtext");//
//        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        manager.notify(1, notification.build());
//    }
}
