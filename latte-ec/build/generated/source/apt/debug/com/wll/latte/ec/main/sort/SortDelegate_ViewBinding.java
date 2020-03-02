// Generated code from Butter Knife. Do not modify!
package com.wll.latte.ec.main.sort;

import android.view.View;
import android.widget.RelativeLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.wll.latte.ec.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SortDelegate_ViewBinding implements Unbinder {
  private SortDelegate target;

  @UiThread
  public SortDelegate_ViewBinding(SortDelegate target, View source) {
    this.target = target;

    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", AppCompatTextView.class);
    target.toolBar = Utils.findRequiredViewAsType(source, R.id.tool_bar, "field 'toolBar'", Toolbar.class);
    target.rlToolBar = Utils.findRequiredViewAsType(source, R.id.rl_tool_bar, "field 'rlToolBar'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SortDelegate target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvTitle = null;
    target.toolBar = null;
    target.rlToolBar = null;
  }
}
