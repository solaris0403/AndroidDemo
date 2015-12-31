package com.tony.thread;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
//        for (int i=0; i<20; i++){
//            fixedThreadPool.execute(new Runnable() {
//                @Override
//                public void run() {
//                    Log.e("123", Thread.currentThread().getName()+"");
//                }
//            });
//        }
    }

    private class MyAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            SystemClock.sleep(3000);
            Log.e("123", Thread.currentThread().getName() + ":"+System.currentTimeMillis());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}
