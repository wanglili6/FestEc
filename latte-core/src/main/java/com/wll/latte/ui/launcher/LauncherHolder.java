package com.wll.latte.ui.launcher;

import android.content.Context;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;

import com.bigkoo.convenientbanner.holder.Holder;


/**
 * @author wanglili
 * @description:LauncherHolder
 * @date : 2020-02-19 10:40
 */
public class LauncherHolder implements Holder<Integer> {
    private AppCompatImageView mImageView = null;


    @Override
    public View createView(Context context) {
        mImageView = new AppCompatImageView(context);
        return mImageView;
    }

    @Override
    public void UpdateUI(Context context, int position, Integer data) {
        mImageView.setBackgroundResource(data);
    }
}
