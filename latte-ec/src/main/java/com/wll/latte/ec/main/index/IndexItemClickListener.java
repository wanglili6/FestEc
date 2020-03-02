package com.wll.latte.ec.main.index;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.wll.latte.delegates.bottom.LatteDelegate;

/**
 * @author wanglili
 * @description: 首页点击事件
 * @date : 2020-02-26 23:50
 */
public class IndexItemClickListener extends SimpleClickListener {
    private final LatteDelegate delegaret;

    private IndexItemClickListener(LatteDelegate delegaret) {
        this.delegaret = delegaret;
    }

    public static SimpleClickListener create(LatteDelegate delegaret) {
        return new IndexItemClickListener(delegaret);
    }

    @Override
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        GoodsDetailDelegate goodsDetailDelegate = GoodsDetailDelegate.create();
        delegaret.start(goodsDetailDelegate);
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

    }


}
