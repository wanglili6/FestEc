package com.wll.latte.ec.main.discover;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;

import com.wll.latte.bottom.BottomItemDelegate;
import com.wll.latte.delegates.web.WebDelegateImpl;
import com.wll.latte.ec.R;
import com.wll.latte.ec.R2;

import butterknife.BindView;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

import static com.blankj.utilcode.util.BarUtils.getStatusBarHeight;

/**
 * @author wanglili
 * @description: 发现
 * @date : 2020-02-25 14:34
 */
public class DiscoverDelegate extends BottomItemDelegate {
    @BindView(R2.id.tv_title)
    AppCompatTextView tvTitle;
    @BindView(R2.id.tool_bar)
    Toolbar toolBar;
    @BindView(R2.id.rl_tool_bar)
    RelativeLayout rlToolBar;

    @Override
    public Object setLayout() {
        return R.layout.delegate_discover;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        tvTitle.setText("发现");
        // 解决状态栏和标题栏重叠的问题
        rlToolBar.setPadding(0, getStatusBarHeight(), 0, 0);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        WebDelegateImpl webDelegate = WebDelegateImpl.create("index.html");
        webDelegate.setTopFragment(getParentDelegate());
        getSupportDelegate().loadRootFragment(R.id.web_discovery_container, webDelegate);
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
