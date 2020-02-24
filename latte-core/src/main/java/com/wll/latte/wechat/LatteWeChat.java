package com.wll.latte.wechat;

import android.app.Activity;

import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.wll.latte.app.ConfigKeys;
import com.wll.latte.app.Latte;
import com.wll.latte.wechat.callbacks.IWechatSignInCallback;

/**
 * @author wanglili
 * @description: 微信wechat
 * @date : 2020-02-21 16:02
 */
public class LatteWeChat {
    //存储appID appsecret
    static final String APP_ID = (String) Latte.getConfigurations(ConfigKeys.WE_CHAT_APPID);

    static final String APP_SECIET = (String) Latte.getConfigurations(ConfigKeys.WE_CHAT_APP_SECIET);
    //微信api
    private final IWXAPI WXAPI;
    private IWechatSignInCallback mIWechatSignInCallback;

    private static final class Holder {
        private static final LatteWeChat LATTE_WE_CHAT = new LatteWeChat();
    }

    public static LatteWeChat getInstance() {
        return Holder.LATTE_WE_CHAT;
    }

    private LatteWeChat() {
        final Activity activity = (Activity) Latte.getConfigurations(ConfigKeys.ACTIVITY);
        WXAPI = WXAPIFactory.createWXAPI(activity, APP_ID, true);
        WXAPI.registerApp(APP_ID);

    }

    public IWXAPI getWXAPI() {
        return WXAPI;
    }

    /**
     * 赋值
     *
     * @param iWechatSignInCallback
     * @return
     */
    public LatteWeChat onSignInSuccess(IWechatSignInCallback iWechatSignInCallback) {
        this.mIWechatSignInCallback = iWechatSignInCallback;
        return this;
    }

    public IWechatSignInCallback getIWechatSignInCallback() {
        return mIWechatSignInCallback;
    }

    /**
     * 登录
     */
    public final void signIn() {
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "random_state";
        WXAPI.sendReq(req);
    }
}
