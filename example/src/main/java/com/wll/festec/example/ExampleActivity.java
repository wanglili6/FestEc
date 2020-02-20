package com.wll.festec.example;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;

import com.wll.latte.activitys.ProxyActivity;
import com.wll.latte.delegates.LatteDelegaret;
import com.wll.latte.ec.launcher.LauncherDelegate;
import com.wll.latte.ec.sign.ISignListener;
import com.wll.latte.ec.sign.SignInDelegate;
import com.wll.latte.ec.sign.SignUpDelegate;
import com.wll.latte.ui.launcher.ILauncherListenner;
import com.wll.latte.ui.launcher.OnLauncherFinishTag;

public class ExampleActivity extends ProxyActivity implements ISignListener, ILauncherListenner {
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
        return new LauncherDelegate();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:
                Toast.makeText(this, "用户登录了", Toast.LENGTH_SHORT).show();
                startWithPop(new ExampleDelegate());
                break;
            case NOT_SIGNED:
                Toast.makeText(this, "用户没有登录", Toast.LENGTH_SHORT).show();
                startWithPop(new SignUpDelegate());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + tag);
        }
    }
}
