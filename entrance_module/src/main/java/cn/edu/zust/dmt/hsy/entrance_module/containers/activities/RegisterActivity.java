package cn.edu.zust.dmt.hsy.entrance_module.containers.activities;

import androidx.annotation.NonNull;

import cn.edu.zust.dmt.hsy.common_module.containers.activities.MyActivity;
import cn.edu.zust.dmt.hsy.entrance_module.R;
import cn.edu.zust.dmt.hsy.my_annotations_library.annotations.MyRouter;
import cn.edu.zust.dmt.hsy.my_annotations_library.constants.MyRouterPaths;
import cn.edu.zust.dmt.hsy.my_base_library.interfaces.presenter_listeners.NullPresenterListener;
import cn.edu.zust.dmt.hsy.my_base_library.presenters.NullPresenter;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 5/22/2020 19:03
 **/
@MyRouter(path = MyRouterPaths.REGISTER_PATH)
public final class RegisterActivity extends MyActivity<NullPresenterListener, NullPresenter> {
    @Override
    protected int getLayoutRId() {
        return R.layout.em_activity_register;
    }

    @NonNull
    @Override
    protected Class<NullPresenter> getViewModelClass() {
        return NullPresenter.class;
    }

    @NonNull
    @Override
    protected NullPresenterListener getViewModelListener() {
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
