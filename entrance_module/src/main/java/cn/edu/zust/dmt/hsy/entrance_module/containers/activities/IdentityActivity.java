package cn.edu.zust.dmt.hsy.entrance_module.containers.activities;

import android.view.ViewGroup;

import androidx.annotation.NonNull;

import cn.edu.zust.dmt.hsy.entrance_module.R;
import cn.edu.zust.dmt.hsy.entrance_module.interfaces.listeners.IdentityDirectorListener;
import cn.edu.zust.dmt.hsy.entrance_module.directors.IdentityDirector;
import cn.edu.zust.dmt.hsy.my_annotations_library.annotations.MyRouter;
import cn.edu.zust.dmt.hsy.my_annotations_library.constants.MyRouterPaths;
import cn.edu.zust.dmt.hsy.my_base_library.containers.activities.BaseActivity;
import cn.edu.zust.dmt.hsy.common_module.views.combined.cm_MyTopBar;

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
        private MyIdentityDirectorListener(@NonNull final BaseActivity baseActivity) {
            super(baseActivity);
        }

        @NonNull
        @Override
        public ViewGroup getIdentityFragmentContainer() {
            return getSafeView(R.id.em_activity_identity_container_constraint_layout);
        }

        @NonNull
        @Override
        public cm_MyTopBar getMyTopBar() {
            return getSafeView(R.id.em_activity_identity_my_top_bar);
        }
    }

    @Override
    protected int getViewRId() {
        return R.layout.em_activity_identity;
    }

    @Override
    protected void loadDirectorsToView() {
        new IdentityDirector().loadActors(getBaseContainerListener()
                , new MyIdentityDirectorListener(this));
    }
}