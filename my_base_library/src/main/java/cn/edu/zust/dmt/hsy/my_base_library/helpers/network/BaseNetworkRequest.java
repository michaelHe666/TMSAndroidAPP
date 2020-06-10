package cn.edu.zust.dmt.hsy.my_base_library.helpers.network;

import android.os.Build;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import cn.edu.zust.dmt.hsy.my_base_library.BuildConfig;
import cn.edu.zust.dmt.hsy.my_base_library.datas.remote.request.BaseRequestData;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 4/23/2020 16:29
 **/
//todo:try scan child class of this and delay initialization of base request to common module
public final class BaseNetworkRequest<T extends BaseRequestData> {
    private final JsonElement args;
    private final JsonElement deviceInfo;

    public BaseNetworkRequest(@NonNull final T args) {
        Gson gson = new Gson();
        this.args = gson.toJsonTree(args);
        this.deviceInfo = getDeviceInfo(gson);
    }

    private JsonElement getDeviceInfo(@NonNull final Gson gson) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("OS", gson.toJsonTree("Android"));
        jsonObject.add("MobileType", gson.toJsonTree(Build.MODEL));
        jsonObject.add("MobileSDK", gson.toJsonTree(Build.VERSION.SDK_INT));
        jsonObject.add("AppVersion", gson.toJsonTree(BuildConfig.VERSION_CODE));
        return jsonObject;
    }
}
