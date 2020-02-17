package com.wll.festec.example;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.wll.festec.R;
import com.wll.latte.delegates.LatteDelegaret;

/**
 * @author wanglili
 * @description:
 * @date : 2020-02-16 17:13
 */
public class ExampleDelegate extends LatteDelegaret {

    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
