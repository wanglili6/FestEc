package com.wll.latte.delegates.web;

import android.annotation.SuppressLint;
import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;
import com.wll.latte.delegates.web.event.Event;
import com.wll.latte.delegates.web.event.EventManager;
import com.wll.latte.util.log.LatteLogger;

/**
 * @author wanglili
 * @description: 交互器
 * @date : 2020-03-01 17:17
 */
public class LatteWebInterface {
    private final WebDelegate FRAGMENT;

    private LatteWebInterface(WebDelegate fragment) {
        this.FRAGMENT = fragment;
    }

    public static LatteWebInterface create(WebDelegate fragment) {
        return new LatteWebInterface(fragment);
    }

    //注入的方法名 js返回值
    @JavascriptInterface
    public String event(String params) {
        final String action = JSON.parseObject(params).getString("action");
        final Event event = EventManager.getInstance().createEvent(action);
        LatteLogger.e("WEB_EVENT",params);
        if (event != null) {
            event.setAction(action);
            event.setDelegate(FRAGMENT);
            event.setContext(FRAGMENT.getContext());
            event.setUrl(FRAGMENT.getUrl());
            return event.execute(params);
        }
        return null;
    }
}
