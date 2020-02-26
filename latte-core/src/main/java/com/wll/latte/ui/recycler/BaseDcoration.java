package com.wll.latte.ui.recycler;

import androidx.annotation.ColorInt;

import com.choices.divider.Divider;
import com.choices.divider.DividerItemDecoration;

/**
 * @author wanglili
 * @description: 创建基础的Dcoration
 * @date : 2020-02-26 22:44
 */
public class BaseDcoration extends DividerItemDecoration {

    public BaseDcoration(@ColorInt int color ,int size){
        setDividerLookup(new DividerLookupImpl(color,size));
    }

    public static  BaseDcoration create(@ColorInt int color ,int size){
       return new BaseDcoration(color,size);
    }

}
