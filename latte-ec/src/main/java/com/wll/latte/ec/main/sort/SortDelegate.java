package com.wll.latte.ec.main.sort;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;

import com.wll.latte.bottom.BottomItemDelegate;
import com.wll.latte.ec.R;
import com.wll.latte.ec.R2;
import com.wll.latte.ec.main.sort.content.ContentDelegate;
import com.wll.latte.ec.main.sort.list.VerticalListDelegate;
import com.wll.latte.util.log.LatteLogger;

import butterknife.BindView;

import static com.blankj.utilcode.util.BarUtils.getStatusBarHeight;


/**
 * @author wanglili
 * @description: 分类fragment
 * @date : 2020-02-25 14:34
 */
public class SortDelegate extends BottomItemDelegate {


    @BindView(R2.id.tv_title)
    AppCompatTextView tvTitle;
    @BindView(R2.id.tool_bar)
    Toolbar toolBar;
    @BindView(R2.id.rl_tool_bar)
    RelativeLayout rlToolBar;

    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        LatteLogger.d("statusBarHeight---" + getStatusBarHeight());
        tvTitle.setText("分类");
        rlToolBar.setPadding(0, getStatusBarHeight(), 0, 0);
    }

    /**
     * 懒加载
     *
     * @param savedInstanceState
     */
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        //加载跟布局
        VerticalListDelegate verticalListDelegate = new VerticalListDelegate();
        loadRootFragment(R.id.vertical_list_container, verticalListDelegate);
        //设置右侧fragment第一个分类显示，默认显示分类一
        loadRootFragment(R.id.sort_list_container, ContentDelegate.newInstance(1));
    }
}
