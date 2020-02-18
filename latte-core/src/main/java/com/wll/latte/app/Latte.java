package com.wll.latte.app;

import android.content.Context;

import java.util.HashMap;

/**
 * @author wanglili
 * @description: 对外的工具类
 * @date : 2020-02-14 15:36
 */
public class Latte {
    //初始化Configurator
    public static Configurator init(Context context) {
        getConfigurations().put(ConfigType.APPLATION_CONTEXT.name(), context.getApplicationContext());
        return Configurator.getInstance();

    }

    //获取存储信息的map
    public static  HashMap<String, Object> getConfigurations() {
        return Configurator.getInstance().getLatteConfigs();
    }

    //Context
    public static Context getApplicationContext() {
        Object o = getConfigurations().get(ConfigType.APPLATION_CONTEXT.name());
        return (Context) o;
    }
}
