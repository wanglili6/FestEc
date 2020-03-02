package com.wll.latte.delegates.web.event;

import android.content.Context;
import android.webkit.WebView;

import com.wll.latte.delegates.bottom.LatteDelegate;
import com.wll.latte.delegates.web.WebDelegate;

/**
 * @author wanglili
 * @description: 处理具体事件
 * @date : 2020-03-02 13:53
 */
public abstract class Event implements IEvent {
    private Context context;
    private String mAction;
    private WebDelegate mDelegate = null;
    private String mUrl = null;
    private WebView webView = null;

    public WebView getWebView() {
        return mDelegate.getWebView();
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getAction() {
        return mAction;
    }

    public void setAction(String mAction) {
        this.mAction = mAction;
    }

    public WebDelegate getDelegate() {
        return mDelegate;
    }

    public void setDelegate(WebDelegate mDelegate) {
        this.mDelegate = mDelegate;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }


}
