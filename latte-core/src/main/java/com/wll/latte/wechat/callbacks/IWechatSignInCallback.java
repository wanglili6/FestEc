package com.wll.latte.wechat.callbacks;

/**
 * @author wanglili
 * @description: 微信登录的回调接口
 * @date : 2020-02-23 17:45
 */
public interface IWechatSignInCallback {
    void onSignInSuccess(String userInfo);
}
