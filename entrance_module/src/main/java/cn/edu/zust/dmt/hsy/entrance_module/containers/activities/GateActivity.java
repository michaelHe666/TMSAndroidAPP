package cn.edu.zust.dmt.hsy.entrance_module.containers.activities;

import android.view.View;

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
 * @since 4/1/2020 21:11
 **/
@MyRouter(path = MyRouterPaths.GATE_PATH)
public final class GateActivity extends MyActivity<NullPresenterListener, NullPresenter> {

    /**
     * @description member views needed for {@link #findViews()}
     */
    private View mLoginView;
    private View mRegisterView;

    @Override
    protected int getLayoutRId() {
        return R.layout.em_activity_gate;
    }

    @NonNull
    @Override
    protected NullPresenterListener getPresenterListener() {
        return new NullPresenterListener() {
        };
    }

    @Override
    protected void findViews() {
        mLoginView = findViewById(R.id.em_activity_gate_login_button);
        mRegisterView = findViewById(R.id.em_activity_gate_register_button);
    }

    @Override
    protected void loadActorsToViews() {
        mLoginView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callMyRouter(MyRouterPaths.LOGIN_PATH, null);
            }
        });
        mRegisterView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callMyRouter(MyRouterPaths.REGISTER_PATH, null);
            }
        });
    }
}
