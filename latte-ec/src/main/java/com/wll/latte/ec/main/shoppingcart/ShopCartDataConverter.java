package com.wll.latte.ec.main.shoppingcart;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wll.latte.ui.recycler.DataConverter;
import com.wll.latte.ui.recycler.MultipleFields;
import com.wll.latte.ui.recycler.MultipleItemBean;

import java.util.ArrayList;

/**
 * @author wanglili
 * @description : 数据转换
 * @date : 2020-03-03 00:34
 */
public class ShopCartDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemBean> covert() {
        ArrayList<MultipleItemBean> datalist = new ArrayList<>();
        //获取列表数据
        JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONArray("data");
        int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            JSONObject dataArrayJSONObject = dataArray.getJSONObject(i);
            String title = dataArrayJSONObject.getString("title");
            String desc = dataArrayJSONObject.getString("desc");
            String thumb = dataArrayJSONObject.getString("thumb");
            int id = dataArrayJSONObject.getInteger("id");
            int count = dataArrayJSONObject.getInteger("count");
            double price = dataArrayJSONObject.getDouble("price");
            //初始化数据bean
            final MultipleItemBean multipleItemBean = MultipleItemBean.builder()
                    .setField(MultipleFields.ITEM_TYPE, ShopCartItemType.SHOP_CART_ITEM)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.IMAGE_URL, thumb)
                    .setField(ShopCartItemFields.TITLE, title)
                    .setField(ShopCartItemFields.DESC, desc)
                    .setField(ShopCartItemFields.COUNT, count)
                    .setField(ShopCartItemFields.PRICE, price)
                    .bulider();
            datalist.add(multipleItemBean);
        }
        return datalist;
    }
}
