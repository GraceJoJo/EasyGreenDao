package com.jojo.sample.easygreendao;

import android.app.Application;
import android.content.Context;

import com.jojo.sample.easygreendao.db.DbManager;

/**
 * author : JOJO
 * e-mail : 18510829974@163.com
 * date   : 2019/1/30 4:29 PM
 * desc   :
 */
public class MyApplication extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        initDataBase();

    }
    /**
     * 初始化数据库
     */
    public static DbManager initDataBase() {
        return DbManager.getInstance();
    }

    public static Context getContext() {
        return context;
    }
}
