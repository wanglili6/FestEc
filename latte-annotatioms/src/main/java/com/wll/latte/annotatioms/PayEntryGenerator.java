package com.wll.latte.annotatioms;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wanglili
 * @description:
 * @date : 2020-02-20 18:55
 */
@Target(ElementType.TYPE)//用在类上
@Retention(RetentionPolicy.SOURCE)//在源码上注解
public @interface PayEntryGenerator {
    String packageName();
    Class<?> payEntryTemplete();
}
