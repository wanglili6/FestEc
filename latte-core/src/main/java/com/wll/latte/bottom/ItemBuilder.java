package com.wll.latte.bottom;

import java.util.LinkedHashMap;

/**
 * @author wanglili
 * @description: item构造器
 * @date : 2020-02-24 17:46
 */
public class ItemBuilder {

    private final LinkedHashMap<BottomTabBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();

    /**
     * 工厂模式
     *
     * @return
     */
    static ItemBuilder builder() {
        return new ItemBuilder();
    }

    public final ItemBuilder addItem(BottomTabBean bean, BottomItemDelegate itemDelegate) {
        ITEMS.put(bean, itemDelegate);
        return this;
    }

    public final ItemBuilder addItem(LinkedHashMap<BottomTabBean, BottomItemDelegate> items) {
        ITEMS.putAll(items);
        return this;
    }

    public final LinkedHashMap<BottomTabBean, BottomItemDelegate> build() {
        return ITEMS;
    }
}
