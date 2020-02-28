// Generated code from Butter Knife. Do not modify!
package com.wll.latte.ec.main.sort.content;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.wll.latte.ec.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ContentDelegate_ViewBinding implements Unbinder {
  private ContentDelegate target;

  @UiThread
  public ContentDelegate_ViewBinding(ContentDelegate target, View source) {
    this.target = target;

    target.rvContentList = Utils.findRequiredViewAsType(source, R.id.rv_content_list, "field 'rvContentList'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ContentDelegate target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rvContentList = null;
  }
}
