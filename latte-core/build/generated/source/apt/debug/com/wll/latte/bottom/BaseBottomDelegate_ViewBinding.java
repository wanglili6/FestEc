// Generated code from Butter Knife. Do not modify!
package com.wll.latte.bottom;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.wll.latte.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BaseBottomDelegate_ViewBinding implements Unbinder {
  private BaseBottomDelegate target;

  @UiThread
  public BaseBottomDelegate_ViewBinding(BaseBottomDelegate target, View source) {
    this.target = target;

    target.bottomBarDelegateContainer = Utils.findRequiredViewAsType(source, R.id.bottom_bar_delegate_container, "field 'bottomBarDelegateContainer'", ConstraintLayout.class);
    target.bottomBar = Utils.findRequiredViewAsType(source, R.id.bottom_bar, "field 'bottomBar'", LinearLayoutCompat.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    BaseBottomDelegate target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.bottomBarDelegateContainer = null;
    target.bottomBar = null;
  }
}
