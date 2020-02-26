package com.wll.latte.ui.banner;

import android.content.Context;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.wll.latte.R;
import com.wll.latte.app.Latte;
import com.wll.latte.util.image.ImageLoaderManager;

/**
 * @author wanglili
 * @description:
 * @date : 2020-02-26 16:04
 */
public class ImageHolder implements Holder<String> {

    private AppCompatImageView mImageView = null;


    @Override
    public View createView(Context context) {
        mImageView = new AppCompatImageView(context);
        return mImageView;
    }

    @Override
    public void UpdateUI(Context context, int position, String data) {
        ImageLoaderManager.loadImage(Latte.getApplicationContext(),data,mImageView);
    }
}
