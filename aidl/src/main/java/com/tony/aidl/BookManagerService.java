package com.tony.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class BookManagerService extends Service {
    public static final String TAG = BookManagerService.class.getSimpleName();
    private CopyOnWriteArrayList<Book> mBooks = new CopyOnWriteArrayList<>();
    private RemoteCallbackList<IOnNewBookArrivedListener> mOnNewBookArrivedListenerList = new RemoteCallbackList<>();

    public BookManagerService() {
    }

    private Binder mBinder = new IBookManager.Stub() {
        @Override
        public List<Book> getBookList() throws RemoteException {
            return mBooks;
        }

        @Override
        public void registerListener(IOnNewBookArrivedListener listener) throws RemoteException {
            mOnNewBookArrivedListenerList.register(listener);
            final int N = mOnNewBookArrivedListenerList.beginBroadcast();
            mOnNewBookArrivedListenerList.finishBroadcast();
            Log.d(TAG, "registerListener, current size:" + N);
        }

        @Override
        public void unregisterListener(IOnNewBookArrivedListener listener) throws RemoteException {
            boolean success = mOnNewBookArrivedListenerList.unregister(listener);
            if (success) {
                Log.d(TAG, "unregister success.");
            } else {
                Log.d(TAG, "not found, can not unregister.");
            }
            final int N = mOnNewBookArrivedListenerList.beginBroadcast();
            mOnNewBookArrivedListenerList.finishBroadcast();
            Log.d(TAG, "unregisterListener, current size:" + N);
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            mBooks.add(book);
            final int N = mOnNewBookArrivedListenerList.beginBroadcast();
            for (int i = 0; i < N; i++) {
                IOnNewBookArrivedListener listener = mOnNewBookArrivedListenerList.getBroadcastItem(i);
                if (listener != null) {
                    listener.onNewBookArrivedListener(book);
                }
            }
            mOnNewBookArrivedListenerList.finishBroadcast();
        }
    };

    @Override
    public IBinder onBind(Intent intent) {

        return mBinder;
    }
}
