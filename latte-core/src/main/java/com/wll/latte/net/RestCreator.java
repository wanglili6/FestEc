package com.wll.latte.net;


import com.wll.latte.app.ConfigKeys;
import com.wll.latte.app.Latte;

import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @author wanglili
 * @description: 获取Restservice
 * @date : 2020-02-17 11:59
 */
public class RestCreator {


    public static RestService getRestService() {
        return RestServiceHoldel.REST_SERVICE_CLINET;
    }

    //单例创建retrofit请求
    private static final class RetrofitHoldel {
        //获取base_url
        private static final String BASE_URL = (String) Latte.getConfigurations().get(ConfigKeys.API_HOST.name());
        //创建Retrofit
        private static final Retrofit RETROFIT_CLINET = new Retrofit.Builder()
                .baseUrl(BASE_URL).addConverterFactory(ScalarsConverterFactory.create())
                .client(OKHttpHolder.OK_HTTP_CLIENT_CLINET)//记载okhttp
                .build();
    }

    /**
     * 对OKHttp的惰性初始化
     */
    private static final class OKHttpHolder {
        //TIME_OUT
        private static final int TIME_OUT = 60;
        private static final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();
        //获取拦截起
        private static final ArrayList<Interceptor> INTERCEPTORS = (ArrayList<Interceptor>) Latte.getConfigurations().get(ConfigKeys.INTERCEPTOR);

        private static OkHttpClient.Builder addInterceptor() {
            if (null != INTERCEPTORS || !INTERCEPTORS.isEmpty()) {
                for (Interceptor interceptor : INTERCEPTORS) {
                    BUILDER.addInterceptor(interceptor);
                }
            }
            return BUILDER;
        }

        //创建Retrofit
        private static final OkHttpClient OK_HTTP_CLIENT_CLINET = addInterceptor()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    /**
     * 加载retrofit的实现接口restservice
     */
    private static final class RestServiceHoldel {
        //创建Retrofit
        private static final RestService REST_SERVICE_CLINET =
                RetrofitHoldel.RETROFIT_CLINET.create(RestService.class);
    }


    /**
     * 加载retrofit的实现接口restservice
     */
    private static final class ParamsHoler {
        //全局参数
        public static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }

    public static WeakHashMap<String, Object> getParams() {
        return ParamsHoler.PARAMS;
    }
}
