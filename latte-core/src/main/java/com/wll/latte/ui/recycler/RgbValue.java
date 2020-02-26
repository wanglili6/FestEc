package com.wll.latte.ui.recycler;

import com.google.auto.value.AutoValue;

/**
 * @author wanglili
 * @description: 存放颜色的类
 * @date : 2020-02-26 23:09
 */
@AutoValue
public abstract class RgbValue  {
    public abstract int red();
    public abstract int green();
    public abstract int blue();

    public static RgbValue create(int red,int green,int blue) {
        return new  AutoValue_RgbValue(red,green,blue);
    }
}
