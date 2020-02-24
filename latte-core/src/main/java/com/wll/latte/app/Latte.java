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
        getConfigurations().put(ConfigKeys.APPLATION_CONTEXT.name(), context.getApplicationContext());
        return Configurator.getInstance();

    }

    //获取存储信息的map
    public static HashMap<Object, Object> getConfigurations() {
        return Configurator.getInstance().getLatteConfigs();
    }

    //获取存储信息的map
    public static Object getConfigurations(Enum<ConfigKeys> key) {
        return Configurator.getInstance().getLatteConfigs().get(key.name());
    }

    //Context
    public static Context getApplicationContext() {
        Object o = getConfigurations().get(ConfigKeys.APPLATION_CONTEXT.name());
        return (Context) o;
    }

    //Context
    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }
}
