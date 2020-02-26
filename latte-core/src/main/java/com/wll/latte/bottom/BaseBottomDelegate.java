package com.wll.latte.bottom;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.joanzapata.iconify.widget.IconTextView;
import com.wll.latte.R;
import com.wll.latte.R2;
import com.wll.latte.delegates.LatteDelegate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * @author wanglili
 * @description: 底部基础设置
 * @date : 2020-02-24 15:41
 */
public abstract class BaseBottomDelegate extends LatteDelegate implements View.OnClickListener {
    @BindView(R2.id.bottom_bar_delegate_container)
    ConstraintLayout bottomBarDelegateContainer;
    @BindView(R2.id.bottom_bar)
    LinearLayoutCompat bottomBar;

    //把bean 和 item结合起来
    private final ArrayList<BottomItemDelegate> ITEMS_DELEGATES = new ArrayList<>();//子fragment
    private final ArrayList<BottomTabBean> TAB_BEANS = new ArrayList<>();//子icon
    //存储 bean 和 item结合起来
    private final LinkedHashMap<BottomTabBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();


    private int mCurrentDelegate = 0;//计数
    private int mIndexDelegate = 0;//初始值
    private int mClickedColor = Color.RED;//点击的颜色

    //给map赋值
    public abstract LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder);

    @Override
    public Object setLayout() {
        return R.layout.delegate_bottom;
    }

    //设置选择fragment
    public abstract int setIndexDelegate();

    //设置选择的items的color
    @ColorInt
    public abstract int setClickCorlor();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //给indexdelegate赋值
        mIndexDelegate = setIndexDelegate();
        if (setClickCorlor() != 0) {
            mClickedColor = setClickCorlor();
        }
        //构造iteam
        final ItemBuilder builder = ItemBuilder.builder();
        LinkedHashMap<BottomTabBean, BottomItemDelegate> items = setItems(builder);
        ITEMS.putAll(items);
        /**
         * 设置数据
         */
        for (Map.Entry<BottomTabBean, BottomItemDelegate> item : ITEMS.entrySet()) {
            final BottomTabBean key = item.getKey();
            final BottomItemDelegate value = item.getValue();
            //设置icon
            TAB_BEANS.add(key);
            //设置 frgament
            ITEMS_DELEGATES.add(value);
        }
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final int size = ITEMS.size();
        //遍历每一个item
        for (int i = 0; i < size; i++) {
            LayoutInflater.from(getContext()).inflate(R.layout.bottom_item_icon_text_layout, bottomBar);
            final RelativeLayout item = (RelativeLayout) bottomBar.getChildAt(i);
            //设置每个item的点击事件
            item.setTag(i);
            item.setOnClickListener(this);
            //拿到底部布局的子view
            IconTextView itemIcon = (IconTextView) item.getChildAt(0);
            AppCompatTextView itemTV = (AppCompatTextView) item.getChildAt(1);
            //初始化底部数据
            BottomTabBean bottomTabBean = TAB_BEANS.get(i);
            itemTV.setText(bottomTabBean.getTitle());//文字
            itemIcon.setText(bottomTabBean.getIcon());//图片文字
            if (i == mIndexDelegate) {
                //点击后的icon
                itemTV.setTextColor(mClickedColor);
                itemIcon.setTextColor(mClickedColor);
            }
        }
        //初始化fragment的数据
        final SupportFragment[] delegateArray = ITEMS_DELEGATES.toArray(new SupportFragment[size]);
        //设置数据
        loadMultipleRootFragment(R.id.bottom_bar_delegate_container, mIndexDelegate, delegateArray);
    }

    /**
     * 重置颜色
     */
    private void resetColort() {
        int childCount = bottomBar.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final RelativeLayout item = (RelativeLayout) bottomBar.getChildAt(i);
            IconTextView itemIcon = (IconTextView) item.getChildAt(0);
            AppCompatTextView itemTV = (AppCompatTextView) item.getChildAt(1);
            itemIcon.setTextColor(Color.GRAY);
            itemTV.setTextColor(Color.GRAY);
        }
    }

    @Override
    public void onClick(View view) {
        final int tag = (int) view.getTag();
        resetColort();
        final RelativeLayout item = (RelativeLayout) view;
        IconTextView itemIcon = (IconTextView) item.getChildAt(0);
        AppCompatTextView itemTV = (AppCompatTextView) item.getChildAt(1);
        itemIcon.setTextColor(mClickedColor);
        itemTV.setTextColor(mClickedColor);
        showHideFragment(ITEMS_DELEGATES.get(tag), ITEMS_DELEGATES.get(mCurrentDelegate));
        //注意先后次序
        mCurrentDelegate = tag;
    }
}
