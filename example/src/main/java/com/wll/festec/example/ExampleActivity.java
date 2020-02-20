package com.wll.festec.example;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;

import com.wll.latte.activitys.ProxyActivity;
import com.wll.latte.delegates.LatteDelegaret;
import com.wll.latte.ec.sign.SignUpDelegate;

public class ExampleActivity extends ProxyActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (null != actionBar) {
            actionBar.hide();
        }
    }

    @Override
    public LatteDelegaret setRootDelegarete() {
        return new SignUpDelegate();
    }
}
