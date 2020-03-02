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

import com.wll.latte.app.Latte;
import com.wll.latte.ec.R;
import com.wll.latte.ui.recycler.RgbValue;

/**
 * @author wanglili
 * @description: 透明的行为
 * @date : 2020-02-26 23:02
 */
public class TranslucentBehavior extends CoordinatorLayout.Behavior<Toolbar> {
    //注意这个变量一定要定义成类变量
    private int mOffset = 0;
    //延长滑动过程
    private static final int MORE = 100;
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

//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    @Override
//    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout,
//                                  @NonNull Toolbar child,
//                                  @NonNull View target,
//                                  int dx,
//                                  int dy,
//                                  @NonNull int[] consumed,
//                                  int type) {
//
//
//        //增加滑动的距离
//        mDistanceY += dy;
//        //toorbar的高度
//        int height = child.getHeight();
//        //当滑动时并且距离小于 toorbar 高度的时候，调整渐变色
//        if (mDistanceY > 0 && mDistanceY <= height) {
//            //设置颜色值
//            final float scale = (float) mDistanceY / height;
//            final float alphe = scale * 255;
//            child.setBackgroundColor(Color.argb((int)alphe, RGB_VALUE.red(), RGB_VALUE.green(), RGB_VALUE.blue()));
//        } else if (mDistanceY > height) {
//            child.setBackgroundColor(Color.rgb(RGB_VALUE.red(), RGB_VALUE.green(), RGB_VALUE.blue()));
//        }
//    }

    @Override
    public void onNestedScroll(
            @NonNull CoordinatorLayout coordinatorLayout
            , @NonNull Toolbar toolbar
            , @NonNull View target
            , int dxConsumed
            , int dyConsumed
            , int dxUnconsumed
            , int dyUnconsumed
            , int type) {
        final int startOffset = 0;
        final Context context = Latte.getApplicationContext();
        final int endOffset = context.getResources().getDimensionPixelOffset(R.dimen.header_height) + MORE;
        mOffset += dyConsumed;
        if (mOffset <= startOffset) {
            toolbar.getBackground().setAlpha(0);
        } else if (mOffset < endOffset) {
            final float percent = (float) (mOffset - startOffset) / endOffset;
            final int alpha = Math.round(percent * 255);
            toolbar.getBackground().setAlpha(alpha);
        } else if (mOffset >= endOffset) {
            toolbar.getBackground().setAlpha(255);
        }
    }
}
