package cn.edu.zust.dmt.hsy.entrance_module.containers.activities;

import cn.edu.zust.dmt.hsy.common_module.views.combined.cm_MyTopBar;
import cn.edu.zust.dmt.hsy.entrance_module.R;
import cn.edu.zust.dmt.hsy.my_annotations_library.annotations.MyRouter;
import cn.edu.zust.dmt.hsy.my_annotations_library.constants.MyRouterPaths;
import cn.edu.zust.dmt.hsy.my_base_library.containers.activities.BaseActivity;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 5/22/2020 15:53
 **/
@MyRouter(path = MyRouterPaths.LOGIN_PATH)
public final class LoginActivity extends BaseActivity {

    private cm_MyTopBar mTopBar = null;

    @Override
    protected int getLayoutRId() {
        return R.layout.em_activity_login;
    }

    @Override
    protected void findViews() {
        mTopBar = findViewById(R.id.em_activity_login_my_top_bar);
    }

    @Override
    protected void loadActorsToViews() {

    }
}
