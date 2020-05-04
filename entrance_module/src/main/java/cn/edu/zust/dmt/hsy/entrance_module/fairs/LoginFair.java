package cn.edu.zust.dmt.hsy.entrance_module.fairs;

import androidx.annotation.NonNull;

import cn.edu.zust.dmt.hsy.entrance_module.interfaces.suppliers.LoginFairSupplier;
import cn.edu.zust.dmt.hsy.entrance_module.models.remote.RemoteConstants;
import cn.edu.zust.dmt.hsy.entrance_module.models.remote.request.LoginRequestModel;
import cn.edu.zust.dmt.hsy.entrance_module.models.remote.response.LoginResponseModel;
import cn.edu.zust.dmt.hsy.my_base_library.interfaces.others.BaseFairCallback;
import cn.edu.zust.dmt.hsy.my_base_library.interfaces.others.BaseNetworkCallback;
import cn.edu.zust.dmt.hsy.my_base_library.helpers.MyNetworkHelper;
import cn.edu.zust.dmt.hsy.my_base_library.models.remote.request.BaseNetworkRequest;
import cn.edu.zust.dmt.hsy.my_base_library.models.remote.response.BaseNetworkResponse;
import cn.edu.zust.dmt.hsy.my_base_library.fairs.BaseFair;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 4/22/2020 14:56
 **/
public final class LoginFair extends BaseFair<LoginFairSupplier<LoginResponseModel>> {
    /**
     * @param currentFairSupplier specialized supplier for implement fair
     */
    @Override
    public void loadFair(@NonNull LoginFairSupplier<LoginResponseModel> currentFairSupplier) {
        sendLoginNetworkRequest(currentFairSupplier.getVoucher(), currentFairSupplier.getToken()
                , currentFairSupplier.getBaseFairCallback());
    }

    /**
     * @param voucher  voucher of user
     * @param token    token for voucher
     * @param callback callback for director
     */
    private void sendLoginNetworkRequest(@NonNull final String voucher, @NonNull final String token
            , @NonNull final BaseFairCallback<LoginResponseModel> callback) {
        final BaseNetworkRequest<LoginRequestModel> requestModel = new BaseNetworkRequest<>(
                new LoginRequestModel(voucher, token));
        final BaseNetworkCallback<LoginResponseModel> networkCallback = new MyLoginNetworkCallback(callback);
        MyNetworkHelper.getInstance().doMyPost(RemoteConstants.LOGIN_PATH, requestModel, networkCallback);
    }

    /**
     * @description implementation of fair business
     */
    private static final class MyLoginNetworkCallback extends SafeNetWorkCallBack<LoginResponseModel> {
        private MyLoginNetworkCallback(BaseFairCallback<LoginResponseModel> baseNetworkCallback) {
            super(baseNetworkCallback);
        }

        @Override
        public void onResult(@NonNull BaseNetworkResponse<LoginResponseModel> response) {
            getBaseFairCallBack().onReturnModel(response.getData());
        }
    }
}
