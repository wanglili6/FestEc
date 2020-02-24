package com.wll.festec.example.generators;


import com.wll.latte.annotations.annotations.PayEntryGenerator;
import com.wll.latte.wechat.templates.WXPayEntryTemplate;

@SuppressWarnings("unused")
@PayEntryGenerator(
        packageName = "com.wll.festec",
        payEntryTemplate = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {

}
