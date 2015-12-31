package com.tony.remoteview.custom;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.*;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

import com.tony.remoteview.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TestActivity extends Activity {

    @Bind(R.id.btn_send)
    Button mBtnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        mBtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send();
            }
        });
    }
    private void send(){
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.view_remote);
        remoteViews.setTextViewText(R.id.txt_time, "msg from process:" + android.os.Process.myPid());
        PendingIntent pendingIntent = PendingIntent.getActivity(TestActivity.this, 0, new Intent(TestActivity.this, TestActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.txt_time, pendingIntent);
        Intent intent = new Intent(MyConstant.ACTION);
        intent.putExtra(MyConstant.EXTRA_REMOTE_VIEWS, remoteViews);
        TestActivity.this.sendBroadcast(intent);
    }
}
