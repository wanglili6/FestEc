package com.wll.latte.ec.launcher;

import android.app.Activity;
import android.icu.text.MessageFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.wll.latte.delegates.LatteDelegaret;
import com.wll.latte.ec.R;
import com.wll.latte.ec.R2;
import com.wll.latte.ec.sign.AccountManager;
import com.wll.latte.ec.sign.IUserChecker;
import com.wll.latte.ui.launcher.ILauncherListenner;
import com.wll.latte.ui.launcher.OnLauncherFinishTag;
import com.wll.latte.ui.launcher.ScrollLauncherTag;
import com.wll.latte.util.storage.LattePreference;
import com.wll.latte.util.timer.BaseTimerTask;
import com.wll.latte.util.timer.ITimerListener;

import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @author wanglili
 * @description: 倒计时启动图
 * @date : 2020-02-18 23:20
 */
public class LauncherDelegate extends LatteDelegaret implements ITimerListener {

    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvLauncherTimer;
    //倒计时的类
    private Timer mTimer = null;

    private int mCount = 5;


    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }
    private ILauncherListenner mILauncherListenner;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListenner) {
            mILauncherListenner = (ILauncherListenner) activity;
        }
    }

    /**
     * 判断是否显示滑动页
     */
    private void checkIsShowScroll() {
        if (!LattePreference.getAppFlag(ScrollLauncherTag.HAS_DIRST_LAUNCHER_APP.name())) {
            //启动滑动页
            start(new LauncherScrollDelegate(), SINGLETASK);
        } else {
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

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (null != mTvLauncherTimer) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        mTvLauncherTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                        mCount--;
                        if (mCount < 0) {
                            if (null != mTimer) {
                                mTimer.cancel();
                                mTimer = null;
                                checkIsShowScroll();
                            }

                        }
                    }
                }
            }
        });
    }

    @OnClick(R2.id.tv_launcher_timer)
    public void onViewClicked() {
        if (null != mTimer) {
            mTimer.cancel();
            mTimer = null;
            checkIsShowScroll();
        }
    }

    /**
     * 初始化timer
     */
    private void initTimer() {
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task, 0, 1000);
    }
}
