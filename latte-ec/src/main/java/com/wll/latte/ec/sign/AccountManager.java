package com.wll.latte.ec.sign;

import com.wll.latte.util.storage.LattePreference;

/**
 * @author wanglili
 * @description: 管理用户信息的额类
 * @date : 2020-02-20 17:44
 */
public class AccountManager {
    /**
     * 登录状态
     */
    private enum SignTag {
        SIGN_TAG
    }

    /**
     * 设置登录状态
     *
     * @param state
     */
    public static void setSignState(boolean state) {
        LattePreference.setAppFlag(SignTag.SIGN_TAG.name(), state);
    }

    /**
     * 是否登录
     */
    public static boolean isSignIn() {
        return LattePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    /**
     * 检查是否登录过
     *
     * @param checker
     */
    public static void checkAccount(IUserChecker checker) {
        if (isSignIn()) {
            //登录过
            checker.onSignIn();
        } else {
            //没有登录过
            checker.onNotSignIn();
        }
    }
}
