package com.wll.latte.ec.main;

import android.graphics.Color;

import com.wll.latte.bottom.BaseBottomDelegate;
import com.wll.latte.bottom.BottomItemDelegate;
import com.wll.latte.bottom.BottomTabBean;
import com.wll.latte.bottom.ItemBuilder;
import com.wll.latte.ec.main.discover.DiscoverDelegate;
import com.wll.latte.ec.main.index.IndexDelegate;
import com.wll.latte.ec.main.shoppingcart.ShoppingCartDelegate;
import com.wll.latte.ec.main.sort.SortDelegate;
import com.wll.latte.ec.main.user.UserDelegate;

import java.util.LinkedHashMap;

/**
 * @author wanglili
 * @description:
 * @date : 2020-02-25 14:32
 */
public class EcBottomDelegate extends BaseBottomDelegate {
    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}", "首页"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}", "分类"), new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}", "发现"), new DiscoverDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new ShoppingCartDelegate());
        items.put(new BottomTabBean("{fa-user}", "我的"), new UserDelegate());
        return builder.addItem(items).build();
    }

    @Override
    public int setIndexDelegate() {
        //默认为主页
        return 0;
    }

    @Override
    public int setClickCorlor() {
        return Color.parseColor("#ffff8800");
    }
}
