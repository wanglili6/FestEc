// Generated code from Butter Knife. Do not modify!
package com.wll.latte.ec.sign;

import android.view.View;
import android.widget.Button;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.AppCompatTextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.google.android.material.textfield.TextInputEditText;
import com.joanzapata.iconify.widget.IconTextView;
import com.wll.latte.ec.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SignInDelegate_ViewBinding implements Unbinder {
  private SignInDelegate target;

  private View view625;

  private View view6f8;

  private View view661;

  @UiThread
  public SignInDelegate_ViewBinding(final SignInDelegate target, View source) {
    this.target = target;

    View view;
    target.editSignInEmail = Utils.findRequiredViewAsType(source, R.id.edit_sign_in_email, "field 'editSignInEmail'", TextInputEditText.class);
    target.editSignInPwd = Utils.findRequiredViewAsType(source, R.id.edit_sign_in_pwd, "field 'editSignInPwd'", TextInputEditText.class);
    view = Utils.findRequiredView(source, R.id.btn_sign_in, "field 'btnSignIn' and method 'onViewClicked'");
    target.btnSignIn = Utils.castView(view, R.id.btn_sign_in, "field 'btnSignIn'", Button.class);
    view625 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_to_sign_up, "field 'tvToSignUp' and method 'onViewClicked'");
    target.tvToSignUp = Utils.castView(view, R.id.tv_to_sign_up, "field 'tvToSignUp'", AppCompatTextView.class);
    view6f8 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.icon_sign_in_wechat, "field 'iconSignInWechat' and method 'onViewClicked'");
    target.iconSignInWechat = Utils.castView(view, R.id.icon_sign_in_wechat, "field 'iconSignInWechat'", IconTextView.class);
    view661 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    SignInDelegate target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.editSignInEmail = null;
    target.editSignInPwd = null;
    target.btnSignIn = null;
    target.tvToSignUp = null;
    target.iconSignInWechat = null;

    view625.setOnClickListener(null);
    view625 = null;
    view6f8.setOnClickListener(null);
    view6f8 = null;
    view661.setOnClickListener(null);
    view661 = null;
  }
}
