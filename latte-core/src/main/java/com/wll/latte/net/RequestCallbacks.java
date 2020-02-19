package com.wll.latte.net;

import android.os.Handler;

import com.wll.latte.net.callback.IError;
import com.wll.latte.net.callback.IFailure;
import com.wll.latte.net.callback.IRequest;
import com.wll.latte.net.callback.ISuccess;
import com.wll.latte.ui.loader.LatteLoader;
import com.wll.latte.ui.loader.LoaderStyle;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author wanglili
 * @description: callback
 * @date : 2020-02-17 15:43
 */
public class RequestCallbacks implements Callback<String> {

    private final IRequest REQUEST;//请求回调
    private final ISuccess SUCCESS;//成功回调
    private final IFailure FAILURE;//请求失败
    private final IError ERROR;//请求返回错误
    private final LoaderStyle LOADER_STYLE;//loader
    //延迟
    private static final Handler HANDLER = new Handler();//loader

    public RequestCallbacks(IRequest request, ISuccess success, IFailure failure, IError error, LoaderStyle loaderStyle) {
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.LOADER_STYLE = loaderStyle;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()) {
            //请求成功
            if (call.isExecuted()) {
                //call执行了
                if (null != SUCCESS) {
                    SUCCESS.onSuccess(response.body());
                }
            }
        } else {
            if (null != ERROR) {
                ERROR.onError(response.code(), response.message());
            }
        }
        stopShowLoader();
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (null != FAILURE) {
            FAILURE.onFailure();
        }
        //结束请求
        if (null != REQUEST) {
            REQUEST.onRequestEnd();
        }

        //停止显示loader
        stopShowLoader();

    }

    /**
     * 停止loader
     */
    private void stopShowLoader() {
        if (null != LOADER_STYLE) {
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    LatteLoader.stopLoading();
                }
            }, 1000);
        }
    }
}
