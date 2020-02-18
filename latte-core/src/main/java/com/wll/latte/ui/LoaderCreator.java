package com.wll.latte.ui;

import android.content.Context;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

/**
 * @author wanglili
 * @description: loader创建类
 * @date : 2020-02-17 16:35
 */
public class LoaderCreator {
    private static final WeakHashMap<String, Object> LOADING_MAP = new WeakHashMap<>();

    static AVLoadingIndicatorView create(String type, Context context) {
        final AVLoadingIndicatorView avLoadingIndicatorView = new AVLoadingIndicatorView(context);
        if (null == LOADING_MAP.get(type)) {
            final Indicator indicator = getIndicator(type);
            LOADING_MAP.put(type, indicator);
        }
        avLoadingIndicatorView.setIndicator((Indicator) LOADING_MAP.get(type));
        return avLoadingIndicatorView;

    }

    private static Indicator getIndicator(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }
        final StringBuilder drawableClassName = new StringBuilder();
        if (!name.contains(".")) {
            final String defultPackageName = AVLoadingIndicatorView.class.getPackage().getName();
            drawableClassName.append(defultPackageName)
                    .append(".indicators")
                    .append(".");
        }
        drawableClassName.append(name);
        try {
            final Class<?> drawanleClass = Class.forName(drawableClassName.toString());
            return (Indicator) drawanleClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
