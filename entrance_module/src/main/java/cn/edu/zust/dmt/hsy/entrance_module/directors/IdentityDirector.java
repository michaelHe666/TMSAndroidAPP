package cn.edu.zust.dmt.hsy.entrance_module.directors;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;

import cn.edu.zust.dmt.hsy.entrance_module.R;
import cn.edu.zust.dmt.hsy.entrance_module.interfaces.listeners.IdentityDirectorListener;
import cn.edu.zust.dmt.hsy.entrance_module.containers.fragments.LoginFragment;
import cn.edu.zust.dmt.hsy.entrance_module.containers.fragments.RegisterFragment;
import cn.edu.zust.dmt.hsy.my_base_library.constants.MyExtrasConstants;
import cn.edu.zust.dmt.hsy.my_base_library.interfaces.others.BaseExtrasListener;
import cn.edu.zust.dmt.hsy.my_base_library.interfaces.others.BaseContainerListener;
import cn.edu.zust.dmt.hsy.my_base_library.directors.BaseDirector;
import cn.edu.zust.dmt.hsy.my_base_library.helpers.MyErrorHelper;
import cn.edu.zust.dmt.hsy.common_module.views.combined.cm_MyTopBar;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 4/6/2020 15:09
 **/
public final class IdentityDirector extends BaseDirector<IdentityDirectorListener> {
    /**
     * @param baseContainerListener         base view listener
     * @param identityDirectorListener current specialized view listener
     * @description load actors to view holder
     */
    public void loadActors(@NonNull final BaseContainerListener baseContainerListener
            , @NonNull final IdentityDirectorListener identityDirectorListener) {
        addMyExtrasParser(baseContainerListener, identityDirectorListener);
    }

    /**
     * @description add login/register {@link BaseExtrasListener}  to Listener holder
     */
    private void addMyExtrasParser(@NonNull final BaseContainerListener baseContainerListener
            , @NonNull final IdentityDirectorListener identityDirectorListener) {
        baseContainerListener.addMyExtrasParser(new MyExtrasParser(baseContainerListener, identityDirectorListener));
    }

    private static final class MyExtrasParser extends SafeDirectorInnerClass<IdentityDirectorListener>
            implements BaseExtrasListener {
        private MyExtrasParser(@NonNull BaseContainerListener baseContainerListener
                , @NonNull IdentityDirectorListener baseDirectorListener) {
            super(baseContainerListener, baseDirectorListener);
        }

        @Override
        public void parseMyExtras(@NonNull Bundle myExtras) {
            final String route = myExtras.getString(String.valueOf(MyExtrasConstants.TAG_UNIVERSE_FRAGMENT));
            if (route == null) {
                MyErrorHelper.showMyNullPointerException("No target extra for identityFair!");
            } else {
                final cm_MyTopBar myTopBar = getSafeDirector().getMyTopBar();
                if (route.equals(String.valueOf(MyExtrasConstants.VALUE_IDENTITY_LOGIN))) {
                    myTopBar.setTitle(R.string.em_string_universe_login);
                    myTopBar.showLeftButton(R.drawable.cm_icon_universe_back);
                    myTopBar.setLeftButtonOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            getSafeContainer().triggerBackPressedEvent();
                        }
                    });
                    getSafeContainer().showBaseFragment(LoginFragment.class
                            , getSafeDirector().getIdentityFragmentContainer().getId(), null);
                } else if (route.equals(String.valueOf(MyExtrasConstants.VALUE_IDENTITY_REGISTER))) {
                    myTopBar.setTitle(R.string.em_string_universe_register);
                    myTopBar.showLeftButton(R.drawable.cm_icon_universe_back);
                    myTopBar.setLeftButtonOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            getSafeContainer().triggerBackPressedEvent();
                        }
                    });
                    getSafeContainer().showBaseFragment(RegisterFragment.class
                            , getSafeDirector().getIdentityFragmentContainer().getId(), null);
                }
            }
        }
    }
}
