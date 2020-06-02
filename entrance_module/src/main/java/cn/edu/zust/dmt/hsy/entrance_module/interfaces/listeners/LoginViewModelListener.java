package cn.edu.zust.dmt.hsy.entrance_module.interfaces.listeners;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import cn.edu.zust.dmt.hsy.common_module.views.combined.cm_MyFormBar;
import cn.edu.zust.dmt.hsy.entrance_module.datas.remote.response.LoginResponseData;
import cn.edu.zust.dmt.hsy.my_base_library.interfaces.listeners.BaseViewModelListener;
import cn.edu.zust.dmt.hsy.my_base_library.interfaces.others.BaseNetworkCallback;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 5/22/2020 20:38
 **/
public interface LoginViewModelListener extends BaseViewModelListener {
    /**
     * @return {@link TextView} contains voucher string
     */
    @NonNull
    cm_MyFormBar getVoucherBar();

    /**
     * @return {@link TextView} contains password string
     */
    @NonNull
    cm_MyFormBar getPasswordBar();

    /**
     * @return {@link View} for trigger login event by click
     */
    @NonNull
    View getLoginClickableView();

    /**
     * @return {@link BaseNetworkCallback} contains view actions
     */
    @NonNull
    BaseNetworkCallback<LoginResponseData> getLoginCallback();

    /**
     * @description contains method for illegal voucher input
     */
    void sendVoucherInputError();

    /**
     * @description contains method for illegal password input
     */
    void sendPasswordInputError();

    /**
     * @description contains method for illegal login request
     */
    void sendLoginError();
}
