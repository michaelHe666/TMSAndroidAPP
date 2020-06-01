package cn.edu.zust.dmt.hsy.entrance_module.viewmodels;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import cn.edu.zust.dmt.hsy.entrance_module.interfaces.listeners.LoginViewModelListener;
import cn.edu.zust.dmt.hsy.entrance_module.repositories.LoginRepository;
import cn.edu.zust.dmt.hsy.my_base_library.viewmodels.BaseViewModel;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 5/22/2020 20:38
 **/
public final class LoginViewModel extends BaseViewModel<LoginViewModelListener> {

    private LoginRepository mLoginRepository = new LoginRepository();

    @Override
    protected void loadViewModelToListener() {
        initializeData();
        loadVoucherBarEvent();
        loadPasswordBarEvent();
        loadLoginEvent();
    }

    /**
     * @description load data to current viewModelListener
     */
    private void initializeData() {
        final LoginViewModelListener loginViewModelListener = getViewModelListener();
        loginViewModelListener.getVoucherBar().setContent(mLoginRepository.getVoucher());
        loginViewModelListener.getPasswordBar().setContent(mLoginRepository.getPassword());
    }

    /**
     * @description set voucher {@link TextWatcher}
     */
    private void loadVoucherBarEvent() {
        final LoginViewModelListener loginViewModelListener = getViewModelListener();
        loginViewModelListener.getVoucherBar().setContentWatcher(new TextWatcher() {
            private String lastInput = null;
            private boolean isInputLegal = true;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                lastInput = s.toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!mLoginRepository.setVoucher(s.toString())) {
                    loginViewModelListener.getVoucherInputErrorMethod().startWrappedMethod();
                    isInputLegal = false;
                } else {
                    isInputLegal = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!isInputLegal) {
                    s.replace(0, s.length(), lastInput);
                }
            }
        });
    }

    /**
     * @description set password {@link TextWatcher}
     */
    private void loadPasswordBarEvent() {
        final LoginViewModelListener loginViewModelListener = getViewModelListener();
        loginViewModelListener.getPasswordBar().setContentWatcher(new TextWatcher() {
            private String lastInput = null;
            private boolean isInputLegal = true;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                lastInput = s.toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!mLoginRepository.setPassword(s.toString())) {
                    loginViewModelListener.getPasswordInputErrorMethod().startWrappedMethod();
                    isInputLegal = false;
                } else {
                    isInputLegal = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!isInputLegal) {
                    s.replace(0, s.length(), lastInput);
                }
            }
        });
    }

    /**
     * @description set login {@link View.OnClickListener}
     */
    private void loadLoginEvent() {
        final LoginViewModelListener loginViewModelListener = getViewModelListener();
        loginViewModelListener.getLoginClickableView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mLoginRepository.login(loginViewModelListener.getLoginCallback())) {
                    loginViewModelListener.getLoginErrorMethod().startWrappedMethod();
                }
            }
        });
    }
}
