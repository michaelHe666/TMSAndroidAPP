package cn.edu.zust.dmt.hsy.entrance_module.containers.fragments;

import android.view.View;

import androidx.annotation.NonNull;

import cn.edu.zust.dmt.hsy.entrance_module.R;
import cn.edu.zust.dmt.hsy.entrance_module.interfaces.listeners.LoginDirectorListener;
import cn.edu.zust.dmt.hsy.entrance_module.directors.LoginDirector;
import cn.edu.zust.dmt.hsy.my_base_library.containers.fragments.BaseFragment;
import cn.edu.zust.dmt.hsy.common_module.views.combined.cm_MyFormBar;

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
        public cm_MyFormBar getVoucherBar() {
            return getWeakReferenceView(R.id.em_fragment_login_voucher_my_form_bar);
        }

        @NonNull
        @Override
        public cm_MyFormBar getTokenBar() {
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
        new LoginDirector().loadActors(getBaseContainerListener(), new MyLoginDirectorListener(this));
    }
}
