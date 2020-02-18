package com.wll.latte.net;

import android.content.Context;

import com.wll.latte.net.callback.IError;
import com.wll.latte.net.callback.IFailure;
import com.wll.latte.net.callback.IRequest;
import com.wll.latte.net.callback.ISuccess;
import com.wll.latte.net.download.DownloadHandler;
import com.wll.latte.ui.LatteLoader;
import com.wll.latte.ui.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Url;

/**
 * @author wanglili
 * @description: 网络请求的具体实现类
 * @date : 2020-02-17 10:24
 */
public class RestClient {
    //参数 URL 文件  传入的值  加载圈
    private final String URL;//地址
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();//参数
    private final IRequest REQUEST;//请求回调
    private final String DOWNLOAD_DIR;//下载目录
    private final String EXTENSION;//下载延迟
    private final String NAME;//文件名字
    private final ISuccess SUCCESS;//成功回调
    private final IFailure FAILURE;//请求失败
    private final IError ERROR;//请求返回错误
    private final RequestBody BODY;//请求体
    private final LoaderStyle LOADER_STYLE;//loader
    private final Context CONTEXT;//上下文
    private final File FILE;//文件

    public RestClient(String url,
                      Map<String, Object> params,
                      IRequest request,
                      String downloadDir,
                      String extension,
                      String name,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      RequestBody body,
                      File file,
                      Context context,
                      LoaderStyle loaderStyle
    ) {
        this.URL = url;
        PARAMS.putAll(params);
        this.REQUEST = request;
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;
        this.FILE = file;
        this.CONTEXT = context;
        this.LOADER_STYLE = loaderStyle;
    }

    //创建构造者
    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    //创建request方法 请求方法
    public void request(HttpMethod method) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;
        //请求开始
        if (null != REQUEST) {
            REQUEST.onRequestStart();
        }
        //展示loader
        if (null != LOADER_STYLE) {
            LatteLoader.showLoading(CONTEXT, LOADER_STYLE);
        }
        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case POST_RAW://post原始数据下载
                call = service.postRaw(URL, BODY);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case PUT_RAW:
                call = service.putRaw(URL, BODY);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            case UPLOAD://上传数据
                final RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                //创建文件请求体
                final MultipartBody.Part body = MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                call = RestCreator.getRestService().upload(URL, body);
                break;

            default:
                break;
        }

        if (null != call) {
            call.enqueue(getRequestCallBack());
        }

    }

    /**
     * 获取callback
     *
     * @return
     */
    private Callback<String> getRequestCallBack() {
        return new RequestCallbacks(REQUEST, SUCCESS, FAILURE, ERROR, LOADER_STYLE);
    }

    //使用方法
    public final void get() {
        request(HttpMethod.GET);
    }


    public final void post() {
        if (null == BODY) {
            request(HttpMethod.POST_RAW);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null");
            }
            request(HttpMethod.POST);
        }
    }

    public final void put() {
        if (null == BODY) {
            request(HttpMethod.PUT_RAW);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null");
            }
            request(HttpMethod.PUT_RAW);
        }
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }

    public final void upload() {
        request(HttpMethod.UPLOAD);
    }

    public final void download() {
        new DownloadHandler(URL,REQUEST,DOWNLOAD_DIR,EXTENSION,NAME,SUCCESS,FAILURE,ERROR).handlerDownload();
    }

}
