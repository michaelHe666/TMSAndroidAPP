package cn.edu.zust.dmt.hsy.my_base_library.interfaces.others;

import androidx.annotation.NonNull;

import cn.edu.zust.dmt.hsy.my_base_library.datas.remote.response.BaseNetworkResponse;
import cn.edu.zust.dmt.hsy.my_base_library.datas.remote.response.BaseResponseData;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 4/23/2020 16:04
 **/
public interface BaseNetworkCallback<T extends BaseResponseData> {
    /**
     * @param response success network callback which means no error happened during network connection
     */
    void onResult(@NonNull BaseNetworkResponse<T> response);

    /**
     * @param e failed network callback which means error caught during network connection
     */
    void onError(@NonNull Throwable e);

    /**
     * @description whatever to do after network callback is processed
     */
    void onComplete();
}
