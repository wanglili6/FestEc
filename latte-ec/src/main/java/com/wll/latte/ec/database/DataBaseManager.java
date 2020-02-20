package com.wll.latte.ec.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * @author wanglili
 * @description: 数据库管理器
 * @date : 2020-02-20 17:08
 */
public class DataBaseManager {
    private DaoSession mDaoSession = null;
    private UserProfileDao mDao = null;

    /**
     * 初始化数据
     *
     * @param context
     * @return
     */
    public DataBaseManager init(Context context) {
        initDao(context);
        return this;
    }

    /*

    单例创建
     */
    private static final class Holder {
        private static final DataBaseManager INSTENCE = new DataBaseManager();
    }

    public static DataBaseManager getInstance() {
        return Holder.INSTENCE;
    }

    /**
     * 初始化dao
     */
    private void initDao(Context context) {
        final ReleaseOpeHelper helper = new ReleaseOpeHelper(context, "fast_ec.db");
        final Database db = helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
        mDao = mDaoSession.getUserProfileDao();
    }

    public final UserProfileDao getDao() {
        return mDao;
    }
}
