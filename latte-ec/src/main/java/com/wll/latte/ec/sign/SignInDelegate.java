package com.wll.latte.ec.sign;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.google.android.material.textfield.TextInputEditText;
import com.joanzapata.iconify.widget.IconTextView;
import com.wll.latte.app.Latte;
import com.wll.latte.delegates.LatteDelegaret;
import com.wll.latte.ec.R;
import com.wll.latte.ec.R2;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author wanglili
 * @description: 注册
 * @date : 2020-02-19 15:34
 */
public class SignInDelegate extends LatteDelegaret {


    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText editSignInEmail;
    @BindView(R2.id.edit_sign_in_pwd)
    TextInputEditText editSignInPwd;
    @BindView(R2.id.btn_sign_up)
    Button btnSignUp;
    @BindView(R2.id.tv_to_sign_in)
    AppCompatTextView tvToSignIn;
    @BindView(R2.id.icon_sign_in_wechat)
    IconTextView iconSignInWechat;

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    /**
     * 验证参数
     *
     * @return
     */
    private boolean checkForm() {
        String email = editSignInEmail.getText().toString();
        String pwd = editSignInPwd.getText().toString();
        boolean isPass = true;

        if (email.isEmpty() || Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editSignInEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            editSignInEmail.setError(null);
        }


        if (pwd.isEmpty() || pwd.length() < 6) {
            editSignInPwd.setError("请填写至少6为密码");
            isPass = false;
        } else {
            editSignInPwd.setError(null);
        }

        return isPass;

    }


    @OnClick({R2.id.btn_sign_up, R2.id.tv_to_sign_in, R2.id.icon_sign_in_wechat})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.btn_sign_up) {
            //去注册
            start(new SignUpDelegate());
        } else if (id == R.id.tv_to_sign_in) {
            //登录
            if (checkForm()) {
                Toast.makeText(getContext(), "验证通过", Toast.LENGTH_SHORT).show();
            }

        } else if (id == R.id.icon_sign_in_wechat) {
            //微信登录
        }
    }
}
