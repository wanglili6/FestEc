package com.wll.latte.bottom;

import android.icu.util.ValueIterator;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.wll.latte.R;
import com.wll.latte.delegates.LatteDelegaret;

/**
 * @author wanglili
 * @description: 每一个页面的fragment
 * @date : 2020-02-24 15:45
 */
public abstract class BottomItemDelegate extends LatteDelegaret implements View.OnKeyListener {
    private long mExitTime = 0;
    private static final int EXIT_TIME = 2000;

    @Override
    public void onResume() {
        super.onResume();
        final View rootView = getView();
        if (null != rootView) {
            rootView.setFocusableInTouchMode(true);
            rootView.requestFocus();
            rootView.setOnKeyListener(this);
        }
    }

    //监听一个key事件，点击返回的记录时间
    @Override
    public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
        if (keyCode == KeyEvent.KEYCODE_BACK && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - mExitTime) > mExitTime) {
                Toast.makeText(getContext(), "双击推出" + getString(R.string.app_name), Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                _mActivity.finish();
                if (mExitTime != 0) {
                    mExitTime = 0;
                }
            }
            return true;
        }
        return false;
    }
}
