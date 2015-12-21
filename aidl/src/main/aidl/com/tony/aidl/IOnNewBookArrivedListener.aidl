// IOnNewBookArrivedListener.aidl
package com.tony.aidl;

// Declare any non-default types here with import statements
import com.tony.aidl.Book;

interface IOnNewBookArrivedListener {
    void onNewBookArrivedListener(in Book book);
}
