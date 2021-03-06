package com.wll.latte.app;

import android.app.Activity;
import android.os.Handler;

import androidx.annotation.NonNull;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;
import com.wll.latte.delegates.web.event.Event;
import com.wll.latte.delegates.web.event.EventManager;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/**
 * @author wanglili
 * @description: 管理类
 * @date : 2020-02-14 15:38
 */
public class Configurator {
    //创建一个存储信息的map
    //一开始是weakhasmap，据说weakhashhmap可以释放内存，不用的时候自行被回收
    private static final HashMap<Object, Object> LATTE_CONFIGS = new HashMap<>();
    //存储字体图标
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();
    //拦截器的集合
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();

    private static final Handler HANDLER = new Handler();

    private Configurator() {
        //设置初始化状态
        //ConfigKeys.APPLATION_CONTEXT.name()  --获取枚举的字符串
        LATTE_CONFIGS.put(ConfigKeys.APPLATION_CONTEXT.name(), false);
        LATTE_CONFIGS.put(ConfigKeys.HANDLER.name(), HANDLER);
    }

    //获取存储信息的map
    final HashMap<Object, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    //静态内部类的单例模式
    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    //获取单例--懒汉模式
    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    //配置完成--configreay副职为true
    public final void configure() {
        initIcons();
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY.name(), true);
    }

    //配置api_HOSt
    public final Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigKeys.API_HOST.name(), host);
        return this;
    }
    //配置web事件
    public Configurator withWebEvent(@NonNull String name, @NonNull Event event) {
        final EventManager manager = EventManager.getInstance();
        manager.addEvent(name, event);
        return this;
    }

    public Configurator withJavascriptInterface(@NonNull String name) {
        LATTE_CONFIGS.put(ConfigKeys.JAVASCRIPT_INTERFACE, name);
        return this;
    }

    //浏览器加载的HOST
    public Configurator withWebHost(@NonNull String host) {
        LATTE_CONFIGS.put(ConfigKeys.WEB_HOST, host);
        return this;
    }
    //初始化字体图标
    private void initIcons() {
        //是否初始化过
        if (ICONS.size() > 0) {
            //有字体，引用第一个字体
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }

    }

    //检查配置项是否完成
    private void checkConfigration() {
        //使变量变为不可变性
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigKeys.CONFIG_READY.name());
        if (!isReady) {
            //如果没有配置完成，抛出异常
            throw new RuntimeException("Configurator is not ready,call configure");
        }
    }

    //配置字体-添加字体
    public final Configurator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;
    }

    //配置拦截器
    public final Configurator withInterceptor(Interceptor interceptor) {
        INTERCEPTORS.add(interceptor);
        //放到存储信息里
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    //配置拦截器
    public final Configurator withInterceptor(ArrayList<Interceptor> interceptors) {
        INTERCEPTORS.addAll(interceptors);
        //放到存储信息里
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    //配置appid
    public final Configurator withWeChatAppId(String appId) {
        LATTE_CONFIGS.put(ConfigKeys.WE_CHAT_APPID, appId);
        return this;
    }

    //配置appSecret
    public final Configurator withWeChatSecret(String appSecret) {
        LATTE_CONFIGS.put(ConfigKeys.WE_CHAT_APP_SECIET, appSecret);
        return this;
    }

    //配置全局activity
    public final Configurator withActivity(Activity activity) {
        LATTE_CONFIGS.put(ConfigKeys.ACTIVITY, activity);
        return this;
    }

    //调用检查
    final <T> T getConfigration(Enum<ConfigKeys> key) {
        checkConfigration();
        return (T) LATTE_CONFIGS.get(key.name());
    }

}
