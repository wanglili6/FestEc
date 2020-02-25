package com.wll.latte.ec.main.shoppingcart;

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
public class ShoppingCartDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_shopping_cart;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
