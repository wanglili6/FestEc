package com.wll.festec.example.generators;


import com.wll.latte.annotations.annotations.EntryGenerator;
import com.wll.latte.wechat.templates.WXEntryTemplate;

@SuppressWarnings("unused")
@EntryGenerator(
        packageName = "com.wll.festec",
        entryTemplate =  WXEntryTemplate.class
)
public interface WeChatEntry {

}
