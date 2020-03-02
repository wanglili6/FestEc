package com.wll.latte.delegates.web;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebView;

import androidx.annotation.Nullable;

import com.wll.latte.app.ConfigKeys;
import com.wll.latte.app.Latte;
import com.wll.latte.delegates.bottom.LatteDelegate;
import com.wll.latte.delegates.web.route.RouteKeys;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @author wanglili
 * @description: web核心页面
 * @date : 2020-03-01 16:51
 */
public abstract class WebDelegate extends LatteDelegate implements IWebViewinitializer {
    private WebView mWebView = null;
    //软引用
    private final ReferenceQueue<WebView> WEB_VIEW_QUEUE = new ReferenceQueue();
    //web的地址
    private String mUrl = null;
    //设置一个flag
    private boolean mIsWebviewAbailable = false;
    //顶部delegate
    private LatteDelegate mTopFragment = null;

    public WebDelegate() {
    }

    public abstract IWebViewinitializer setInitializer();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle arguments = getArguments();
        mUrl = arguments.getString(RouteKeys.URL.name());
        initWebView();
    }

    /**
     * 初始化webview
     */

    @SuppressLint("JavascriptInterface")
    private void initWebView() {
        if (null != mWebView) {
            //清除所有的view
            mWebView.removeAllViews();
            mWebView.destroy();
        } else {
            IWebViewinitializer iWebViewinitializer = setInitializer();
            if (null != iWebViewinitializer) {
                //使用弱应用 WebView在xml中定义可能会有oom内存泄漏 使用new
                final WeakReference<WebView> webViewWeakReference =
                        new WeakReference<>(new WebView(getContext()), WEB_VIEW_QUEUE);
                mWebView = webViewWeakReference.get();
                mWebView = iWebViewinitializer.initWebView(mWebView);
                mWebView.setWebViewClient(iWebViewinitializer.initWebViewClient());
                mWebView.setWebChromeClient(iWebViewinitializer.initWebChromeClient());
                String name = (String) Latte.getConfigurations(ConfigKeys.JAVASCRIPT_INTERFACE);
                mWebView.addJavascriptInterface(LatteWebInterface.create(this), name);
                mIsWebviewAbailable = true;
            } else {
                throw new NullPointerException("Initializer is null");
            }
        }
    }

    public void setTopFragment(LatteDelegate fragment) {
        mTopFragment = fragment;
    }

    public LatteDelegate getTopFragment() {
        if (mTopFragment == null) {
            mTopFragment = this;
        }
        return mTopFragment;
    }

    public WebView getWebView() {
        if (null != mWebView) {
            return mIsWebviewAbailable ? mWebView : null;
        } else {
            throw new NullPointerException("WebView is null");
        }
    }

    //获取url
    public String getUrl() {
        if (null != mUrl) {
            return mUrl;
        } else {
            throw new NullPointerException("mUrl is null");
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (null != mWebView) {
            mWebView.onPause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (null != mWebView) {
            mWebView.onResume();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mIsWebviewAbailable = false;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != mWebView) {
            mWebView.removeAllViews();
            mWebView.destroy();
            mWebView = null;
        }
    }
}
