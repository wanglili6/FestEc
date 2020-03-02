package com.wll.latte.delegates.web.event;

import com.wll.latte.util.log.LatteLogger;

/**
 * @author wanglili
 * @description:
 * @date : 2020-03-02 14:00
 */
public class UndefindEvent extends Event {
    @Override
    public String execute(String params) {
        LatteLogger.d("UndefindEvent",params);
        return null;
    }
}
