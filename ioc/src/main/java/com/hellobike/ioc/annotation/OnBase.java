package com.hellobike.ioc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author Su Hengshuo
 * @Data 12/21/21
 */
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface OnBase {

    /**
     * 事件三要素
     * 1、事件源: setCommonObjectListener 事件被触发的对象（ View.OnClickListener、View.OnLongClickListener）
     * 2、事件类型: setCommonListener 订阅方式、如何触发（setOnClickListener、setOnLongClickListener）
     * 3、事件处理程序: callbackMethod 最终的消费者（onClick、onLongClick）
     */
    Class<?> setCommonObjectListener();

    String setCommonListener();

    String callbackMethod();
}

