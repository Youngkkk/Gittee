package com.young.cm.aidltest;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.young.cm.aidltest.aidl.Book;
import com.young.cm.aidltest.aidl.IBookManager;

import java.util.ArrayList;
import java.util.List;

public class BookManagerService extends Service {

    private List<Book> mBookList=new ArrayList<>();

    private Binder binder =new IBookManager.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public List<Book> getBooks() throws RemoteException {
            return mBookList;
        }

        @Override
        public void setBook(Book book) throws RemoteException {
            mBookList.add(book);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("TAG", "---->远程服务启动");
        mBookList.add(new Book(1, "book1"));
        mBookList.add(new Book(2, "book2"));

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
