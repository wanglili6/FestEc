package com.wll.latte.ui.recycler;

import java.util.LinkedHashMap;
import java.util.WeakHashMap;

/**
 * @author wanglili
 * @description: MultipleItemBean, 构建者
 * @date : 2020-02-26 00:17
 */
public class MultipleItemBeanBulider {
    private static final LinkedHashMap<Object, Object> FIELDS = new LinkedHashMap<>();

    public MultipleItemBeanBulider() {
        //先清除之前数据
        FIELDS.clear();
    }

    /**
     * 设置itemType
     *
     * @param itemType
     * @return
     */
    public final MultipleItemBeanBulider setItemType(int itemType) {
        FIELDS.put(MultipleFields.ITEM_TYPE, itemType);
        return this;
    }

    /**
     * 设置Field
     *
     * @return
     */
    public final MultipleItemBeanBulider setField(Object key, Object value) {
        FIELDS.put(key, value);
        return this;
    }

    /**
     * 设置Field
     *
     * @return
     */
    public final MultipleItemBeanBulider setField(LinkedHashMap<Object, Object> map) {
        FIELDS.putAll(map);
        return this;
    }


    /**
     * 设置Field
     *
     * @return
     */
    public final MultipleItemBean bulider() {
        return new MultipleItemBean(FIELDS);
    }

}
