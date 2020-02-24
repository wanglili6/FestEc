package com.wll.festec.example.generators;


import com.wll.latte.annotations.annotations.AppRegisterGenerator;
import com.wll.latte.wechat.templates.AppRegisterTemplate;

@AppRegisterGenerator(
        packageName = "com.wll.festec",
        registerTemplate = AppRegisterTemplate.class)
public interface AppRegister {

}
