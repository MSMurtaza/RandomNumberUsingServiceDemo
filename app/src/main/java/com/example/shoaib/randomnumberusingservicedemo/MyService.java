package com.example.shoaib.randomnumberusingservicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Random;

public class MyService extends Service {

    private int mRandomNumber;
    private boolean mIsRandomNumberGeneratorOn;

    private final int MIN = 0;
    private final int MAX = 100;

    class MyServiceBinder extends Binder {
        public MyService getService() {
            return MyService.this;
        }
    }

    private IBinder mBinder = new MyServiceBinder();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(getString(R.string.service_demo_tag), " in onStartCommand, thread id " + Thread.currentThread().getId());
        //stopSelf();
        mIsRandomNumberGeneratorOn = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                startRandomNumberGenerator();
            }
        }).start();
        return START_STICKY;
        //return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.i(getString(R.string.service_demo_tag), " in onRebind");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopRandomNumberGenerator();
        Log.i(getString(R.string.service_demo_tag), " in onDestroy, thread destroyed");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(getString(R.string.service_demo_tag), " in onBind");
        return mBinder;
    }

    private void startRandomNumberGenerator() {
        while (mIsRandomNumberGeneratorOn) {
            try {
                Thread.sleep(1000);
                if (mIsRandomNumberGeneratorOn) {
                    mRandomNumber = new Random().nextInt(MAX) + MIN;
                    Log.i(getString(R.string.service_demo_tag), " in startRandomNumberGenerator, thread id " +
                            Thread.currentThread().getName() + " Random No. " +
                            getmRandomNumber());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                Log.i(getString(R.string.service_demo_tag), " thread interrupted");
            }
        }
    }

    private void stopRandomNumberGenerator() {
        mIsRandomNumberGeneratorOn = false;
    }

    public int getmRandomNumber() {
        return mRandomNumber;
    }
}
