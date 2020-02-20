package com.wll.latte.ui.launcher;

/**
 * @author wanglili
 * @description: 有没有登录过接口回调 登录过直接进入主页面，没有登录进入注册页面
 * @date : 2020-02-20 18:17
 */
public interface ILauncherListenner {
    void onLauncherFinish(OnLauncherFinishTag tag);
}
