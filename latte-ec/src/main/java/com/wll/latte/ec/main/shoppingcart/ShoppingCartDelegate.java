package com.wll.latte.ec.main.shoppingcart;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

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
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

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
    @BindView(R2.id.icon_shop_cart_select_all)
    IconTextView iconShopCarrSelectAll;
    @BindView(R2.id.rl_shop_cart_toolbar)
    RelativeLayout rlShopCartToolbar;
    @BindView(R2.id.tv_settlement)
    AppCompatTextView tvSettlement;
    private ShopCartAdapter shopCartAdapter;

    //记录当前点击的position,position是从1开始的也就是数据的从1开始的
    private int mCurrentCount = 0;
    //获取总的item的数量
    private int mTotalCount = 0;

    @Override
    public Object setLayout() {
        return R.layout.delegate_shopping_cart;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        rlShopCartToolbar.setPadding(0, getStatusBarHeight(), 0, 0);
        iconShopCarrSelectAll.setTag(0);
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
                        shopCartAdapter = new ShopCartAdapter(data);
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

    @OnClick({R2.id.icon_shop_cart_select_all, R2.id.tv_settlement, R2.id.tv_top_shop_cart_remove_select, R2.id.tv_top_shop_cart_clear})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.icon_shop_cart_select_all) {
            //全选
            selectAll();
        } else if (id == R.id.tv_top_shop_cart_remove_select) {
            //删除
            removeItem();

        } else if (id == R.id.tv_top_shop_cart_clear) {
            //清空
            clearAll();
        } else if (id == R.id.tv_item_shop_cart_desc) {
            //结算
        }
    }

    /*
    清空
     */
    private void clearAll() {
        shopCartAdapter.getData().clear();
        shopCartAdapter.notifyDataSetChanged();
    }

    /**
     * 删除
     */
    private void removeItem() {
        List<MultipleItemBean> data = shopCartAdapter.getData();
        //找到要删除的数据
        List<MultipleItemBean> deledBeanList = new ArrayList<>();
        for (MultipleItemBean multipleItemBean : data) {
            boolean isSelected = multipleItemBean.getField(ShopCartItemFields.IS_SELECTED);
            if (isSelected) {
                deledBeanList.add(multipleItemBean);
            }
        }
        //删除要删除的变量
        for (MultipleItemBean delbean : deledBeanList) {
            //获取删除的positoon
            int removePostion;
            int postion = delbean.getField(ShopCartItemFields.POSITION);
            if (postion > mCurrentCount - 1) {
                removePostion = postion - (mTotalCount - mCurrentCount);
            } else {
                removePostion = postion;
            }
            if (removePostion <= shopCartAdapter.getItemCount()) {
                // 移除bean
                shopCartAdapter.remove(removePostion);
                mCurrentCount = shopCartAdapter.getItemCount();
                shopCartAdapter.notifyItemRangeChanged(removePostion, shopCartAdapter.getItemCount());
            }

        }
    }

    /**
     * 全选
     */
    private void selectAll() {
        int tag = (int) iconShopCarrSelectAll.getTag();
        if (tag == 0) {
            iconShopCarrSelectAll.setTextColor(getContext().getResources().getColor(R.color.app_main));
            iconShopCarrSelectAll.setTag(1);
            shopCartAdapter.setIsSelectrdAll(true);

        } else {
            iconShopCarrSelectAll.setTextColor(getContext().getResources().getColor(R.color.gary));
            iconShopCarrSelectAll.setTag(0);
            shopCartAdapter.setIsSelectrdAll(false);
        }
        //更新recycleView的状态
        shopCartAdapter.notifyItemRangeChanged(0, shopCartAdapter.getItemCount());
    }
}
