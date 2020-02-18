package com.wll.latte.util.timer;

import java.util.TimerTask;

/**
 * @author wanglili
 * @description:
 * @date : 2020-02-18 23:21
 */
public class BaseTimerTask extends TimerTask {
    private ITimerListener mITimerListener = null;

    public BaseTimerTask(ITimerListener mITimerListener) {
        this.mITimerListener = mITimerListener;
    }

    @Override
    public void run() {
        if (null != mITimerListener) {
            mITimerListener.onTimer();
        }

    }
}
