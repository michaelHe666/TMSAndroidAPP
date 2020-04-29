package cn.edu.zust.dmt.hsy.mybaselibrary;

import android.app.Application;

import cn.edu.zust.dmt.hsy.mybaselibrary.helpers.MyRouterHelper;

/**
 * @projectName TMS
 * @author MR.M
 * @since 4/11/2020 20:54
 * @version 1.0
 * @description $
 *
 * copyright(c) all rights reserved by MR.M
 **/
public final class MyApplication extends Application {
    /**
     * @description initialize app framework to ensure its functions
     */
    @Override
    public void onCreate() {
        super.onCreate();
        MyRouterHelper.INSTANCE.initializeMyRouter(this);
    }
}
