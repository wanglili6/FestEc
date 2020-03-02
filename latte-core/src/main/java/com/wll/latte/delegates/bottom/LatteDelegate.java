package com.wll.latte.delegates.bottom;

/**
 * @author wanglili
 * @description: 主要使用的fragment
 * @date : 2020-02-16 16:59
 */
public abstract class LatteDelegate extends PermissionsCheckerDelegate {

    public <T extends LatteDelegate> T getParentDelegate() {
        return (T) getParentFragment();
    }

}
