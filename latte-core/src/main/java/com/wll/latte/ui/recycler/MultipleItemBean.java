package com.wll.latte.ui.recycler;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;

/**
 * @author wanglili
 * @description: 存放数据的bean
 * @date : 2020-02-25 17:26
 */
public class MultipleItemBean implements MultiItemEntity {
    //取放数据的队列，内存不足的时候要释放
    private final ReferenceQueue<LinkedHashMap<Object, Object>> ITEM_QUEUE = new ReferenceQueue<>();
    //存放数据的map
    private final LinkedHashMap<Object, Object> MULTIPE_FILELDS = new LinkedHashMap<>();
    //
    private final SoftReference<LinkedHashMap<Object, Object>> FIELDS_REFERENCE
            = new SoftReference<LinkedHashMap<Object, Object>>(MULTIPE_FILELDS, ITEM_QUEUE);

    public MultipleItemBean(LinkedHashMap<Object, Object> fields) {
        FIELDS_REFERENCE.get().putAll(fields);

    }

    @Override
    public int getItemType() {
        return (int) FIELDS_REFERENCE.get().get(MultipleFields.ITEM_TYPE);
    }

    /**
     * 获取其他数据的方法
     */

    public final <T> T getField(Object key) {
        return (T) FIELDS_REFERENCE.get().get(key);
    }

    public final LinkedHashMap<?, ?> getFields() {
        return FIELDS_REFERENCE.get();
    }

    public final MultipleItemBean setField(Object key, Object value) {
        FIELDS_REFERENCE.get().put(key, value);
        return this;
    }
    public static  MultipleItemBeanBulider builder(){
        return new MultipleItemBeanBulider();
    }

}
