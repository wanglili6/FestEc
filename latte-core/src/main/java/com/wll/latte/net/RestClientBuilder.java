package com.wll.latte.net;

import android.content.Context;

import com.wll.latte.net.callback.IError;
import com.wll.latte.net.callback.IFailure;
import com.wll.latte.net.callback.IRequest;
import com.wll.latte.net.callback.ISuccess;
import com.wll.latte.ui.loader.LoaderStyle;

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
public class RestClientBuilder {
    private String mUrl;//地址
    private static final Map<String, Object> PARAMS = RestCreator.getParams();//是内存的管理更合理，再不用的的时候回收
    private IRequest mIrequest;//请求回调
    private  String mDownloadDir;//下载存放目录
    private  String mExtesion;//后缀名
    private  String mName;//文件名字
    private ISuccess mISuccess;//成功回调
    private IFailure mIFailure;//请求失败
    private IError mIError;//请求返回错误
    private RequestBody mBody;//请求体
    private Context mContext;//上下文
    private LoaderStyle mLoaderstyle;//loader
    private File mFile;//loader

    RestClientBuilder() {
    }

    //传入url参数
    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;

    }

    //传入mParams参数
    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;

    }

    //传入mParams参数
    public final RestClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;

    }

    //file
    public final RestClientBuilder file(File file) {
        this.mFile = file;
        return this;

    }

    //file
    public final RestClientBuilder file(String filepath) {
        this.mFile = new File(filepath);
        return this;

    }

    //文件存放目录
    public final RestClientBuilder dir(String dir) {
        this.mDownloadDir = dir;
        return this;

    }

    //后缀名
    public final RestClientBuilder extesion(String extesion) {
        this.mExtesion = extesion;
        return this;

    }

    //传入的是原始数据
    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;

    }

    //回调方法
    public final RestClientBuilder onRequest(IRequest iRequest) {
        this.mIrequest = iRequest;
        return this;

    }

    //回调方法
    public final RestClientBuilder success(ISuccess iSuccess) {
        this.mISuccess = iSuccess;
        return this;

    }

    //回调方法
    public final RestClientBuilder failure(IFailure iFailure) {
        this.mIFailure = iFailure;
        return this;

    }

    //回调方法
    public final RestClientBuilder error(IError iError) {
        this.mIError = iError;
        return this;
    }

    /**
     * 加载loader
     *
     * @return
     */
    public final RestClientBuilder loader(Context context, LoaderStyle loaderStyle) {
        this.mContext = context;
        this.mLoaderstyle = loaderStyle;
        return this;
    }

    /**
     * 加载默认loader
     *
     * @return
     */
    public final RestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoaderstyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }

    //构建
    public final RestClient build() {
        return new RestClient(mUrl, PARAMS, mIrequest,mDownloadDir,mExtesion,mName, mISuccess, mIFailure, mIError, mBody,mFile, mContext, mLoaderstyle);

    }

}
