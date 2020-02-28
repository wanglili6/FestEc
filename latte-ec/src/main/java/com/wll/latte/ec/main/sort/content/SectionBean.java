package com.wll.latte.ec.main.sort.content;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * @author wanglili
 * @description:
 * @date : 2020-02-28 15:57
 */
public class SectionBean extends SectionEntity<SectionContentItemBean> {
    //是否更多
    private boolean mIsMore = false;
    private int mId = -1;

    public SectionBean(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public SectionBean(SectionContentItemBean sectionContentItemBean) {
        super(sectionContentItemBean);
    }

    public boolean isIsMore() {
        return mIsMore;
    }

    public void setIsMore(boolean mIsMore) {
        this.mIsMore = mIsMore;
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }
}
