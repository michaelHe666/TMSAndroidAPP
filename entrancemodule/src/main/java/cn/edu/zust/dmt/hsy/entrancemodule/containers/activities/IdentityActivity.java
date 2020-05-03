package cn.edu.zust.dmt.hsy.entrancemodule.containers.activities;

import android.view.ViewGroup;

import androidx.annotation.NonNull;

import cn.edu.zust.dmt.hsy.entrancemodule.R;
import cn.edu.zust.dmt.hsy.entrancemodule.interfaces.listeners.IdentityDirectorListener;
import cn.edu.zust.dmt.hsy.entrancemodule.presenters.directors.IdentityDirector;
import cn.edu.zust.dmt.hsy.myannotationslibrary.annotations.MyRouter;
import cn.edu.zust.dmt.hsy.myannotationslibrary.constants.MyRouterPaths;
import cn.edu.zust.dmt.hsy.mybaselibrary.containers.activities.BaseActivity;
import cn.edu.zust.dmt.hsy.mybaselibrary.views.combined.mbl_MyTopBar;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 4/5/2020 21:37
 **/
@MyRouter(path = MyRouterPaths.IDENTITY_PATH)
public final class IdentityActivity extends BaseActivity {
    /**
     * @description cache-safe implementation for {@link IdentityDirectorListener}
     */
    private static final class MyIdentityDirectorListener extends BaseActivitySafeDirectorListener
            implements IdentityDirectorListener {
        private MyIdentityDirectorListener(@NonNull BaseActivity baseActivity) {
            super(baseActivity);
        }

        @NonNull
        @Override
        public ViewGroup getIdentityFragmentContainer() {
            return getSafeView(R.id.em_activity_identity_container_constraint_layout);
        }

        @NonNull
        @Override
        public mbl_MyTopBar getMyTopBar() {
            return getSafeView(R.id.em_activity_identity_my_top_bar);
        }
    }

    @Override
    protected int getViewRId() {
        return R.layout.em_activity_identity;
    }

    @Override
    protected void loadDirectorsToView() {
        new IdentityDirector().loadActors(getBaseViewListener()
                , new MyIdentityDirectorListener(this));
    }
}
