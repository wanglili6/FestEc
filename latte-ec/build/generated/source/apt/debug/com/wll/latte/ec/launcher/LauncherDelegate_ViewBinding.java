// Generated code from Butter Knife. Do not modify!
package com.wll.latte.ec.launcher;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.AppCompatTextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wll.latte.ec.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LauncherDelegate_ViewBinding implements Unbinder {
  private LauncherDelegate target;

  private View view6e3;

  @UiThread
  public LauncherDelegate_ViewBinding(final LauncherDelegate target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.tv_launcher_timer, "field 'mTvLauncherTimer' and method 'onViewClicked'");
    target.mTvLauncherTimer = Utils.castView(view, R.id.tv_launcher_timer, "field 'mTvLauncherTimer'", AppCompatTextView.class);
    view6e3 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    LauncherDelegate target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mTvLauncherTimer = null;

    view6e3.setOnClickListener(null);
    view6e3 = null;
  }
}
