package com.wll.latte.ui.launcher;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * @author wanglili
 * @description: 创建 LauncherHolder
 * @date : 2020-02-19 10:39
 */
public class LauncherHolderCreator implements CBViewHolderCreator {
    @Override
    public Object createHolder() {
        return new LauncherHolder();
    }
}
