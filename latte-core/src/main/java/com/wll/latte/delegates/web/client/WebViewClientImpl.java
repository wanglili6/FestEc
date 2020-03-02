package com.wll.latte.delegates.web.client;

import android.graphics.Bitmap;
import android.os.Handler;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.wll.latte.delegates.web.IPageLoadListenner;
import com.wll.latte.delegates.web.WebDelegate;
import com.wll.latte.delegates.web.WebDelegateImpl;
import com.wll.latte.delegates.web.route.Router;
import com.wll.latte.ui.loader.LatteLoader;
import com.wll.latte.util.log.LatteLogger;

/**
 * @author wanglili
 * @description:
 * @date : 2020-03-01 22:22
 */
public class WebViewClientImpl extends WebViewClient {

    private final WebDelegate delegate;
    private IPageLoadListenner iPageLoadListenner = null;
    private static final Handler HANDLER = new Handler();

    public void setPageLoadListenner(IPageLoadListenner iPageLoadListenner) {
        this.iPageLoadListenner = iPageLoadListenner;
    }

    public WebViewClientImpl(WebDelegate delegate) {
        this.delegate = delegate;
    }


    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        LatteLogger.d("shouldOverrideUrlLoading", url);
        return Router.getInstance().handleWebUrl(delegate, url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (iPageLoadListenner != null) {
            iPageLoadListenner.onLoadStart();
        }
        LatteLoader.showLoading(view.getContext());
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (iPageLoadListenner != null) {
            iPageLoadListenner.onLoadEnd();
        }
        HANDLER.postDelayed(new Runnable() {
            @Override
            public void run() {
                LatteLoader.stopLoading();
            }
        }, 1000);
    }

    //    /**
//     * 判断型号
//     * @param view
//     * @param request
//     * @return
//     */
//    @Override
//    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//        return super.shouldOverrideUrlLoading(view, request);
//    }
}
