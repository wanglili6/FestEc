package com.wll.latte.delegates.web;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * @author wanglili
 * @description: web的初始化接口
 * @date : 2020-03-01 16:58
 */
public interface IWebViewinitializer {

    WebView initWebView(WebView webView);

    // 针对浏览器本省的控制
    WebViewClient initWebViewClient();

    //对内部本身的控制
    WebChromeClient initWebChromeClient();
}
