package com.wll.latte.ui.recycler;

import java.util.ArrayList;

/**
 * @author wanglili
 * @description: 数据转换的类
 * @date : 2020-02-25 17:25
 */
public abstract class DataConverter {

    protected final ArrayList<MultipleItemBean> beanArrayList = new ArrayList<>();
    //json数据
    private String mJsonData = null;

    //返回bean的数组
    public abstract ArrayList<MultipleItemBean> covert();

    //设置数据
    public DataConverter setJsonData(String json) {
        this.mJsonData = json;
        return this;
    }

    //获取数据
    protected String getJsonData() {
        if (null == mJsonData || mJsonData.isEmpty()) {
            return null;
        }
        return mJsonData;
    }


}


