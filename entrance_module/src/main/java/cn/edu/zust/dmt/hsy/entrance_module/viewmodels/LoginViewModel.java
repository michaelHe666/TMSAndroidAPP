package cn.edu.zust.dmt.hsy.entrance_module.viewmodels;

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
public class LoginViewModel extends BaseViewModel<LoginViewModelListener> {

    @Override
    protected void loadViewModelToListener() {
        loadLoginEvent();
    }

    private void loadLoginEvent() {
        final LoginViewModelListener loginViewModelListener = getViewModelListener();
        loginViewModelListener.getLoginClickableView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LoginRepository().login(loginViewModelListener.getVoucherTextView().getContentString()
                        , loginViewModelListener.getPasswordTextView().getContentString()
                        , loginViewModelListener.getLoginCallback());
            }
        });
    }
}
