package com.wll.latte.ec.main.sort.content;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wll.latte.ui.recycler.DataConverter;
import com.wll.latte.ui.recycler.MultipleItemBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wanglili
 * @description:
 * @date : 2020-02-28 15:57
 */
public class SectionDataConverter {
    final List<SectionBean> covert(String json) {
        ArrayList<SectionBean> dataList = new ArrayList<>();
        JSONObject jsonObject1 = JSON.parseObject(json);
        JSONArray data = jsonObject1.getJSONArray("data");
        int size = data.size();
        for (int i = 0; i < size; i++) {
            JSONObject dataJSONObject = data.getJSONObject(i);
            Integer id = dataJSONObject.getInteger("id");
            String title = dataJSONObject.getString("section");

            //添加title
            SectionBean sectionBean = new SectionBean(true, title);
            sectionBean.setId(id);
            sectionBean.setIsMore(true);
            dataList.add(sectionBean);

            //封装内容分类数据
            JSONArray goods = dataJSONObject.getJSONArray("goods");
            //商品分类内循环
            int size1 = goods.size();
            for (int j = 0; j < size1; j++) {
                JSONObject goodsJSONObject = goods.getJSONObject(j);
                Integer goods_id = goodsJSONObject.getInteger("goods_id");
                String goods_thumb = goodsJSONObject.getString("goods_thumb");
                String goods_name = goodsJSONObject.getString("goods_name");
                //获取内容
                SectionContentItemBean itemBean = new SectionContentItemBean();
                itemBean.setGoodId(goods_id);
                itemBean.setGoodThumb(goods_thumb);
                itemBean.setGoodName(goods_name);
                dataList.add(new SectionBean(itemBean));
            }
            //商品内容循环结束
        }
        //Section循环结束
        return dataList;
    }

}
