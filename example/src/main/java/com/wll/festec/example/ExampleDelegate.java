package com.wll.festec.example;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.wll.festec.R;
import com.wll.latte.delegates.bottom.LatteDelegate;
import com.wll.latte.net.RestClient;
import com.wll.latte.net.callback.IError;
import com.wll.latte.net.callback.IFailure;
import com.wll.latte.net.callback.ISuccess;

/**
 * @author wanglili
 * @description:
 * @date : 2020-02-16 17:13
 */
public class ExampleDelegate extends LatteDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        testRestCilent();
    }

    private void testRestCilent() {
        RestClient.builder()
                .url("http://127.0.0.1/index")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build()
                .get();
    }
}
