package com.daily.proxytest.reflect;

import android.util.Log;

import com.daily.proxytest.bean.Animal;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2018/5/2.
 */

public class AnimalProxyHandler implements InvocationHandler {

    public static String TAG = "AnimalProxyHandler";

    private Animal mAnimal;

    public AnimalProxyHandler(Animal animal) {
        mAnimal = animal;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Log.e(TAG, method.getName());
        return method.invoke(mAnimal, args);
    }
}
