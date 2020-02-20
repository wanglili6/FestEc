package com.wll.latte.activitys;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.ContentFrameLayout;

import com.wll.latte.R;
import com.wll.latte.app.Latte;
import com.wll.latte.delegates.LatteDelegaret;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * @author wanglili
 * @description: 容器activity
 * @date : 2020-02-16 16:23
 */
public abstract class ProxyActivity extends SupportActivity {
    //返回根delegate
    public abstract LatteDelegaret setRootDelegarete();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化视图
        initContainer(savedInstanceState);
    }

    /**
     * 初始化视图
     */
    private void initContainer(@Nullable Bundle savedInstanceState) {
        //跟容器
        @SuppressLint("RestrictedApi") final ContentFrameLayout container = new ContentFrameLayout(this);
        //传入ID
        container.setId(R.id.delegate_container);
        //设置view
        setContentView(container);
        //加载根Fragment
        if (null == savedInstanceState) {
            loadRootFragment(R.id.delegate_container, setRootDelegarete());
        }

    }

    /**
     * 优化
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //回收
        System.gc();
        System.runFinalization();
    }
}
