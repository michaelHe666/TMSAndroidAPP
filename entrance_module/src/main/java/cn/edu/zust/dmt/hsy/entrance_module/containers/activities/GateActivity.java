package cn.edu.zust.dmt.hsy.entrance_module.containers.activities;

import android.view.View;

import androidx.annotation.NonNull;

import cn.edu.zust.dmt.hsy.entrance_module.R;
import cn.edu.zust.dmt.hsy.entrance_module.interfaces.listeners.GateDirectorListener;
import cn.edu.zust.dmt.hsy.entrance_module.directors.GateDirector;
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
     * @description cache-safe implementation for {@link GateDirectorListener}
     */
    private static final class MyGateDirectorListener extends BaseActivitySafeDirectorListener
            implements GateDirectorListener {
        private MyGateDirectorListener(@NonNull final BaseActivity baseActivity) {
            super(baseActivity);
        }

        @NonNull
        @Override
        public View getLoginRouterView() {
            return getSafeView(R.id.em_activity_gate_login_button);
        }

        @NonNull
        @Override
        public View getRegisterRouterView() {
            return getSafeView(R.id.em_activity_gate_register_button);
        }
    }

    @Override
    protected int getViewRId() {
        return R.layout.em_activity_gate;
    }

    @Override
    protected void loadDirectorsToView() {
        new GateDirector().loadActors(getBaseContainerListener(), new MyGateDirectorListener(this));
    }
}
