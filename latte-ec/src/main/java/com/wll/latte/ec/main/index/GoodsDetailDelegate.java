package com.wll.latte.ec.main.index;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.wll.latte.delegates.LatteDelegate;
import com.wll.latte.ec.R;

/**
 * @author wanglili
 * @description:
 * @date : 2020-02-27 00:37
 */
public class GoodsDetailDelegate extends LatteDelegate {
    public static GoodsDetailDelegate create(){
        return new GoodsDetailDelegate();
    }
    @Override
    public Object setLayout() {
        return R.layout.delegate_goods_detail;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
