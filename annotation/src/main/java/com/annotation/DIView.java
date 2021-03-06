package com.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2018/5/4.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.CLASS)
public @interface DIView {
    int value() default 0;
}
