package com.wll.latte.ec.main.user;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.wll.latte.bottom.BottomItemDelegate;
import com.wll.latte.ec.R;

/**
 * @author wanglili
 * @description:
 * @date : 2020-02-25 14:34
 */
public class UserDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_user;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
