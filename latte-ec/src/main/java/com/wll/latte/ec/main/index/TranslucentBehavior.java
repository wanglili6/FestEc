package com.wll.latte.ec.main.index;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.wll.latte.ec.R;
import com.wll.latte.ui.recycler.RgbValue;

/**
 * @author wanglili
 * @description: 透明的行为
 * @date : 2020-02-26 23:02
 */
public class TranslucentBehavior extends CoordinatorLayout.Behavior<Toolbar> {
    //顶部的距离
    private int mDistanceY = 0;
    //颜色变化的速度
    private static final int SHOW_SPEED = 3;
    //定义变化的颜色
    private static final RgbValue RGB_VALUE = RgbValue.create(255, 124, 3);


    public TranslucentBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 滑动recycleview的进行行动
     *
     * @param parent
     * @param child
     * @param dependency
     * @return
     */
    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull Toolbar child, @NonNull View dependency) {
        return dependency.getId() == R.id.rv_index;
    }

    /**
     * 监听滑动事件
     *
     * @param coordinatorLayout
     * @param child
     * @param directTargetChild
     * @param target
     * @param axes
     * @param type
     * @return
     */
    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull Toolbar child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull Toolbar child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
        //增加滑动的距离
        mDistanceY += dy;
        //toorbar的高度
        int height = child.getHeight();
        //当滑动时并且距离小于 toorbar 高度的时候，调整渐变色
        if (mDistanceY > 0 && mDistanceY <= height) {
            //设置颜色值
            final float scale = (float) mDistanceY / height;
            final float alphe = scale * 255;
            child.setBackgroundColor(Color.argb((int)alphe, RGB_VALUE.red(), RGB_VALUE.green(), RGB_VALUE.blue()));
        } else if (mDistanceY > height) {
            child.setBackgroundColor(Color.rgb(RGB_VALUE.red(), RGB_VALUE.green(), RGB_VALUE.blue()));
        }
    }
}
