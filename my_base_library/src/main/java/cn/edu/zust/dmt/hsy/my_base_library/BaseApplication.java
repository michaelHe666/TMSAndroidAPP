package cn.edu.zust.dmt.hsy.my_base_library;

import android.app.Application;

import cn.edu.zust.dmt.hsy.my_base_library.helpers.MyRouterHelper;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * <p>
 * copyright(c) all rights reserved by MR.M
 * @since 4/11/2020 20:54
 **/
public class BaseApplication extends Application {
    /**
     * @description initialize app framework to ensure its functions
     */
    @Override
    public void onCreate() {
        super.onCreate();
        MyRouterHelper.INSTANCE.initializeMyRouter(this);
    }
}
