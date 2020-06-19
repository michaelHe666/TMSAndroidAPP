package cn.edu.zust.dmt.hsy.entrance_module.repositories;

import androidx.annotation.NonNull;

import cn.edu.zust.dmt.hsy.entrance_module.datas.remote.RemoteConstants;
import cn.edu.zust.dmt.hsy.entrance_module.datas.remote.request.LoginRequestData;
import cn.edu.zust.dmt.hsy.entrance_module.datas.remote.response.LoginResponseData;
import cn.edu.zust.dmt.hsy.my_base_library.helpers.network.BaseNetworkRequest;
import cn.edu.zust.dmt.hsy.my_base_library.helpers.network.MyNetworkHelper;
import cn.edu.zust.dmt.hsy.my_base_library.interfaces.others.BaseNetworkCallback;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 5/24/2020 14:20
 **/
public final class LoginRepository {

    private String mVoucher = "";
    private String mPassword = "";

    /**
     * @param callback handler for view callback
     * @return method called illegal or not
     */
    public boolean login(@NonNull final BaseNetworkCallback<LoginResponseData> callback) {
        if (mVoucher.matches("[A-Z][0-9]{4}") && mPassword.matches("[a-zA-Z0-9_]{6,20}")) {
            BaseNetworkRequest<LoginRequestData> baseNetworkRequest = new BaseNetworkRequest<>(
                    new LoginRequestData(mVoucher, mPassword));
            MyNetworkHelper.getInstance().post(RemoteConstants.LOGIN_PATH, baseNetworkRequest, callback);
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param voucher input for {@link #mVoucher}
     * @return false for illegal input
     */
    public boolean setVoucher(@NonNull final String voucher) {
        if (voucher.matches("[a-zA-Z0-9_]{0,20}")) {
            if (!voucher.equals(mVoucher)) {
                mVoucher = voucher;
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param password input for {@link #mPassword}
     * @return false for illegal input
     */
    public boolean setPassword(@NonNull final String password) {
        if (password.matches("[a-zA-Z0-9_]{0,20}")) {
            if (!password.equals(mPassword)) {
                mPassword = password;
            }
            return true;
        } else {
            return false;
        }
    }

    @NonNull
    public String getVoucher() {
        return mVoucher;
    }

    @NonNull
    public String getPassword() {
        return mPassword;
    }
}
