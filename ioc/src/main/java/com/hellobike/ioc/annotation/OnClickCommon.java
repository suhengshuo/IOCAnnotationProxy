package com.hellobike.ioc.annotation;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author Su Hengshuo
 * @Data 12/21/21
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@OnBase(
        setCommonObjectListener = View.OnClickListener.class,
        setCommonListener = "setOnClickListener",
        callbackMethod = "onClick"
)
public @interface OnClickCommon {
    int value() default -1;
}
