package com.wll.festec.example;

import com.wll.latte.activitys.ProxyActivity;
import com.wll.latte.delegates.LatteDelegaret;
import com.wll.latte.ec.launcher.LauncherDelegate;
import com.wll.latte.ec.launcher.LauncherScrollDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    public LatteDelegaret setRootDelegarete() {
        return new LauncherScrollDelegate();
    }
}
