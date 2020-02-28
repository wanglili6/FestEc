// Generated code from Butter Knife. Do not modify!
package com.wll.latte.ec.main.sort.list;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.wll.latte.ec.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class VerticalListDelegate_ViewBinding implements Unbinder {
  private VerticalListDelegate target;

  @UiThread
  public VerticalListDelegate_ViewBinding(VerticalListDelegate target, View source) {
    this.target = target;

    target.rvVerticalMenuList = Utils.findRequiredViewAsType(source, R.id.rv_vertical_menu_list, "field 'rvVerticalMenuList'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    VerticalListDelegate target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rvVerticalMenuList = null;
  }
}
