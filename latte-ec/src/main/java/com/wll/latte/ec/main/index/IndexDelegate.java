package com.wll.latte.ec.main.index;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.joanzapata.iconify.widget.IconTextView;
import com.wll.latte.bottom.BottomItemDelegate;
import com.wll.latte.ec.R;
import com.wll.latte.ec.R2;
import com.wll.latte.net.RestClient;
import com.wll.latte.net.callback.IError;
import com.wll.latte.net.callback.IFailure;
import com.wll.latte.net.callback.ISuccess;
import com.wll.latte.ui.recycler.MultipleFields;
import com.wll.latte.ui.recycler.MultipleItemBean;
import com.wll.latte.ui.refresh.RefreshHandler;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @author wanglili
 * @description: 首页
 * @date : 2020-02-25 14:34
 */
public class IndexDelegate extends BottomItemDelegate {
    @BindView(R2.id.rv_index)
    RecyclerView rvIndex;
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout srlIndex;
    @BindView(R2.id.icon_index_scan)
    IconTextView iconIndexScan;
    @BindView(R2.id.icon_index_messages)
    IconTextView iconIndexMessages;
    @BindView(R2.id.tv_index)
    Toolbar tvIndex;
    private RefreshHandler refreshHandler = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        refreshHandler = new RefreshHandler(srlIndex);

        RestClient.builder()
                .url("index_data.json")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        //解析数据
                        IndexDataConverter indexDataConverter = new IndexDataConverter();
                        indexDataConverter.setJsonData(response);
                        ArrayList<MultipleItemBean> covert = indexDataConverter.covert();
                        String imageUrl = covert.get(1).getField(MultipleFields.IMAGE_URL);
                        Toast.makeText(getContext(), imageUrl, Toast.LENGTH_SHORT).show();
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

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayput();
//        refreshHandler.firstPage("index_data.json");
    }

    /**
     * 初始化上拉加载，下拉刷新
     */
    private void initRefreshLayput() {
        srlIndex.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light);
        srlIndex.setProgressViewOffset(true, 120, 300);

    }
}
