package com.wll.latte.delegates.web;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;

import com.wll.latte.delegates.web.client.WebViewClientImpl;
import com.wll.latte.delegates.web.route.RouteKeys;
import com.wll.latte.delegates.web.route.Router;

/**
 * @author wanglili
 * @description:
 * @date : 2020-03-01 22:15
 */
public class WebDelegateImpl extends WebDelegate  {

    private IPageLoadListenner iPageLoadListenner = null;

    public void setPageLoadListenner(IPageLoadListenner iPageLoadListenner) {
        this.iPageLoadListenner = iPageLoadListenner;
    }

    //创建
    public static WebDelegateImpl create(String url) {
        Bundle args = new Bundle();
        args.putString(RouteKeys.URL.name(), url);
        WebDelegateImpl webDelegateimpl = new WebDelegateImpl();
        webDelegateimpl.setArguments(args);
        return webDelegateimpl;
    }


    @Override
    public IWebViewinitializer setInitializer() {
        return this;
    }

    @Override
    public Object setLayout() {
        return getWebView();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        if (null != getUrl()) {
            //原生的方法模拟web进行跳转
            Router.getInstance().loadPage(this, getUrl());

        }
    }


    @Override
    public WebView initWebView(WebView webView) {
        return new WebViewClientInitializer().createWebView(webView);
    }

    @Override
    public WebViewClient initWebViewClient() {
        WebViewClientImpl client =  new WebViewClientImpl(this);
        client.setPageLoadListenner(iPageLoadListenner);
        return client;
    }

    @Override
    public WebChromeClient initWebChromeClient() {
        return new WeChromeClientinitializer();
    }
}
