package com.wll.latte.ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.wll.latte.delegates.LatteDelegaret;
import com.wll.latte.ec.R;
import com.wll.latte.ec.sign.AccountManager;
import com.wll.latte.ec.sign.IUserChecker;
import com.wll.latte.ui.launcher.ILauncherListenner;
import com.wll.latte.ui.launcher.LauncherHolderCreator;
import com.wll.latte.ui.launcher.OnLauncherFinishTag;
import com.wll.latte.ui.launcher.ScrollLauncherTag;
import com.wll.latte.util.storage.LattePreference;

import java.util.ArrayList;

/**
 * @author wanglili
 * @description: 滑动的banner
 * @date : 2020-02-19 10:22
 */
public class LauncherScrollDelegate extends LatteDelegaret implements OnItemClickListener {
    private ConvenientBanner<Integer> mConvenientBanner = null;
    private static ArrayList<Integer> INTEGERS = new ArrayList<>();

    @Override
    public Object setLayout() {
        mConvenientBanner = new ConvenientBanner<>(getContext());
        return mConvenientBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initBanner();
    }


    private ILauncherListenner mILauncherListenner;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListenner) {
            mILauncherListenner = (ILauncherListenner) activity;
        }
    }
    private void initBanner() {
        INTEGERS.add(R.mipmap.launcher_01);
        INTEGERS.add(R.mipmap.launcher_02);
        INTEGERS.add(R.mipmap.launcher_03);
        INTEGERS.add(R.mipmap.launcher_04);
        INTEGERS.add(R.mipmap.launcher_05);
        mConvenientBanner
                .setPages(new LauncherHolderCreator(), INTEGERS)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .setCanLoop(true);
    }


    @Override
    public void onItemClick(int position) {
        if (position == INTEGERS.size() - 1) {
            //点击的是最后一个，增加一个标记
            LattePreference.setAppFlag(ScrollLauncherTag.HAS_DIRST_LAUNCHER_APP.name(), true);
            //检查是否登录
            //检查用户是否登录
            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    if (null != mILauncherListenner) {
                        mILauncherListenner.onLauncherFinish(OnLauncherFinishTag.SIGNED);
                    }
                }

                @Override
                public void onNotSignIn() {
                    if (null != mILauncherListenner) {
                        mILauncherListenner.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
                    }
                }
            });
        }

    }
}
