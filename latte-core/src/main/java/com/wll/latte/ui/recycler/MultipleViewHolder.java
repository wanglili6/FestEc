package com.wll.latte.ui.recycler;

import android.view.View;

import com.chad.library.adapter.base.viewholder.BaseViewHolder;

/**
 * @author wanglili
 * @description:ViewHolder
 * @date : 2020-02-26 11:15
 */
public class MultipleViewHolder extends BaseViewHolder {
    public MultipleViewHolder(View view) {
        super(view);
    }

    public static MultipleViewHolder create(View view) {
        return new MultipleViewHolder(view);
    }
}
