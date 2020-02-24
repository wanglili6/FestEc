package com.wll.latte.bottom;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;

import com.wll.latte.delegates.LatteDelegaret;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author wanglili
 * @description:
 * @date : 2020-02-24 15:41
 */
public abstract class BaseBottomDelegate extends LatteDelegaret {

    //把bean 和 item结合起来
    private final ArrayList<BottomItemDelegate> ITEMS_DELEGATES = new ArrayList<>();//子fragment
    private final ArrayList<BottomTabBean> TAB_BEANS = new ArrayList<>();//子icon
    //存储 bean 和 item结合起来
    private final LinkedHashMap<BottomTabBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();
    private int mCurrentDelegate = 0;//计数
    private int mIndexDelegate = 0;//初始值
    private int mClickedColor = Color.RED;//点击的颜色

    //给map赋值
    public abstract LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder);

    //设置选择fragment
    public abstract int setIndexDelegate();

    //设置选择的items的color
    @ColorInt
    public abstract int setClickCorlor();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //给indexdelegate赋值
        mIndexDelegate = setIndexDelegate();
        if (setClickCorlor() != 0) {
            mClickedColor = setClickCorlor();
        }
        //构造iteam
        final ItemBuilder builder = ItemBuilder.builder();
        LinkedHashMap<BottomTabBean, BottomItemDelegate> items = setItems(builder);
        ITEMS.putAll(items);

        for (Map.Entry<BottomTabBean, BottomItemDelegate> item : ITEMS.entrySet()) {
            final BottomTabBean key = item.getKey();
            final BottomItemDelegate value = item.getValue();
            //设置icon
            TAB_BEANS.add(key);
            //设置 frgament
            ITEMS_DELEGATES.add(value);
        }


    }
}
