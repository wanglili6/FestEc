package com.wll.latte.net.download;

import android.os.AsyncTask;

import com.wll.latte.net.RestCreator;
import com.wll.latte.net.callback.IError;
import com.wll.latte.net.callback.IFailure;
import com.wll.latte.net.callback.IRequest;
import com.wll.latte.net.callback.ISuccess;

import java.util.WeakHashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author wanglili
 * @description: 文件下载
 * @date : 2020-02-18 15:21
 */
public class DownloadHandler {

    private final String URL;//地址
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();//参数
    private final IRequest REQUEST;//请求回调
    private final String DOWNLOAD_DIR;//下载目录
    private final String EXTENSION;//下载延迟
    private final String NAME;//文件名字
    private final ISuccess SUCCESS;//成功回调
    private final IFailure FAILURE;//请求失败
    private final IError ERROR;//请求返回错误

    public DownloadHandler(String url,
                           IRequest request,
                           String downloadDir,
                           String extension,
                           String name,
                           ISuccess success,
                           IFailure failure,
                           IError error) {
        this.URL = url;
        this.REQUEST = request;
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
    }

    /**
     * 下载文件
     */
    public void handlerDownload() {
        //
        if (null != REQUEST) {
            REQUEST.onRequestStart();
        }

        RestCreator.getRestService().download(URL, PARAMS)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            ResponseBody responseBody = response.body();
                            final saveFileTask task = new saveFileTask(REQUEST, SUCCESS);
                            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, DOWNLOAD_DIR, EXTENSION, responseBody, NAME);
                            //一定注意判断，否则文件下载不全，是否下载完成
                            if (task.isCancelled()) {
                                if (null != REQUEST) {
                                    REQUEST.onRequestEnd();
                                }
                            }
                        } else {
                            if (null != ERROR) {
                                ERROR.onError(response.code(), response.message());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        if (null != FAILURE) {
                            FAILURE.onFailure();
                        }
                    }
                });

    }
}
