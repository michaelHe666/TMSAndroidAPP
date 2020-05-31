package cn.edu.zust.dmt.hsy.entrance_module.containers.activities;

import cn.edu.zust.dmt.hsy.entrance_module.R;
import cn.edu.zust.dmt.hsy.my_annotations_library.annotations.MyRouter;
import cn.edu.zust.dmt.hsy.my_annotations_library.constants.MyRouterPaths;
import cn.edu.zust.dmt.hsy.my_base_library.containers.activities.BaseActivity;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 5/22/2020 19:03
 **/
@MyRouter(path = MyRouterPaths.REGISTER_PATH)
public final class RegisterActivity extends BaseActivity {
    @Override
    protected int getLayoutRId() {
        return R.layout.em_activity_register;
    }

    @Override
    protected void findViews() {

    }

    @Override
    protected void loadActorsToViews() {

    }

    @Override
    protected void refreshViewModelListener() {

    }
}
