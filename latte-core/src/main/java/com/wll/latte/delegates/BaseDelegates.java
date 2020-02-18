package com.wll.latte.delegates;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.wll.latte.activitys.ProxyActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * @author wanglili
 * @description: 基础的fragment --Delegate
 * @date : 2020-02-16 16:28
 */
public abstract class BaseDelegates extends SwipeBackFragment {

    private Unbinder mUnbinder = null;

    //设置布局
    public abstract Object setLayout();

    //绑定view
    public abstract void onBindView(@Nullable Bundle savedInstanceState, View rootView);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootView;
        if (setLayout() instanceof Integer) {
            //是否是layoutID
            rootView = inflater.inflate((Integer) setLayout(), container, false);
        } else if (setLayout() instanceof View) {
            rootView = (View) setLayout();
        } else {
            throw new ClassCastException("setlayput() type must be int or view");
        }
        mUnbinder = ButterKnife.bind(this, rootView);
        onBindView(savedInstanceState, rootView);
        return rootView;
    }

    public final ProxyActivity getProxyActivity(){
        return (ProxyActivity) _mActivity;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        //解除绑定
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }
}
