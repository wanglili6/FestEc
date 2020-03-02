package com.wll.latte.delegates.web.event;

import android.annotation.SuppressLint;
import android.webkit.WebView;
import android.widget.Toast;

/**
 * @author wanglili
 * @description:
 * @date : 2020-03-02 14:05
 */
public class TestEvent  extends Event {
    @Override
    public String execute(String params) {
        Toast.makeText(getContext(),getAction(),Toast.LENGTH_SHORT).show();
        //原生事件 传递给 js
        if (getAction().equals("test")) {
            final WebView webView = getWebView();
            webView.post(new Runnable() {
                @SuppressLint("NewApi")
                @Override
                public void run() {
                    webView.evaluateJavascript("nativeCall();",null);
                }
            });
        }
        return null;
    }
}
