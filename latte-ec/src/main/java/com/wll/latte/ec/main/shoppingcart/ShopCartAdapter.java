package com.wll.latte.ec.main.shoppingcart;

import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatTextView;

import com.joanzapata.iconify.widget.IconTextView;
import com.wll.latte.ec.R;
import com.wll.latte.ec.main.sort.SortDelegate;
import com.wll.latte.ui.recycler.ItemType;
import com.wll.latte.ui.recycler.MultipleFields;
import com.wll.latte.ui.recycler.MultipleItemBean;
import com.wll.latte.ui.recycler.MultipleRecyclerAdapter;
import com.wll.latte.ui.recycler.MultipleViewHolder;
import com.wll.latte.util.image.ImageLoaderManager;

import java.util.List;

/**
 * @author wanglili
 * @description: 购物车适配器
 * @date : 2020-03-03 00:50
 */
public class ShopCartAdapter extends MultipleRecyclerAdapter {

    public ShopCartAdapter(List<MultipleItemBean> data) {
        super(data);
        //添加垂直的菜单布局
        addItemType(ShopCartItemType.SHOP_CART_ITEM, R.layout.item_shop_cart);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemBean multipleItemBean) {
        switch (holder.getItemViewType()) {
            case ShopCartItemType.SHOP_CART_ITEM:
                //取出所有的值
                int id = multipleItemBean.getField(MultipleFields.ID);
                String imgUrl = multipleItemBean.getField(MultipleFields.IMAGE_URL);
                String title = multipleItemBean.getField(ShopCartItemFields.TITLE);
                String desc = multipleItemBean.getField(ShopCartItemFields.DESC);
                int count = multipleItemBean.getField(ShopCartItemFields.COUNT);
                double price = multipleItemBean.getField(ShopCartItemFields.PRICE);
                //取出所有控件
                ImageView imgCart = holder.getView(R.id.img_item_shop_cart);
                AppCompatTextView tvTitle = holder.getView(R.id.tv_item_shop_cart_title);
                AppCompatTextView tvDesc = holder.getView(R.id.tv_item_shop_cart_desc);
                AppCompatTextView tvCount = holder.getView(R.id.tv_item_shop_cart_count);
                AppCompatTextView tvPrice = holder.getView(R.id.tv_item_shop_cart_price);
                IconTextView iconMinus = holder.getView(R.id.icon_item_minus);
                IconTextView iconPlus = holder.getView(R.id.icon_item_plus);
                //赋值
                tvTitle.setText(title);
                tvDesc.setText(desc);
                tvCount.setText(String.valueOf(count));
                tvPrice.setText(String.valueOf(price));
                //加载图片
                ImageLoaderManager.loadImage(mContext, imgUrl, imgCart);


                break;
            default:
                break;
        }
    }
}
