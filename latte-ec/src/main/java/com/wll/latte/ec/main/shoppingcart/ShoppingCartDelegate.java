package com.wll.latte.ec.main.shoppingcart;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.joanzapata.iconify.widget.IconTextView;
import com.wll.latte.bottom.BottomItemDelegate;
import com.wll.latte.ec.R;
import com.wll.latte.ec.R2;
import com.wll.latte.net.RestClient;
import com.wll.latte.net.callback.IError;
import com.wll.latte.net.callback.IFailure;
import com.wll.latte.net.callback.ISuccess;
import com.wll.latte.ui.recycler.MultipleItemBean;

import java.util.ArrayList;

import butterknife.BindView;

import static com.blankj.utilcode.util.BarUtils.getStatusBarHeight;

/**
 * @author wanglili
 * @description:
 * @date : 2020-02-25 14:34
 */
public class ShoppingCartDelegate extends BottomItemDelegate {
    @BindView(R2.id.tv_top_shop_cart_clear)
    AppCompatTextView tvTopShopCartClear;
    @BindView(R2.id.tv_top_shop_cart_title)
    AppCompatTextView tvTopShopCartTitle;
    @BindView(R2.id.tv_top_shop_cart_remove_select)
    AppCompatTextView tvTopShopCartRemoveSelect;
    @BindView(R2.id.rv_shop_cart)
    RecyclerView rvShopCart;
    @BindView(R2.id.icon_shop_carr_select_all)
    IconTextView iconShopCarrSelectAll;
    @BindView(R2.id.rl_shop_cart_toolbar)
    RelativeLayout rlShopCartToolbar;

    @Override
    public Object setLayout() {
        return R.layout.delegate_shopping_cart;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        rlShopCartToolbar.setPadding(0, getStatusBarHeight(), 0, 0);

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .url("shop_cart_data.json")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        ArrayList<MultipleItemBean> data = new ShopCartDataConverter().setJsonData(response).covert();
                        ShopCartAdapter shopCartAdapter = new ShopCartAdapter(data);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                        rvShopCart.setLayoutManager(linearLayoutManager);
                        rvShopCart.setAdapter(shopCartAdapter);
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                }).build().get();

    }
}
