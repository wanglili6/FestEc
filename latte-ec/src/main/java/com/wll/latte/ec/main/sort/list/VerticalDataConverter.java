package com.wll.latte.ec.main.sort.list;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wll.latte.ui.recycler.DataConverter;
import com.wll.latte.ui.recycler.ItemType;
import com.wll.latte.ui.recycler.MultipleFields;
import com.wll.latte.ui.recycler.MultipleItemBean;

import java.util.ArrayList;

/**
 * @author wanglili
 * @description: 左边的数据解析
 * @date : 2020-02-28 11:23
 */
public class VerticalDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemBean> covert() {

        final ArrayList<MultipleItemBean> dataList = new ArrayList<>();
        JSONObject data = JSON.parseObject(getJsonData()).getJSONObject("data");
        final JSONArray jsonArray = data.getJSONArray("list");
        int size = jsonArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject jsonObject = jsonArray.getJSONObject(i);
            Integer id = jsonObject.getInteger("id");
            String name = jsonObject.getString("name");
            MultipleItemBean multipleItemBean
                    = MultipleItemBean.builder()
                    .setField(MultipleFields.ITEM_TYPE, ItemType.VERTICAL_MUNE_LIST)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.TEXT, name)
                    .setField(MultipleFields.TAG, false)//设置一个tag,标记有没有点击过
                    .bulider();
            dataList.add(multipleItemBean);
            //设置第一个被选中
            dataList.get(0).setField(MultipleFields.TAG,true);
        }
        return dataList;
    }
}
