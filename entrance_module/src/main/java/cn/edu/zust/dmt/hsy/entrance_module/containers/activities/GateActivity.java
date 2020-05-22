package cn.edu.zust.dmt.hsy.entrance_module.containers.activities;

import android.os.Bundle;
import android.view.View;

import cn.edu.zust.dmt.hsy.entrance_module.R;
import cn.edu.zust.dmt.hsy.my_annotations_library.annotations.MyRouter;
import cn.edu.zust.dmt.hsy.my_annotations_library.constants.MyRouterPaths;
import cn.edu.zust.dmt.hsy.my_base_library.containers.activities.BaseActivity;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 4/1/2020 21:11
 **/
@MyRouter(path = MyRouterPaths.GATE_PATH)
public final class GateActivity extends BaseActivity {

    /**
     * @description member views needed for {@link #findViews()}
     */
    private View mLoginView = null;
    private View mRegisterView = null;

    @Override
    protected int getLayoutRId() {
        return R.layout.em_activity_gate;
    }

    @Override
    protected void findViews() {
        mLoginView = findViewById(R.id.em_activity_gate_login_button);
        mRegisterView = findViewById(R.id.em_activity_gate_register_button);

//        Bundle bundle = new Bundle();
//        bundle.putString(String.valueOf(MyExtrasConstants.TAG_UNIVERSE_FRAGMENT)
//                , String.valueOf(MyExtrasConstants.VALUE_IDENTITY_LOGIN));
//        getSafeContainer().callMyRouter(MyRouterPaths.IDENTITY_PATH, bundle);
    }

    @Override
    protected void loadActorsToViews() {
        mLoginView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                callMyRouter(MyRouterPaths.LOGIN_PATH, bundle);
            }
        });
    }
}
