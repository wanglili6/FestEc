package com.wll.latte.ec.main.sort.list;

import android.graphics.Color;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;

import com.wll.latte.ec.R;
import com.wll.latte.ec.main.sort.SortDelegate;
import com.wll.latte.ec.main.sort.content.ContentDelegate;
import com.wll.latte.ui.recycler.ItemType;
import com.wll.latte.ui.recycler.MultipleFields;
import com.wll.latte.ui.recycler.MultipleItemBean;
import com.wll.latte.ui.recycler.MultipleRecyclerAdapter;
import com.wll.latte.ui.recycler.MultipleViewHolder;

import java.util.List;

/**
 * @author wanglili
 * @description: 适配器
 * @date : 2020-02-28 11:39
 */
public class SortRecyclerAdapter extends MultipleRecyclerAdapter {
    private SortDelegate sortDelegate = null;
    private int mPrePosition = 0;


    public SortRecyclerAdapter(List<MultipleItemBean> data, SortDelegate sortDelegate) {
        super(data);
        this.sortDelegate = sortDelegate;
        //添加垂直的菜单布局
        addItemType(ItemType.VERTICAL_MUNE_LIST, R.layout.item_vertical_mune_list);


    }

    @Override
    protected void convert(final MultipleViewHolder holder, final MultipleItemBean multipleItemBean) {
        int itemViewType = holder.getItemViewType();
        switch (itemViewType) {
            case ItemType.VERTICAL_MUNE_LIST:
                String name = multipleItemBean.getField(MultipleFields.TEXT);
                boolean isCheck = multipleItemBean.getField(MultipleFields.TAG);
                AppCompatTextView tvname = holder.getView(R.id.tv_list_content_name);
                View viewLine = holder.getView(R.id.view_line);
                View itemView = holder.itemView;
                tvname.setText(name);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //获取当前的角标
                        int adapterPosition = holder.getAdapterPosition();
                        if (mPrePosition != adapterPosition) {
                            //还原上一个
                            getData().get(mPrePosition).setField(MultipleFields.TAG, false);
                            notifyItemChanged(mPrePosition);
                            //更新选中的item
                            getData().get(adapterPosition).setField(MultipleFields.TAG, true);
                            notifyItemChanged(adapterPosition);
                            //mPrePosition = adapterPosition
                            //更新角标
                            mPrePosition = adapterPosition;
                            //获取ID 和右边的做交互
                            int id = getData().get(adapterPosition).getField(MultipleFields.ID);
                            showContent(id);
                        }

                    }
                });
                if (!isCheck) {
                    viewLine.setVisibility(View.INVISIBLE);
                    tvname.setTextColor(ContextCompat.getColor(mContext, R.color.we_chat_black));
                    itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.item_background));
                } else {
                    viewLine.setVisibility(View.VISIBLE);
                    tvname.setTextColor(ContextCompat.getColor(mContext, R.color.app_main));
                    viewLine.setBackgroundColor(ContextCompat.getColor(mContext, R.color.app_main));
                    itemView.setBackgroundColor(Color.WHITE);
                }


                break;
            default:
                break;
        }
    }

    private void showContent(int contentId) {
        final ContentDelegate contentDelegate = ContentDelegate.newInstance(contentId);
        swithContent(contentDelegate);

    }

    private void swithContent(ContentDelegate delegate) {
        ContentDelegate childFragment = sortDelegate.findChildFragment(ContentDelegate.class);
        if (null != childFragment) {
            //替换当前的delegate
            childFragment.replaceFragment(delegate, false);

        }

    }
}
