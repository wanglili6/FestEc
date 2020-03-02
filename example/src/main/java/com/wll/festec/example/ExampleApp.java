package com.wll.festec.example;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.wll.festec.R;
import com.wll.latte.app.Latte;
import com.wll.latte.delegates.web.event.TestEvent;
import com.wll.latte.ec.database.DataBaseManager;
import com.wll.latte.ec.icon.FontEcModule;
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
        //初始化Latte
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withApiHost("http://mock.fulingjie.com/mock/data/")
                .withInterceptor(new DebugInterceptor("test", R.raw.test))
                .withWeChatAppId("wx2dc450271ada632e")
                .withWeChatSecret("")
                .withJavascriptInterface("Latte")
                .withWebEvent("test", new TestEvent())
                .configure();
        //今日头条适配
//        AutoSizeConfig.getInstance().getUnitsManager()
//                .setSupportSubunits(Subunits.PT);
        //打印log
        Logger.addLogAdapter(new AndroidLogAdapter());

        //初始化greendao
        DataBaseManager.getInstance().init(this);

        Stetho.initializeWithDefaults(this);
    }
}
