package com.tony.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BookManagerActivity extends AppCompatActivity {
    private static final String TAG = BookManagerActivity.class.getSimpleName();
    @Bind(R.id.btn_add_book)
    Button btnAddBook;
    private IBookManager bookManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_manager);
        ButterKnife.bind(this);
        btnAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    bookManager.addBook(new Book(25, "name"));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        Intent intent = new Intent("com.tony.service.book");
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    private IBinder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            Log.d(TAG, "binder died. tname:" + Thread.currentThread().getName());
            if (bookManager == null)
                return;
            bookManager.asBinder().unlinkToDeath(mDeathRecipient, 0);
            bookManager = null;
            Intent intent = new Intent("com.tony.service.book");
            bindService(intent, connection, Context.BIND_AUTO_CREATE);
        }
    };

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            bookManager = IBookManager.Stub.asInterface(service);
            try {
                bookManager.asBinder().linkToDeath(mDeathRecipient, 0);
                bookManager.registerListener(listener);
//                List<Book> list = bookManager.getBookList();
//                Log.i(TAG, "query book list, list type:" + list.getClass().getCanonicalName());
//                Log.i(TAG, "book list, list size:" + list.size());
//                bookManager.addBook(new Book(25, "NewBook"));
//                Log.i(TAG, "book list, list size:" + bookManager.getBookList().size());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            bookManager = null;
            Log.d(TAG, "onServiceDisconnected. tname:" + Thread.currentThread().getName());
        }
    };

    private IOnNewBookArrivedListener listener = new IOnNewBookArrivedListener.Stub() {
        @Override
        public void onNewBookArrivedListener(Book book) throws RemoteException {
            Log.e(TAG, "onNewBookArrivedListener:" + book);
            Log.e(TAG, "bookList:" + bookManager.getBookList().size());
        }
    };

    @Override
    protected void onDestroy() {
        if (bookManager != null && bookManager.asBinder().isBinderAlive()) {
            try {
                Log.e(TAG, "unregister listener:" + listener);
                bookManager.unregisterListener(listener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        Log.e(TAG, "unbindService:" + connection);
        unbindService(connection);
        super.onDestroy();
    }
}
