package com.wll.latte.delegates.web.route;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.URLUtil;
import android.webkit.WebView;

import androidx.core.content.ContextCompat;

import com.wll.latte.delegates.bottom.LatteDelegate;
import com.wll.latte.delegates.web.WebDelegate;
import com.wll.latte.delegates.web.WebDelegateImpl;

/**
 * @author wanglili
 * @description: 路由者
 * @date : 2020-03-01 22:25
 */
public class Router {
    public Router() {
    }

    /**
     * 惰性单例
     */
    private static class Holder {
        private static final Router INSTRANCE = new Router();
    }

    public static Router getInstance() {
        return Holder.INSTRANCE;
    }

    //处理url的方法, 接管url

    public final boolean handleWebUrl(WebDelegate delegate, String url) {
        if (url.contains("tel:")) {
            callPhone(delegate.getContext(), url);
            return true;
        }
        LatteDelegate topFragment = delegate.getTopFragment();
//        //解析原生的
        WebDelegateImpl webDelegate = WebDelegateImpl.create(url);
        topFragment.start(webDelegate);
        return true;
    }

    /**
     * 判断电话
     */

    private void callPhone(Context context, String url) {
        final Intent intent = new Intent(Intent.ACTION_DIAL);
        final Uri data = Uri.parse(url);
        intent.setData(data);
        ContextCompat.startActivity(context, intent, null);
    }

    /**
     * 加载webpage
     * @param webView
     * @param url
     */
    private void loadWebpage(WebView webView,String url){
        if (null!=webView){
            webView.loadUrl(url);
        }else {
            throw new NullPointerException("webView is null");
        }
    }

    /**
     * 加载本地webpage
     * @param webView
     * @param url
     */
    private void loadLocalpage(WebView webView,String url){
        loadWebpage(webView,"file:///android_asset/"+url);
    }

    private void loadPage(WebView webView,String url){
        if (URLUtil.isNetworkUrl(url)||URLUtil.isAssetUrl(url)){
            loadWebpage(webView,url);
        }else {
            loadLocalpage(webView,url);
        }
    }

    public void loadPage(WebDelegate delegate,String url){
       loadPage(delegate.getWebView(),url);
    }
}
