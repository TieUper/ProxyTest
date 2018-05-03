package com.daily.proxytest.utils;

import android.app.Activity;
import android.view.View;

import com.daily.proxytest.annotation.EventType;
import com.daily.proxytest.annotation.InjectView;
import com.daily.proxytest.annotation.onClick;
import com.daily.proxytest.reflect.ProxyHandler;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2018/5/2.
 */

public class ViewUtils {

    public static void injectView(Activity activity) {
        if (null == activity) return;

        Class<? extends Activity> activityClass = activity.getClass();
        //获取DeclaredFields的成员变量
        Field[] declaredFields = activityClass.getDeclaredFields();
        for (Field field : declaredFields) {
            //判断方法中是否有指定注解类型的注解
            if (field.isAnnotationPresent(InjectView.class)) {
                //找到InjectView注解的field
                InjectView annotation = field.getAnnotation(InjectView.class);
                //找到button的id
                int value = annotation.value();
                try {
                    //找到findViewById方法
//                    Method findViewById = activityClass.getMethod("findViewById", int.class);
//                    findViewById.setAccessible(true);
                    field.setAccessible(true);
                    field.set(activity,activity.findViewById(value));
                }catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void injectEvent(Activity activity) {
        if (null == activity) {
            return;
        }

        Class<? extends Activity> activityClass = activity.getClass();
        Method[] declaredMethods = activityClass.getDeclaredMethods();

        for (Method method : declaredMethods) {
            if (method.isAnnotationPresent(onClick.class)) {
                onClick annotation = method.getAnnotation(onClick.class);
                //获取button id
                int[] value = annotation.value();
                //获取EventType
                EventType eventType = annotation.annotationType().getAnnotation(EventType.class);
                Class listenerType = eventType.listenerType();
                String listenerSetter = eventType.listenerSetter();
                String methodName = eventType.methodName();

                ProxyHandler proxyHandler = new ProxyHandler(activity);

                Object listener = Proxy.newProxyInstance(listenerType.getClassLoader(), new Class[]{listenerType}, proxyHandler);

                proxyHandler.mapMethod(methodName, method);
                try {
                    for (int id : value) {
                        //找到button

                        Method findViewById = activityClass.getMethod("findViewById", int.class);
                        findViewById.setAccessible(true);
                        View btn = (View) findViewById.invoke(activity, id);
                        Method listenerSetMethod = btn.getClass().getMethod(listenerSetter, listenerType);
                        listenerSetMethod.setAccessible(true);
                        listenerSetMethod.invoke(btn, listener);
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
