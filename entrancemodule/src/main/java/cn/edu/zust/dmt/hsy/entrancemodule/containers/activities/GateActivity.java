package cn.edu.zust.dmt.hsy.entrancemodule.containers.activities;

import android.view.View;

import androidx.annotation.NonNull;

import cn.edu.zust.dmt.hsy.entrancemodule.R;
import cn.edu.zust.dmt.hsy.entrancemodule.contracts.listeners.GateDirectorListener;
import cn.edu.zust.dmt.hsy.entrancemodule.presenters.directors.GateDirector;
import cn.edu.zust.dmt.hsy.myannotationslibrary.annotations.MyRouter;
import cn.edu.zust.dmt.hsy.myannotationslibrary.constants.MyRouterPaths;
import cn.edu.zust.dmt.hsy.mybaselibrary.containers.activities.BaseActivity;

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
        new GateDirector().loadActors(getBaseViewListener(), new MyGateDirectorListener(this));
    }
}
