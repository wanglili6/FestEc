package com.wll.latte.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * @author wanglili
 * @description:
 * @date : 2020-02-14 18:02
 */
public enum EcIcons implements Icon {
   icon_zhifu('\ue606') ,
   icon_pay('\ue606') ;
    private char charcter;

    EcIcons(char charcter) {
        this.charcter = charcter;
    }

    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return 0;
    }
}
