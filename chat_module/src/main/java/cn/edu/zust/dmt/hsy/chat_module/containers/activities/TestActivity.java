package cn.edu.zust.dmt.hsy.chat_module.containers.activities;

import androidx.annotation.NonNull;

import cn.edu.zust.dmt.hsy.chat_module.R;
import cn.edu.zust.dmt.hsy.my_annotations_library.annotations.MyRouter;
import cn.edu.zust.dmt.hsy.my_annotations_library.constants.MyRouterPaths;
import cn.edu.zust.dmt.hsy.my_base_library.containers.activities.BaseActivity;
import cn.edu.zust.dmt.hsy.my_base_library.interfaces.presenter_listeners.NullPresenterListener;
import cn.edu.zust.dmt.hsy.my_base_library.presenters.NullPresenter;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 6/14/2020 13:24
 **/
@MyRouter(path = MyRouterPaths.TEST_PATH)
public final class TestActivity extends BaseActivity<NullPresenterListener, NullPresenter> {
    @Override
    protected int getLayoutRId() {
        return R.layout.cm1_acitivty_test;
    }

    @NonNull
    @Override
    protected NullPresenterListener getPresenterListener() {
        return new NullPresenterListener() {
        };
    }

    @Override
    protected void findViews() {

    }

    @Override
    protected void loadActorsToViews() {

    }
}
