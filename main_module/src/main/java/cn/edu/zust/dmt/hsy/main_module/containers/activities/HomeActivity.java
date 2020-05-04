package cn.edu.zust.dmt.hsy.main_module.containers.activities;

import cn.edu.zust.dmt.hsy.main_module.R;
import cn.edu.zust.dmt.hsy.my_annotations_library.annotations.MyRouter;
import cn.edu.zust.dmt.hsy.my_annotations_library.constants.MyRouterPaths;
import cn.edu.zust.dmt.hsy.my_base_library.containers.activities.BaseActivity;

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
