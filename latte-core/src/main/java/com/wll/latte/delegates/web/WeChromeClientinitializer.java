package com.wll.latte.delegates.web;

import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * @author wanglili
 * @description:
 * @date : 2020-03-01 22:49
 */
public class WeChromeClientinitializer extends WebChromeClient {

    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        return super.onJsAlert(view, url, message, result);
    }
}
