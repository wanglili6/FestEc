package com.wll.latte.ui.banner;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.wll.latte.R;

import java.util.List;

/**
 * @author wanglili
 * @description: banner创建
 * @date : 2020-02-26 16:03
 */
public class BannerCreator {

    public static void setDefault(ConvenientBanner<String> convenientBanner, List<String> bannerList , OnItemClickListener clickListener){
        convenientBanner.setPages(new HolderCreator(),bannerList)
                .setPageIndicator(new int[]{R.drawable.dot_normal,R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(clickListener)
                .startTurning(3000)
               .setCanLoop(true);


    }
}
