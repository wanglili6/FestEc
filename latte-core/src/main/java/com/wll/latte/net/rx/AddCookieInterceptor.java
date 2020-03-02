package com.wll.latte.net.rx;

import android.annotation.SuppressLint;

import com.wll.latte.util.storage.LattePreference;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author wanglili
 * @description: 添加cookie拦截器
 * @date : 2020-03-02 16:16
 */
public  final  class AddCookieInterceptor implements Interceptor {
    @SuppressLint("CheckResult")
    @Override
    public Response intercept(Chain chain) throws IOException {
        //拦截原始请求
        Request.Builder builder = chain.request().newBuilder();
        Observable.just(LattePreference.getCustomAppProfile("cookie"))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String cookie) throws Exception {
                        //给原生api请求附带上webview拦截下来的cookie
                        builder.addHeader("cookie",cookie);
                    }
                });
        return chain.proceed(builder.build());
    }
}
