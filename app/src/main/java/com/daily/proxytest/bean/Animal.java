package com.daily.proxytest.bean;

import android.util.Log;

import com.daily.proxytest.inter.FLy;
import com.daily.proxytest.inter.Run;

/**
 * Created by Administrator on 2018/5/2.
 */

public class Animal implements FLy,Run{

    public static final String TAG = "Animal";

    @Override
    public void fly() {
        Log.e(TAG, "Animal Fly");
    }

    @Override
    public void run() {
        Log.e(TAG, "Animal Run");
    }
}
