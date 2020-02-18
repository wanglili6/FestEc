package com.wll.latte.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.wll.latte.app.Latte;

/**
 * @author wanglili
 * @description: 计算宽高的工具类
 * @date : 2020-02-18 12:04
 */
public class DimenUtil {
    /**
     * 获取屏幕的宽
     * @return
     */
    public static int getScreeWidth(){
      final   Resources resources = Latte.getApplication().getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    /**
     * 获取屏幕的高
     * @return
     */
    public static int getScreeHeight(){
        final   Resources resources = Latte.getApplication().getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.heightPixels;
    }
}
