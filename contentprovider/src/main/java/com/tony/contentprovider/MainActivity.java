package com.tony.contentprovider;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String AUTHORITY = "com.tony.book.provider";
    //兩張表
    private static final Uri BOOK_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/book");
    private static final Uri USER_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/user");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider);
        ContentValues values = new ContentValues();
        values.put("_id", 6);
        values.put("name", "程序设计的艺术");
        getContentResolver().insert(BOOK_CONTENT_URI, values);
        Cursor cursor = getContentResolver().query(BOOK_CONTENT_URI, new String[]{"_id", "name"}, null, null, null);
        while (cursor.moveToNext()) {
            Book book = new Book();
            book.bookId = cursor.getInt(0);
            book.bookName = cursor.getString(1);
            Log.d(TAG, "query book:" + book.toString());
        }
        cursor.close();
        Cursor userCursor = getContentResolver().query(USER_CONTENT_URI, new String[]{"_id", "name", "sex"}, null, null, null);
        while (userCursor.moveToNext()) {
            User user = new User();
            user.userId = userCursor.getInt(0);
            user.userName = userCursor.getString(1);
            user.isMale = userCursor.getInt(2) == 1;
            Log.d(TAG, "query user:" + user.toString());
        }
        userCursor.close();
    }
}
