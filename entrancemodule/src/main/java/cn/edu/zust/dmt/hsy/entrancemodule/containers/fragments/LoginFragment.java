package cn.edu.zust.dmt.hsy.entrancemodule.containers.fragments;

import android.view.View;

import androidx.annotation.NonNull;

import cn.edu.zust.dmt.hsy.entrancemodule.R;
import cn.edu.zust.dmt.hsy.entrancemodule.contracts.listeners.LoginDirectorListener;
import cn.edu.zust.dmt.hsy.entrancemodule.presenters.directors.LoginDirector;
import cn.edu.zust.dmt.hsy.mybaselibrary.containers.fragments.BaseFragment;
import cn.edu.zust.dmt.hsy.mybaselibrary.views.combined.mbl_MyFormBar;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 4/6/2020 15:42
 **/
public final class LoginFragment extends BaseFragment {
    /**
     * @description cache-safe implementation for {@link LoginDirectorListener}
     */
    private static final class MyLoginDirectorListener extends BaseFragmentDirectorListener
            implements LoginDirectorListener {
        private MyLoginDirectorListener(@NonNull final BaseFragment baseFragment) {
            super(baseFragment);
        }

        @NonNull
        @Override
        public mbl_MyFormBar getVoucherBar() {
            return getWeakReferenceView(R.id.em_fragment_login_voucher_my_form_bar);
        }

        @NonNull
        @Override
        public mbl_MyFormBar getTokenBar() {
            return getWeakReferenceView(R.id.em_fragment_login_token_my_form_bar);
        }

        @NonNull
        @Override
        public View getLoginRequestView() {
            return getWeakReferenceView(R.id.em_fragment_login_confirm_button);
        }
    }

    @Override
    protected int getViewRId() {
        return R.layout.em_fragment_login;
    }

    @Override
    protected void loadDirectorsToView() {
        new LoginDirector().loadActors(getBaseViewListener(), new MyLoginDirectorListener(this));
    }
}
