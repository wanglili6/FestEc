package com.wll.latte.ui.recycler;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.GridLayoutManager;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.listener.GridSpanSizeLookup;
import com.wll.latte.R;
import com.wll.latte.app.Latte;
import com.wll.latte.ui.banner.BannerCreator;
import com.wll.latte.util.image.ImageLoaderManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wanglili
 * @description: RecyclerAdapter
 * @date : 2020-02-26 11:14
 */
public class MultipleRecyclerAdapter extends BaseMultiItemQuickAdapter<MultipleItemBean, MultipleViewHolder> implements GridSpanSizeLookup, OnItemClickListener {
    public MultipleRecyclerAdapter(List<MultipleItemBean> data) {
        super(data);
        init();
    }

    //取保banner只加载一次
    private boolean mIsInitBanner = false;

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemBean multipleItemBean) {
        //视图转换
        //取出数据
        final String text;
        final String imageUrl;
        final ArrayList<String> bannerList;

        switch (holder.getItemViewType()) {
            case ItemType.TEXT:
                text = multipleItemBean.getField(MultipleFields.TEXT);
                //设置数据
                holder.setText(R.id.text_single, text);
                break;
            case ItemType.IMAGE:
                imageUrl = multipleItemBean.getField(MultipleFields.IMAGE_URL);
                //设置数据
                ImageView imageView = holder.getView(R.id.img_single);
                ImageLoaderManager.loadImage(Latte.getApplicationContext(), imageUrl, imageView);

                break;
            case ItemType.TEXT_IMAGE:

                text = multipleItemBean.getField(MultipleFields.TEXT);
                //设置数据
                holder.setText(R.id.text_multiple, text);
                imageUrl = multipleItemBean.getField(MultipleFields.IMAGE_URL);
                //加载图片
                ImageLoaderManager.loadImage(Latte.getApplicationContext(), imageUrl, holder.getView(R.id.img_multiple));
                break;
            case ItemType.BANNER:
                if (!mIsInitBanner) {
                    bannerList = multipleItemBean.getField(MultipleFields.BANNERS);
                    final ConvenientBanner<String> convenientBanner = holder.getView(R.id.banner_recycler);
                    BannerCreator.setDefault(convenientBanner, bannerList, this);
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + holder.getItemViewType());
        }
    }

    /**
     * 只让继承的时候使用
     *
     * @param data
     * @return
     */
    protected MultipleRecyclerAdapter create(List<MultipleItemBean> data) {
        return new MultipleRecyclerAdapter(data);

    }


    /**
     * 转换后的数据
     *
     * @param dataConverter
     * @return
     */
    public static MultipleRecyclerAdapter create(DataConverter dataConverter) {
        return new MultipleRecyclerAdapter(dataConverter.covert());

    }

    @Override
    protected MultipleViewHolder createBaseViewHolder(View view) {
        return MultipleViewHolder.create(view);
    }

    /**
     * 设置不同的布局
     */
    private void init() {
        //设置不同的布局
        addItemType(ItemType.TEXT, R.layout.item_multiple_text);
        addItemType(ItemType.IMAGE, R.layout.item_multiple_image);
        addItemType(ItemType.TEXT_IMAGE, R.layout.item_multiple_image_text);
        addItemType(ItemType.BANNER, R.layout.item_multiple_banner);
        //设置宽度的监听
        setGridSpanSizeLookup(this);
//        //打开动画
        setAnimationEnable(true);
//        //多次执行动画
        setAnimationFirstOnly(false);

    }

    @Override
    public int getSpanSize(GridLayoutManager gridLayoutManager, int viewType, int position) {
        return getData().get(position).getField(MultipleFields.SPAN_SIZE);
    }

    @Override
    public void onItemClick(int position) {

    }
}
