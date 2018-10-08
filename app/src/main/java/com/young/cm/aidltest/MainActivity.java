package com.young.cm.aidltest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.young.cm.aidltest.aidl.Book;
import com.young.cm.aidltest.aidl.IBookManager;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ServiceConnection serviceConnection;

    private IBookManager iBookManager;

    private int currentBookIndex = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindAIDLService();
    }

    private void bindAIDLService() {
        Intent intent = new Intent("com.young.aidl.AIDL_SERVICE");
        intent.setPackage("com.young.cm.aidltest");
        if (serviceConnection == null) {
            serviceConnection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    IBookManager iBookManager = IBookManager.Stub.asInterface(service);
                    if (iBookManager != null) {
                        MainActivity.this.iBookManager = iBookManager;
                    }
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {

                }
            };
        }
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }


    public void addBook(View view) {
        try {
            iBookManager.setBook(new Book(currentBookIndex++, "whatever" + currentBookIndex));
            getBook(view);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void getBook(View view) {
        try {
            List<Book> books = iBookManager.getBooks();
            Log.e("当前数据", books.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }
}
