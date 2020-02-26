package com.wll.latte.ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.google.android.material.textfield.TextInputEditText;
import com.wll.latte.delegates.LatteDelegate;
import com.wll.latte.ec.R;
import com.wll.latte.ec.R2;
import com.wll.latte.net.RestClient;
import com.wll.latte.net.callback.IError;
import com.wll.latte.net.callback.IFailure;
import com.wll.latte.net.callback.ISuccess;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author wanglili
 * @description: 注册
 * @date : 2020-02-19 15:34
 */
public class SignUpDelegate extends LatteDelegate {
    @BindView(R2.id.edit_sign_up_name)
    TextInputEditText editSignUpName;
    @BindView(R2.id.edit_sign_up_phone)
    TextInputEditText editSignUpPhone;
    @BindView(R2.id.edit_sign_up_email)
    TextInputEditText editSignUpEmail;
    @BindView(R2.id.edit_sign_up_pwd)
    TextInputEditText editSignUpPwd;
    @BindView(R2.id.edit_sign_up_re_pwd)
    TextInputEditText editSignUpRePwd;
    @BindView(R2.id.btn_sign_up)
    Button btnSignUp;
    @BindView(R2.id.tv_to_sign_in)
    AppCompatTextView tvToSignIn;
    private ISignListener mISignListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener){
            mISignListener = (ISignListener) activity;
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
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
        String name = editSignUpName.getText().toString();
        String phone = editSignUpPhone.getText().toString();
        String email = editSignUpEmail.getText().toString();
        String pwd = editSignUpPwd.getText().toString();
        String rePwd = editSignUpRePwd.getText().toString();
        boolean isPass = true;
        if (name.isEmpty()) {
            editSignUpName.setError("请输入姓名");
            isPass = false;
        } else {
            editSignUpName.setError(null);
        }
        if (email.isEmpty() || Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editSignUpEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            editSignUpEmail.setError(null);
        }

        if (phone.isEmpty() || phone.length() != 11) {
            editSignUpPhone.setError("手机号格式错误");
            isPass = false;
        } else {
            editSignUpPhone.setError(null);
        }

        if (pwd.isEmpty() || pwd.length() < 6) {
            editSignUpPwd.setError("请填写至少6为密码");
            isPass = false;
        } else {
            editSignUpPwd.setError(null);
        }
        if (rePwd.isEmpty() || rePwd.length() < 6) {
            editSignUpRePwd.setError("密码验证错误");
            isPass = false;
        } else {
            editSignUpRePwd.setError(null);
        }
        return isPass;

    }

    @OnClick({R2.id.btn_sign_up, R2.id.tv_to_sign_in})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.btn_sign_up) {
            if (checkForm()) {
                    //验证通过
            RestClient.builder()
                    .url("http://mock.fulingjie.com/mock/data/user_profile.json")
                    .params("name", editSignUpName.getText().toString())
                    .params("email", editSignUpEmail.getText().toString())
                    .params("phone", editSignUpPhone.getText().toString())
                    .params("password", editSignUpPwd.getText().toString())
                    .loader(getContext())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
//                            Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                            SignHandler.onSignUP(response,mISignListener);

                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure() {
                            Toast.makeText(getContext(), "onFailure", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .error(new IError() {
                        @Override
                        public void onError(int code, String msg) {
                            Toast.makeText(getContext(), "onError", Toast.LENGTH_SHORT).show();

                        }
                    })
                    .build()
                    .post();
            }
        } else if (id == R.id.tv_to_sign_in) {
            //去登录
            start(new SignInDelegate());
        }
    }
}
