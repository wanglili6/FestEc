package com.wll.latte.ec.main.sort.list;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wll.latte.delegates.bottom.LatteDelegate;
import com.wll.latte.ec.R;
import com.wll.latte.ec.R2;
import com.wll.latte.ec.main.sort.SortDelegate;
import com.wll.latte.net.RestClient;
import com.wll.latte.net.callback.IError;
import com.wll.latte.net.callback.IFailure;
import com.wll.latte.net.callback.ISuccess;
import com.wll.latte.ui.recycler.MultipleItemBean;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @author wanglili
 * @description: 垂直的列表页面
 * @date : 2020-02-27 17:07
 */
public class VerticalListDelegate extends LatteDelegate {
    @BindView(R2.id.rv_vertical_menu_list)
    RecyclerView rvVerticalMenuList;

    @Override
    public Object setLayout() {
        return R.layout.delegate_vertical_list;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    private void initRecycleView() {

        rvVerticalMenuList.setLayoutManager(new LinearLayoutManager(getContext()));
        //屏蔽动画
        rvVerticalMenuList.setItemAnimator(null);

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRecycleView();
        RestClient.builder()
                .url("sort_list_data.json")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        //解析数据
                        ArrayList<MultipleItemBean> covert = new VerticalDataConverter().setJsonData(response).covert();
                        SortRecyclerAdapter sortRecyclerAdapter = new SortRecyclerAdapter(covert, (SortDelegate) getParentDelegate());
                        rvVerticalMenuList.setAdapter(sortRecyclerAdapter);

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
