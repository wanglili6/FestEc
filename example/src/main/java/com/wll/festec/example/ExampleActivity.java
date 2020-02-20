package com.wll.festec.example;

import com.wll.latte.activitys.ProxyActivity;
import com.wll.latte.delegates.LatteDelegaret;
import com.wll.latte.ec.sign.SignUpDelegate;

public class ExampleActivity extends ProxyActivity {
    @Override
    public LatteDelegaret setRootDelegarete() {
        return new SignUpDelegate();
    }
}
