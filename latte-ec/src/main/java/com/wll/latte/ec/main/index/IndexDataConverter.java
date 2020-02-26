package com.wll.latte.ec.main.index;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wll.latte.ui.recycler.DataConverter;
import com.wll.latte.ui.recycler.ItemType;
import com.wll.latte.ui.recycler.MultipleFields;
import com.wll.latte.ui.recycler.MultipleItemBean;
import com.wll.latte.ui.recycler.MultipleItemBeanBulider;

import java.util.ArrayList;

/**
 * @author wanglili
 * @description: 首页的数据转换
 * @date : 2020-02-26 00:30
 */
public class IndexDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemBean> covert() {
        JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONArray("data");
        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final String imageUrl = data.getString("imageUrl");
            final String text = data.getString("text");
            final int spanSize = data.getInteger("spanSize");
            final int goodsId = data.getInteger("goodsId");
            final JSONArray banners = data.getJSONArray("banners");
            final ArrayList<String> bannerList = new ArrayList<>();
            int type = 0;
            if (null==imageUrl&&text!=null){
                type = ItemType.TEXT;
            }else if (null!=imageUrl&&text==null){
                type = ItemType.IMAGE;
            }else if (null!=imageUrl){
                type = ItemType.TEXT_IMAGE;
            }else if (null!=banners){
                type = ItemType.BANNER;
                int bannerSize  = banners.size();
                for (int j = 0; j < bannerSize; j++) {
                    String banner = banners.getString(j);
                    bannerList.add(banner);
                }
            }
            //初始化数据bean
            final MultipleItemBean multipleItemBean = MultipleItemBean.builder()
                    .setField(MultipleFields.ITEM_TYPE,type)
                    .setField(MultipleFields.SPAN_SIZE,spanSize)
                    .setField(MultipleFields.ID,goodsId)
                    .setField(MultipleFields.TEXT,text)
                    .setField(MultipleFields.IMAGE_URL,imageUrl)
                    .setField(MultipleFields.BANNERS,bannerList)
                    .bulider();
            beanArrayList.add(multipleItemBean);
        }
        return beanArrayList;
    }
}
