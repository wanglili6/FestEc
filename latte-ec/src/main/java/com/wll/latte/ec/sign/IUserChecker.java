package com.wll.latte.ec.sign;

/**
 * @author wanglili
 * @description: 检查用户信息的接口，用户信息
 * @date : 2020-02-20 17:41
 */
public interface IUserChecker {
    void onSignIn();
    void onNotSignIn();
}
