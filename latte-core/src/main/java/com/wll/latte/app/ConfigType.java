package com.wll.latte.app;

/**
 * @author wanglili
 * @description: 唯一的单例 ，枚举类型 只加载一次
 * @date : 2020-02-14 15:41
 */
public enum ConfigType {
    API_HOST,//配置网络请求的域名
    APPLATION_CONTEXT,//全局的上下文
    CONFIG_READY,//初始化是否完成
    ICON,//存储字体

}