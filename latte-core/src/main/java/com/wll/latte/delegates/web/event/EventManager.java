package com.wll.latte.delegates.web.event;

import androidx.annotation.NonNull;

import java.util.HashMap;

/**
 * @author wanglili
 * @description: 事件管理类
 * @date : 2020-03-02 13:52
 */
public class EventManager {

    private static final HashMap<String, Event> EVENTS = new HashMap<>();

    public EventManager() {
    }

    private static class Holder {
        private static final EventManager EVENT_MANAGER = new EventManager();
    }

    public static EventManager getInstance() {
        return Holder.EVENT_MANAGER;
    }

    public EventManager addEvent(@NonNull String name, @NonNull Event event) {
        EVENTS.put(name, event);
        return this;
    }

    public Event createEvent(@NonNull String action) {
        Event event = EVENTS.get(action);
        if (null == event) {
            return new UndefindEvent();
        }
        return event;
    }
}
