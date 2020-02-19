package com.wll.latte.ui.loader;

import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatDialog;

import com.wang.avi.AVLoadingIndicatorView;
import com.wll.latte.R;
import com.wll.latte.util.Dimen.DimenUtil;

import java.util.ArrayList;

/**
 * @author wanglili
 * @description:
 * @date : 2020-02-18 11:47
 */
public class LatteLoader {
    //默认缩放比
    private static final int LOADER_SIZE_SCALE = 8;
    //上下偏移量
    private static final int LOADER_OFFSET_SCALE = 10;
    //统一管理loader的list
    private static final ArrayList<AppCompatDialog> LOADERS = new ArrayList<>();
    //默认的loader样式
    private static final String DEFULT_LOADER = LoaderStyle.BallClipRotatePulseIndicator.name();

    /**
     * 展示loading
     *
     * @param context 上下文
     * @param type    load的类型
     */
    public static void showLoading(Context context, String type) {
        //自定义dialog
        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);
        //创建load de
        final AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreator.create(type, context);
        dialog.setContentView(avLoadingIndicatorView);
        //获取屏幕宽高
        final int screeWidth = DimenUtil.getScreeWidth();
        final int screeHeight = DimenUtil.getScreeHeight();

        //获取window
        final Window dialogWindow = dialog.getWindow();
        if (null != dialogWindow) {
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            //设置缩放比
            lp.width = screeWidth / LOADER_SIZE_SCALE;
            lp.height = screeHeight / LOADER_SIZE_SCALE;
            //额外处理--增加偏移量
            lp.height = lp.height + screeHeight / LOADER_SIZE_SCALE;
            lp.gravity = Gravity.CENTER;
        }
        LOADERS.add(dialog);
        dialog.show();

    }

    /**
     * 记载默认的样式
     *
     * @param context
     */
    public static void showLoading(Context context) {
        showLoading(context, DEFULT_LOADER);
    }

    /**
     * 记载默认的样式
     *
     * @param context
     */
    public static void showLoading(Context context,Enum<LoaderStyle> type) {
        showLoading(context, type.name());
    }

    /**
     * 停止加载load
     */
    public static void stopLoading() {
        for (AppCompatDialog dialog : LOADERS) {
            if (null != dialog) {
                if (dialog.isShowing()) {
                    dialog.cancel();
                }
            }
        }
    }
}
