package com.wll.latte.net.rx;

import android.content.Context;

import com.wll.latte.net.HttpMethod;
import com.wll.latte.net.RequestCallbacks;
import com.wll.latte.net.RestClientBuilder;
import com.wll.latte.net.RestCreator;
import com.wll.latte.net.RestService;
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

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * @author wanglili
 * @description: 网络请求的具体实现类
 * @date : 2020-02-17 10:24
 */
public class RxRestClient {
    //参数 URL 文件  传入的值  加载圈
    private final String URL;//地址
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();//参数
    private final RequestBody BODY;//请求体
    private final LoaderStyle LOADER_STYLE;//loader
    private final Context CONTEXT;//上下文
    private final File FILE;//文件

    public RxRestClient(String url,
                        Map<String, Object> params,
                        RequestBody body,
                        File file,
                        Context context,
                        LoaderStyle loaderStyle
    ) {
        this.URL = url;
        PARAMS.putAll(params);
        this.BODY = body;
        this.FILE = file;
        this.CONTEXT = context;
        this.LOADER_STYLE = loaderStyle;
    }

    //创建构造者
    public static RxRestClientBuilder builder() {
        return new RxRestClientBuilder();
    }

    //创建request方法 请求方法
    public Observable<String> request(HttpMethod method) {
        final RxRestService service = RestCreator.getRxRestService();
        Observable<String> observable = null;
        //展示loader
        if (null != LOADER_STYLE) {
            LatteLoader.showLoading(CONTEXT, LOADER_STYLE);
        }
        switch (method) {
            case GET:
                observable = service.get(URL, PARAMS);
                break;
            case POST:
                observable = service.post(URL, PARAMS);
                break;
            case POST_RAW://post原始数据下载
                observable = service.postRaw(URL, BODY);
                break;
            case PUT:
                observable = service.put(URL, PARAMS);
                break;
            case PUT_RAW:
                observable = service.putRaw(URL, BODY);
                break;
            case DELETE:
                observable = service.delete(URL, PARAMS);
                break;
            case UPLOAD://上传数据
                final RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                //创建文件请求体
                final MultipartBody.Part body = MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                observable = RestCreator.getRxRestService().upload(URL, body);
                break;

            default:
                break;
        }

        return observable;
    }

    //使用方法
    public final Observable<String> get() {
        return request(HttpMethod.GET);
    }


    public final Observable<String> post() {
        if (null == BODY) {
            return request(HttpMethod.POST_RAW);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null");
            }
            return request(HttpMethod.POST);
        }
    }

    public final Observable<String> put() {
        if (null == BODY) {
            return request(HttpMethod.PUT_RAW);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null");
            }
            return request(HttpMethod.PUT_RAW);
        }
    }

    public final Observable<String> delete() {
        return request(HttpMethod.DELETE);
    }

    public final Observable<String> upload() {
        return request(HttpMethod.UPLOAD);
    }

    public final Observable<ResponseBody> download() {
        final Observable<ResponseBody> responseBodyObservable = RestCreator.getRxRestService().download(URL, PARAMS);
        return responseBodyObservable;
    }

}
