package cn.edu.zust.dmt.hsy.entrancemodule.presenters.directors;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;

import cn.edu.zust.dmt.hsy.entrancemodule.R;
import cn.edu.zust.dmt.hsy.entrancemodule.contracts.listeners.IdentityDirectorListener;
import cn.edu.zust.dmt.hsy.entrancemodule.containers.fragments.LoginFragment;
import cn.edu.zust.dmt.hsy.entrancemodule.containers.fragments.RegisterFragment;
import cn.edu.zust.dmt.hsy.mybaselibrary.constants.MyExtraConstants;
import cn.edu.zust.dmt.hsy.mybaselibrary.contracts.others.BaseExtrasListener;
import cn.edu.zust.dmt.hsy.mybaselibrary.contracts.others.BaseViewListener;
import cn.edu.zust.dmt.hsy.mybaselibrary.presenters.directors.BaseDirector;
import cn.edu.zust.dmt.hsy.mybaselibrary.utils.MyErrorUtils;
import cn.edu.zust.dmt.hsy.mybaselibrary.views.combined.mbl_MyTopBar;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 4/6/2020 15:09
 **/
public final class IdentityDirector extends BaseDirector<IdentityDirectorListener> {
    /**
     * @param baseViewListener         base view listener
     * @param identityDirectorListener current specialized view listener
     * @description load actors to view holder
     */
    public void loadActors(@NonNull final BaseViewListener baseViewListener
            , @NonNull final IdentityDirectorListener identityDirectorListener) {
        addMyExtrasParser(baseViewListener, identityDirectorListener);
    }

    /**
     * @description add login/register {@link BaseExtrasListener}  to Listener holder
     */
    private void addMyExtrasParser(@NonNull final BaseViewListener baseViewListener
            , @NonNull final IdentityDirectorListener identityDirectorListener) {
        baseViewListener.addMyExtrasParser(new MyExtrasParser(baseViewListener, identityDirectorListener));
    }

    private static final class MyExtrasParser extends SafeDirectorInnerClass<IdentityDirectorListener>
            implements BaseExtrasListener {
        private MyExtrasParser(@NonNull BaseViewListener baseViewListener
                , @NonNull IdentityDirectorListener baseDirectorListener) {
            super(baseViewListener, baseDirectorListener);
        }

        @Override
        public void parseMyExtras(@NonNull Bundle myExtras) {
            final String route = myExtras.getString(String.valueOf(MyExtraConstants.TAG_UNIVERSE_FRAGMENT));
            if (route == null) {
                MyErrorUtils.showMyNullPointerException("No target extra for identityFair!");
            } else {
                final mbl_MyTopBar myTopBar = getDirectorWeakReference().getMyTopBar();
                if (route.equals(String.valueOf(MyExtraConstants.VALUE_IDENTITY_LOGIN))) {
                    myTopBar.setTitle(R.string.em_string_universe_login);
                    myTopBar.showLeftButton(R.drawable.mbl_icon_universe_back);
                    myTopBar.setLeftButtonOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            getViewWeakReference().triggerBackPressedEvent();
                        }
                    });
                    getViewWeakReference().showBaseFragment(LoginFragment.class
                            , getDirectorWeakReference().getIdentityFragmentContainer().getId(), null);
                } else if (route.equals(String.valueOf(MyExtraConstants.VALUE_IDENTITY_REGISTER))) {
                    myTopBar.setTitle(R.string.em_string_universe_register);
                    myTopBar.showLeftButton(R.drawable.mbl_icon_universe_back);
                    myTopBar.setLeftButtonOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            getViewWeakReference().triggerBackPressedEvent();
                        }
                    });
                    getViewWeakReference().showBaseFragment(RegisterFragment.class
                            , getDirectorWeakReference().getIdentityFragmentContainer().getId(), null);
                }
            }
        }
    }
}
