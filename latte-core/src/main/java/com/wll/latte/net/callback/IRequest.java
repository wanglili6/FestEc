package com.wll.latte.net.callback;

/**
 * @author wanglili
 * @description:
 * @date : 2020-02-17 15:04
 */
public interface IRequest {
    //请求开始
    void onRequestStart();

    //请求结束
    void onRequestEnd();
}
