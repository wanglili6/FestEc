package com.wll.latte.net.interceptors;

import java.io.IOException;
import java.util.LinkedHashMap;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author wanglili
 * @description: 基础拦截器
 * @date : 2020-02-18 17:49
 */
public abstract class BaseInterceptor implements Interceptor {
/**
 * 获取url的请求参数
 *
 * @param chain
 * @return
 */
protected LinkedHashMap<String, String> getUrlParameters(Chain chain){
final HttpUrl url=chain.request().url();
        //获取请求参数的个数
        int size=url.querySize();
final LinkedHashMap<String, String> params=new LinkedHashMap<>();
        for(int i=0;i<size; i++){
        params.put(url.queryParameterName(i),url.queryParameterValue(i));
        }
        return params;
        }

/**
 * 根据key获取value
 *
 * @param chain
 * @param key
 * @return
 */
protected String getUrlParameters(Chain chain,String key){
final Request request=chain.request();
        return request.url().queryParameter(key);
        }

/**
 * 获取body的请求参数
 *
 * @param chain
 * @return
 */
protected LinkedHashMap<String, String> getBodyParameters(Chain chain){
final FormBody formBody=(FormBody)chain.request().body();
        //获取请求参数的个数
        int size=formBody.size();
final LinkedHashMap<String, String> params=new LinkedHashMap<>();
        for(int i=0;i<size; i++){
        params.put(formBody.name(i),formBody.value(i));
        }
        return params;
        }

/**
 * 获取body的请求参数
 *
 * @param chain
 * @return
 */
protected String getBodyParameters(Chain chain,String key){
        return getBodyParameters(chain).get(key);
        }
        }
