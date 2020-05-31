package cn.edu.zust.dmt.hsy.entrance_module.repositories;

import androidx.annotation.NonNull;

import cn.edu.zust.dmt.hsy.entrance_module.datas.remote.RemoteConstants;
import cn.edu.zust.dmt.hsy.entrance_module.datas.remote.request.LoginRequestData;
import cn.edu.zust.dmt.hsy.entrance_module.datas.remote.response.LoginResponseData;
import cn.edu.zust.dmt.hsy.my_base_library.datas.remote.request.BaseNetworkRequest;
import cn.edu.zust.dmt.hsy.my_base_library.helpers.MyNetworkHelper;
import cn.edu.zust.dmt.hsy.my_base_library.interfaces.others.BaseNetworkCallback;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 5/24/2020 14:20
 **/
public class LoginRepository {
    /**
     * @param voucher voucher for password
     * @param password password for voucher
     * @param callback actions for callback
     */
    public void login(@NonNull final String voucher, @NonNull final String password
            , @NonNull final BaseNetworkCallback<LoginResponseData> callback) {
        BaseNetworkRequest<LoginRequestData> baseNetworkRequest = new BaseNetworkRequest<>(
                new LoginRequestData(voucher, password));
        MyNetworkHelper.getInstance().doMyPost(RemoteConstants.LOGIN_PATH, baseNetworkRequest, callback);
    }
}
