package com.wll.latte.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author wanglili
 * @description: 管理类
 * @date : 2020-02-14 15:38
 */
public class Configurator {
    //创建一个存储信息的map
    //一开始是weakhasmap，据说weakhashhmap可以释放内存，不用的时候自行被回收
    private static final HashMap<String, Object> LATTE_CONFIGS = new HashMap<>();
    //存储字体图标
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();

    private Configurator() {
        //设置初始化状态
        //ConfigType.APPLATION_CONTEXT.name()  --获取枚举的字符串
        LATTE_CONFIGS.put(ConfigType.APPLATION_CONTEXT.name(), false);
    }

    //获取存储信息的map
    final HashMap<String, Object> getLatteConfigs() {
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
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
    }

    //配置api_HOSt
    public final Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(), host);
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
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY.name());
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

    //调用检查
    final <T> T getConfigration(Enum<ConfigType> key) {
        checkConfigration();
        return (T) LATTE_CONFIGS.get(key.name());
    }

}
