// IBookManager.aidl
package com.tony.aidl;

// Declare any non-default types here with import statements
import com.tony.aidl.Book;
import com.tony.aidl.IOnNewBookArrivedListener;

interface IBookManager {
    List<Book> getBookList();
    void addBook(in Book book);
    void registerListener(IOnNewBookArrivedListener listener);
    void unregisterListener(IOnNewBookArrivedListener listener);
}
