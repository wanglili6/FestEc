package com.wll.latte.ui.refresh;

import android.widget.Toast;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.wll.latte.app.Latte;
import com.wll.latte.net.RestClient;
import com.wll.latte.net.callback.IError;
import com.wll.latte.net.callback.IFailure;
import com.wll.latte.net.callback.ISuccess;

/**
 * @author wanglili
 * @description: 刷新助手
 * @date : 2020-02-25 15:51
 */
public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener {
    private final SwipeRefreshLayout REFRESH_LAYOUT;

    //传入layout
    public RefreshHandler(SwipeRefreshLayout swipeRefreshLayout) {
        this.REFRESH_LAYOUT = swipeRefreshLayout;
        //监听滑动事件
        REFRESH_LAYOUT.setOnRefreshListener(this);
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
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        //解析数据
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
