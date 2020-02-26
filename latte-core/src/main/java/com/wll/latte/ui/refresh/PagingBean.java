package com.wll.latte.ui.refresh;

/**
 * @author wanglili
 * @description: 存放分页数据
 * @date : 2020-02-26 16:25
 */
public final class PagingBean {
    //当前第几页
    private int mPageIndex = 0;
    //一共多少
    private int mTotal = 0;
    //一页显示多少数据
    private int mPageSize = 0;
    //当前显示了多少数据
    private int mCurrentCount = 0;
    //加载延迟
    private long mDelayed = 0;


    public int getPageIndex() {
        return mPageIndex;
    }

    public PagingBean setPageIndex(int mPageIndex) {
        this.mPageIndex = mPageIndex;
        return this;
    }

    public int getmTotal() {
        return mTotal;
    }

    public PagingBean setTotal(int mTotal) {
        this.mTotal = mTotal;
        return this;
    }

    public int getPageSize() {
        return mPageSize;
    }

    public PagingBean setPageSize(int mPageSize) {
        this.mPageSize = mPageSize;
        return this;
    }

    public int getCurrentCount() {
        return mCurrentCount;
    }

    public PagingBean setCurrentCount(int mCurrentCount) {
        this.mCurrentCount = mCurrentCount;
        return this;
    }

    public long getDelayed() {
        return mDelayed;
    }

    public PagingBean setDelayed(long mDelayed) {
        this.mDelayed = mDelayed;
        return this;
    }

    //第一次加载要调用的方法
    PagingBean addIndex() {
        mPageIndex++;
        return this;
    }
}
