package cn.edu.zust.dmt.hsy.mainmodule.views.activities;

import cn.edu.zust.dmt.hsy.mainmodule.R;
import cn.edu.zust.dmt.hsy.myannotationslibrary.annotations.MyRouter;
import cn.edu.zust.dmt.hsy.myannotationslibrary.constants.MyRouterPaths;
import cn.edu.zust.dmt.hsy.mybaselibrary.views.activities.BaseActivity;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 4/12/2020 9:52
 **/
@MyRouter(path = MyRouterPaths.HOME_PATH)
public final class HomeActivity extends BaseActivity {
    @Override
    protected int getViewRId() {
        return R.layout.mm_activity_home;
    }

    @Override
    protected void loadDirectorsToView() {

    }
}
