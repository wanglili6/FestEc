package com.wll.latte.ec.main.index;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.joanzapata.iconify.widget.IconTextView;
import com.wll.latte.bottom.BottomItemDelegate;
import com.wll.latte.ec.R;
import com.wll.latte.ec.R2;
import com.wll.latte.ec.main.EcBottomDelegate;
import com.wll.latte.ui.recycler.BaseDcoration;
import com.wll.latte.ui.recycler.MultipleRecyclerAdapter;
import com.wll.latte.ui.refresh.RefreshHandler;

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
        refreshHandler = RefreshHandler.create(srlIndex, rvIndex, new IndexDataConverter());

    }

    private void initRecycleView() {
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4);
        rvIndex.setLayoutManager(gridLayoutManager);
        rvIndex.addItemDecoration(BaseDcoration.create(ContextCompat.getColor(getContext(), R.color.app_background), 5));
        final EcBottomDelegate ecBottomDelegate = getParentDelegate();
        rvIndex.addOnItemTouchListener(IndexItemClickListener.create(ecBottomDelegate));

    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayput();
        initRecycleView();
        refreshHandler.firstPage("index_data.json");
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
