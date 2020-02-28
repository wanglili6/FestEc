package com.wll.latte.ec.main.sort.content;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.wll.latte.delegates.LatteDelegate;
import com.wll.latte.ec.R;
import com.wll.latte.ec.R2;
import com.wll.latte.net.RestClient;
import com.wll.latte.net.callback.IError;
import com.wll.latte.net.callback.IFailure;
import com.wll.latte.net.callback.ISuccess;
import com.wll.latte.ui.recycler.MultipleItemBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author wanglili
 * @description: 容器页面
 * @date : 2020-02-27 17:31
 */
public class ContentDelegate extends LatteDelegate {

    private static final String ARG_CONTENT_ID = "CONTENT_ID";
    @BindView(R2.id.rv_content_list)
    RecyclerView rvContentList;
    private int mContentid = -1;

    List<SectionBean> mData = null;

    public static ContentDelegate newInstance(int contentid) {
        final Bundle args = new Bundle();
        args.putInt(ARG_CONTENT_ID, contentid);
        ContentDelegate contentDelegate = new ContentDelegate();
        contentDelegate.setArguments(args);
        return contentDelegate;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (null != arguments) {
            mContentid = arguments.getInt(ARG_CONTENT_ID);
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_list_content;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rvContentList.setLayoutManager(staggeredGridLayoutManager);

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .url("sort_content_data_1.json?contentId=" + mContentid)
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        //解析数据
                        mData = new SectionDataConverter().covert(response);
                        SectionAdapter sectionAdapter = new SectionAdapter(R.layout.item_section_content, R.layout.item_section_header, mData);
                        rvContentList.setAdapter(sectionAdapter);

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
