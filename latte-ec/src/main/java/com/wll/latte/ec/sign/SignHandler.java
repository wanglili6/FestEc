package com.wll.latte.ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wll.latte.ec.database.DataBaseManager;
import com.wll.latte.ec.database.UserProfile;

/**
 * @author wanglili
 * @description: 登录注册帮助类
 * @date : 2020-02-20 17:25
 */
public class SignHandler {
    public static void onSignUP(String response) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long user = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");


        final UserProfile profile = new UserProfile(user, name, avatar, gender, address);
        //插入数据
        DataBaseManager.getInstance().getDao().insert(profile);
    }
}
