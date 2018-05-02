package com.daily.proxytest.utils;

import android.app.Activity;

import com.daily.proxytest.annotation.InjectView;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2018/5/2.
 */

public class ViewUtils {

    public static void inJectView(Activity activity) {
        if(null == activity) return;

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
                    Method findViewById = activityClass.getMethod("findViewById", int.class);
                    findViewById.setAccessible(true);
                    findViewById.invoke(activity, value);
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
