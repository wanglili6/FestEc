// Generated code from Butter Knife. Do not modify!
package com.wll.latte.ec.main.index;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.joanzapata.iconify.widget.IconTextView;
import com.wll.latte.ec.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class IndexDelegate_ViewBinding implements Unbinder {
  private IndexDelegate target;

  @UiThread
  public IndexDelegate_ViewBinding(IndexDelegate target, View source) {
    this.target = target;

    target.rvIndex = Utils.findRequiredViewAsType(source, R.id.rv_index, "field 'rvIndex'", RecyclerView.class);
    target.srlIndex = Utils.findRequiredViewAsType(source, R.id.srl_index, "field 'srlIndex'", SwipeRefreshLayout.class);
    target.iconIndexScan = Utils.findRequiredViewAsType(source, R.id.icon_index_scan, "field 'iconIndexScan'", IconTextView.class);
    target.iconIndexMessages = Utils.findRequiredViewAsType(source, R.id.icon_index_messages, "field 'iconIndexMessages'", IconTextView.class);
    target.tvIndex = Utils.findRequiredViewAsType(source, R.id.tv_index, "field 'tvIndex'", Toolbar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    IndexDelegate target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rvIndex = null;
    target.srlIndex = null;
    target.iconIndexScan = null;
    target.iconIndexMessages = null;
    target.tvIndex = null;
  }
}
