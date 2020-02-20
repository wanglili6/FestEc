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
import com.wll.latte.ec.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SignUpDelegate_ViewBinding implements Unbinder {
  private SignUpDelegate target;

  private View view46d;

  private View view512;

  @UiThread
  public SignUpDelegate_ViewBinding(final SignUpDelegate target, View source) {
    this.target = target;

    View view;
    target.editSignUpName = Utils.findRequiredViewAsType(source, R.id.edit_sign_up_name, "field 'editSignUpName'", TextInputEditText.class);
    target.editSignUpPhone = Utils.findRequiredViewAsType(source, R.id.edit_sign_up_phone, "field 'editSignUpPhone'", TextInputEditText.class);
    target.editSignUpEmail = Utils.findRequiredViewAsType(source, R.id.edit_sign_up_email, "field 'editSignUpEmail'", TextInputEditText.class);
    target.editSignUpPwd = Utils.findRequiredViewAsType(source, R.id.edit_sign_up_pwd, "field 'editSignUpPwd'", TextInputEditText.class);
    target.editSignUpRePwd = Utils.findRequiredViewAsType(source, R.id.edit_sign_up_re_pwd, "field 'editSignUpRePwd'", TextInputEditText.class);
    view = Utils.findRequiredView(source, R.id.btn_sign_up, "field 'btnSignUp' and method 'onViewClicked'");
    target.btnSignUp = Utils.castView(view, R.id.btn_sign_up, "field 'btnSignUp'", Button.class);
    view46d = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_to_sign_in, "field 'tvToSignIn' and method 'onViewClicked'");
    target.tvToSignIn = Utils.castView(view, R.id.tv_to_sign_in, "field 'tvToSignIn'", AppCompatTextView.class);
    view512 = view;
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
    SignUpDelegate target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.editSignUpName = null;
    target.editSignUpPhone = null;
    target.editSignUpEmail = null;
    target.editSignUpPwd = null;
    target.editSignUpRePwd = null;
    target.btnSignUp = null;
    target.tvToSignIn = null;

    view46d.setOnClickListener(null);
    view46d = null;
    view512.setOnClickListener(null);
    view512 = null;
  }
}
