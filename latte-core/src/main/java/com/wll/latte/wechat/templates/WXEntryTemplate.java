package com.wll.latte.wechat.templates;


import com.wll.latte.wechat.BaseWXEntryActivity;
import com.wll.latte.wechat.LatteWeChat;

public class WXEntryTemplate extends BaseWXEntryActivity {

    @Override
    protected void onResume() {
        super.onResume();
        //再次进来关闭掉
        finish();
        //去除动画
        overridePendingTransition(0,0);
    }
    @Override
    protected void onSignInSuccess(String userInfo) {
        LatteWeChat.getInstance().getIWechatSignInCallback().onSignInSuccess(userInfo);
    }
}
