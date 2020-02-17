package com.wll.festec.example;

import com.wll.latte.activitys.ProxyActivity;
import com.wll.latte.delegates.LatteDelegaret;

public class ExampleActivity extends ProxyActivity {

    @Override
    public LatteDelegaret setRootDelegarete() {
        return new ExampleDelegate();
    }
}
