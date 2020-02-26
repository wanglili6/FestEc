package com.wll.latte.ui.refresh;

import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.wll.latte.app.Latte;
import com.wll.latte.delegates.LatteDelegate;
import com.wll.latte.net.RestClient;
import com.wll.latte.net.callback.IError;
import com.wll.latte.net.callback.IFailure;
import com.wll.latte.net.callback.ISuccess;
import com.wll.latte.ui.recycler.DataConverter;
import com.wll.latte.ui.recycler.MultipleRecyclerAdapter;

/**
 * @author wanglili
 * @description: 刷新助手
 * @date : 2020-02-25 15:51
 */
public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener {
    private final SwipeRefreshLayout REFRESH_LAYOUT;
    private final PagingBean BEAN;
    private final RecyclerView RECYCLER_VIEW;
    private MultipleRecyclerAdapter mAdapter = null;
    private final DataConverter dataConverter;

    public MultipleRecyclerAdapter getmAdapter() {
        return mAdapter;
    }

    public void setmAdapter(MultipleRecyclerAdapter mAdapter) {
        this.mAdapter = mAdapter;
    }

    //传入layout
    public RefreshHandler(SwipeRefreshLayout swipeRefreshLayout, RecyclerView recyclerView, DataConverter converter, PagingBean pagingBean) {
        this.REFRESH_LAYOUT = swipeRefreshLayout;
        this.RECYCLER_VIEW = recyclerView;
        this.dataConverter = converter;
        this.BEAN = pagingBean;
        //监听滑动事件
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }

    public static RefreshHandler create(SwipeRefreshLayout swipeRefreshLayout, RecyclerView recyclerView, DataConverter converter) {
        return new RefreshHandler(swipeRefreshLayout, recyclerView, converter, new PagingBean());
    }

    private void refresh() {
        REFRESH_LAYOUT.setRefreshing(true);
        Latte.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //消失
                //网络请求
                REFRESH_LAYOUT.setRefreshing(false);
            }
        }, 2000);
    }

    @Override
    public void onRefresh() {
        refresh();
    }

    public void firstPage(String url) {
        BEAN.setDelayed(1000);
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        //解析数据
                        final JSONObject jsonObject = JSON.parseObject(response);
                        Integer total = jsonObject.getInteger("total");
                        Integer page_size = jsonObject.getInteger("page_size");
                        BEAN.setTotal(total).setPageSize(page_size);
                        //设置adapter
                        mAdapter = MultipleRecyclerAdapter.create(dataConverter.setJsonData(response));
                        RECYCLER_VIEW.setAdapter(mAdapter);
                        BEAN.addIndex();

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                }).failure(new IFailure() {
            @Override
            public void onFailure() {

            }
        }).build().get();

    }


}
