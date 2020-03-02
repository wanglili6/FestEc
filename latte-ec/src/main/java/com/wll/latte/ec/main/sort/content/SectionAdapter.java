package com.wll.latte.ec.main.sort.content;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wll.latte.ec.R;
import com.wll.latte.util.image.ImageLoaderManager;

import java.util.List;

/**
 * @author wanglili
 * @description:
 * @date : 2020-02-28 16:18
 */
public class SectionAdapter extends BaseSectionQuickAdapter<SectionBean, BaseViewHolder> {


    public SectionAdapter(int layoutResId, int sectionHeadResId, List<SectionBean> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder baseViewHolder, SectionBean sectionBean) {
        baseViewHolder.setText(R.id.tv_section_header, sectionBean.header);
        baseViewHolder.setVisible(R.id.tv_section_more, sectionBean.isIsMore());
        //设置more的点击事件
        baseViewHolder.addOnClickListener(R.id.tv_section_more);

    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, SectionBean sectionBean) {
        //iteam返回的类型
        final String goodThumb = sectionBean.t.getGoodThumb();
        final String goodName = sectionBean.t.getGoodName();
        final int goodid = sectionBean.t.getGoodId();
        final SectionContentItemBean sectionContentItemBean = sectionBean.t;
        baseViewHolder.setText(R.id.tv_section, goodName);
        //设置图片
        AppCompatImageView imageView = baseViewHolder.getView(R.id.iv_section);
        ImageLoaderManager.loadImage(mContext, goodThumb, imageView);

    }
}
