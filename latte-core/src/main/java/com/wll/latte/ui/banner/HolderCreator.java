package com.wll.latte.ui.banner;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * @author wanglili
 * @description:
 * @date : 2020-02-26 16:04
 */
public class HolderCreator  implements CBViewHolderCreator<ImageHolder> {
    @Override
    public ImageHolder createHolder() {

        return new ImageHolder();
    }
}
