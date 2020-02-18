package com.wll.festec.example;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.wll.festec.R;
import com.wll.latte.app.Latte;
import com.wll.latte.ec.icon.FontEcMoudle;
import com.wll.latte.net.interceptors.DebugInterceptor;

/**
 * @author wanglili
 * @description:
 * @date : 2020-02-14 16:06
 */
public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcMoudle())
                .withApiHost("http://127.1.10/")
                .withInterceptor(new DebugInterceptor("index", R.raw.test))
                .configure();
    }
}
