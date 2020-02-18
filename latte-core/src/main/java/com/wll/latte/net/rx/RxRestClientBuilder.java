package com.wll.latte.net.rx;

import android.content.Context;

import com.wll.latte.net.RestClient;
import com.wll.latte.net.RestCreator;
import com.wll.latte.net.callback.IError;
import com.wll.latte.net.callback.IFailure;
import com.wll.latte.net.callback.IRequest;
import com.wll.latte.net.callback.ISuccess;
import com.wll.latte.ui.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @author wanglili
 * @description: RestClient 的构建类
 * @date : 2020-02-17 14:58
 */
public class RxRestClientBuilder {
    private String mUrl;//地址
    private static final Map<String, Object> PARAMS = RestCreator.getParams();//是内存的管理更合理，再不用的的时候回收
    private RequestBody mBody;//请求体
    private Context mContext;//上下文
    private LoaderStyle mLoaderstyle;//loader
    private File mFile;//loader

    RxRestClientBuilder() {
    }

    //传入url参数
    public final RxRestClientBuilder url(String url) {
        this.mUrl = url;
        return this;

    }

    //传入mParams参数
    public final RxRestClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;

    }

    //传入mParams参数
    public final RxRestClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;

    }

    //file
    public final RxRestClientBuilder file(File file) {
        this.mFile = file;
        return this;

    }

    //file
    public final RxRestClientBuilder file(String filepath) {
        this.mFile = new File(filepath);
        return this;

    }


    //传入的是原始数据
    public final RxRestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;

    }

    /**
     * 加载loader
     *
     * @return
     */
    public final RxRestClientBuilder loader(Context context, LoaderStyle loaderStyle) {
        this.mContext = context;
        this.mLoaderstyle = loaderStyle;
        return this;
    }

    /**
     * 加载默认loader
     *
     * @return
     */
    public final RxRestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoaderstyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }

    //构建
    public final RxRestClient build() {
        return new RxRestClient(mUrl, PARAMS, mBody,mFile, mContext, mLoaderstyle);

    }

}
