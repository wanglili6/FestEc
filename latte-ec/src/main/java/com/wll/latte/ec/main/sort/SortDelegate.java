package com.wll.latte.ec.main.sort;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.wll.latte.bottom.BottomItemDelegate;
import com.wll.latte.ec.R;
import com.wll.latte.ec.main.sort.content.ContentDelegate;
import com.wll.latte.ec.main.sort.list.VerticalListDelegate;

/**
 * @author wanglili
 * @description: 分类fragment
 * @date : 2020-02-25 14:34
 */
public class SortDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

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
