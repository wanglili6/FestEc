package com.wll.latte.net.callback;

/**
 * @author wanglili
 * @description: 失败接口
 * @date : 2020-02-17 15:02
 */
public interface IError {
    /**
     * 失败回调的方法
     */
    void onError(int code, String msg);
}
